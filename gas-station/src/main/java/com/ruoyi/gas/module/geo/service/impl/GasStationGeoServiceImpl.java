package com.ruoyi.gas.module.geo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.mapper.GasStationGeoMapper;
import com.ruoyi.gas.module.map.amap.AmapClient;
import com.ruoyi.gas.module.map.amap.model.DrivingDirectionRequest;
import com.ruoyi.gas.module.map.amap.model.DrivingDirectionResult;
import com.ruoyi.gas.module.map.amap.model.driving.Cost;
import com.ruoyi.gas.module.map.amap.model.driving.Paths;
import com.ruoyi.gas.module.map.amap.model.driving.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.gas.module.geo.domain.GasStationGeo;
import com.ruoyi.gas.module.geo.service.IGasStationGeoService;

import javax.annotation.Resource;

import static com.ruoyi.gas.module.geo.utils.DistanceCalculateUtils.*;

/**
 * 加油站地理信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
@Service
public class GasStationGeoServiceImpl implements IGasStationGeoService 
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmapClient amapClient;
    @Autowired
    private GasStationGeoMapper gasStationGeoMapper;
    @Resource(name = "gasStationExecutor")
    private Executor executor;

    @Value("${gas.amap.key}")
    private String amapKey;

    @Override
    public List<GasStationGeo> listStationDistance(GasStationInfo systemStation, List<GasStationInfo> outSystemStationList) {
        List<GasStationGeo> systemStationGeos =
                gasStationGeoMapper.selectGasStationGeoBySystemStationId(systemStation.getId());
        if (systemStationGeos == null) {
            return listStationDistance(systemStation, outSystemStationList, true);
        }
        return systemStationGeos;
    }

    @Override
    public List<GasStationGeo> listStationDistance(GasStationInfo systemStation,
                                                   List<GasStationInfo> outSystemStationList,
                                                   boolean newest) {
        return null;
    }

    //******************************************************************************************************8

    public void saveGeoInfo(int radius, GasStationInfo systemStation, List<GasStationInfo> outSystemStation) {
        Objects.requireNonNull(systemStation);
        if (outSystemStation == null || outSystemStation.size() == 0) return;
        CountDownLatch cdl = new CountDownLatch(outSystemStation.size());
        List<GasStationGeo> saveList = new ArrayList<>();
        List<GasStationGeo> updateList = new ArrayList<>();

        // 分析时间分布
        AtomicLong dbQueryTime = new AtomicLong();
        AtomicLong apiTime = new AtomicLong();

        for (GasStationInfo out : outSystemStation) {
            // 异步执行所有的API查询操作
            CompletableFuture.runAsync(() -> {
                long staStart = System.currentTimeMillis(); // TODO 统计API时间
                DrivingDirectionRequest request = new DrivingDirectionRequest(
                        amapKey,
                        systemStation.getLocation(),
                        out.getLocation()
                );
                DrivingDirectionResult result = amapClient.drivingDirectionRequest(request);
                Paths paths = result.getRoute().getPaths().get(0);
                apiTime.getAndAdd(System.currentTimeMillis() - staStart); // TODO 统计API时间

                // 如果返回结果异常直接跳出整个方法，并且抛出异常，便于上层的调用者结束该事务
                if (!"10000".equals(result.getInfocode())) {
                    cdl.countDown();
                    throw new GlobalException("获取路线结果异常，停止保存");
                }

                // 如果路线长度大于半径长度的2倍直接排除这个加油站的影响
                if (paths.getDistance() / 2 > radius) {
                    // 结束异步任务的时候，减少计数
                    cdl.countDown();
                    return;
                }

                // 设置基本数据
                GasStationGeo geo = new GasStationGeo();
                geo.setSystemStationId(systemStation.getId());
                geo.setOutSystemStationId(out.getId());
                geo.setDistance(paths.getDistance() / 1000.0);
                Cost cost = paths.getCost();

                // 计算红绿灯影响因子
                geo.setTrafficLights(cost.getTraffic_lights());
                Double trafficFactor = trafficLightsAlgorithm(geo.getTrafficLights());
                geo.setTrafficFactor(trafficFactor);

                // 计算方向因子
                StringBuilder routeDirection = new StringBuilder();
                List<Step> steps = paths.getSteps();
                String prevDirection = "";
                for (int i = 0; i < steps.size(); i++) {
                    String currentDirection = steps.get(i).getOrientation();
                    if (i == 0) {
                        routeDirection.append(currentDirection);
                    } else if (!prevDirection.equals(currentDirection)) {
                        routeDirection.append(",").append(steps.get(i).getOrientation());
                    }
                    prevDirection = currentDirection;
                }
                geo.setRouteShape(routeDirection.toString());
                Double routeShapeFactor =
                        routeShapeFactorAlgorithm(routeDirection.toString().split(","));
                geo.setRouteShapeFactor(routeShapeFactor);

                // 计算路线影响因子
                Double routerFactor = routeFactorAlgorithm(trafficFactor, routeShapeFactor);
                geo.setRouteFactor(routerFactor);

                // 保存不同类型的数据
                staStart = System.currentTimeMillis(); // TODO 统计数据库查询时间
                GasStationGeo geoEntity =
                        gasStationGeoMapper.selectGasStationGeoByStationId(systemStation.getId(), out.getId());
                dbQueryTime.getAndAdd(System.currentTimeMillis() - staStart); // TODO 统计数据库查询时间
                if (geoEntity == null) {
                    saveList.add(geo);
                } else {
                    geo.setId(geoEntity.getId());
                    updateList.add(geo);
                }

                // CountDownLatch计算
                cdl.countDown();
            }, executor);
        }

        // 所有查询结束后，保存数据结果
        try {
            cdl.await();
            long start = System.currentTimeMillis();
            saveBatch(saveList);
            updateBatchById(updateList);
            log.debug("[GEO-Save-Update] API Consume time for " + apiTime.get() + "ms");
            log.debug("[GEO-Save-Update] Query Consume time for " + dbQueryTime.get() + "ms");
            log.info("[GEO-Save-Update] Consume time for " + (System.currentTimeMillis() - start) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量保存列表数据
     *
     * @param saveList 地理信息列表
     */
    public void saveBatch(List<GasStationGeo> saveList) {
        saveList.forEach(this::insertGasStationGeo);
    }

    /**
     * 批量按照ID更新列表数据
     *
     * @param updateList 地理信息列表
     */
    public void updateBatchById(List<GasStationGeo> updateList) {
        updateList.forEach(this::updateGasStationGeo);
    }

    @Override
    public List<GasStationGeo> selectGasStationGeoList(GasStationGeo gasStationGeo) {
        return gasStationGeoMapper.selectGasStationGeoList(gasStationGeo);
    }

    /**
     * 新增加油站地理信息
     * 
     * @param gasStationGeo 加油站地理信息
     * @return 结果
     */
    @Override
    public int insertGasStationGeo(GasStationGeo gasStationGeo)
    {
        return gasStationGeoMapper.insertGasStationGeo(gasStationGeo);
    }

    /**
     * 修改加油站地理信息
     * 
     * @param gasStationGeo 加油站地理信息
     * @return 结果
     */
    @Override
    public int updateGasStationGeo(GasStationGeo gasStationGeo)
    {
        return gasStationGeoMapper.updateGasStationGeo(gasStationGeo);
    }
}
