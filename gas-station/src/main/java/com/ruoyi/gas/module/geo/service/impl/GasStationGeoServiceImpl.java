package com.ruoyi.gas.module.geo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import com.ruoyi.gas.constant.GeoConstant;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.mapper.GasStationGeoMapper;
import com.ruoyi.gas.module.map.MapService;
import com.ruoyi.gas.module.map.model.PathCost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GasStationGeoServiceImpl implements IGasStationGeoService {
    private final Logger log = LoggerFactory.getLogger(IGasStationGeoService.class);

    @Autowired
    private GasStationGeoMapper gasStationGeoMapper;

    @Autowired
    private MapService mapService;

    @Resource(name = "gasStationExecutor")
    private Executor executor;

    @Override
    public List<GasStationGeo> listStationDistance(GasStationInfo systemStation,
                                                   List<GasStationInfo> outSystemStationList, Integer distance) {
        List<GasStationGeo> systemStationGeos =
                gasStationGeoMapper.selectGasStationGeoBySystemStationIdAndDistance(systemStation.getId(), distance / 1000.0);
        // 如果内容是空的，那么就强制更新地理信息内容
        if (systemStationGeos == null || systemStationGeos.size() == 0) {
            return listStationDistance(systemStation, outSystemStationList, distance, true);
        }
        return systemStationGeos;
    }

    /**
     * 列出所有关于系统内加油站周围的加油站的距离信息
     * <p>参数calculate表示数据库是否需要强制计算，并更新加油站的距离信息数据。<br/>
     * 如果true，那么强制更新；如果为false，不强制更新数据</p>
     * @param systemStation 系统内加油站
     * @param outSystemStationList 系统外加油站列表
     * @param newest 是否需要重新计算保存
     * @return 加油站距离列表
     */
    @Override
    public List<GasStationGeo> listStationDistance(GasStationInfo systemStation,
                                                   List<GasStationInfo> outSystemStationList,
                                                   Integer distance,
                                                   boolean newest) {
        if (outSystemStationList == null || outSystemStationList.size() == 0) {
            return new ArrayList<>();
        }

        if (!newest) {
            List<GasStationGeo> gasStationGeos =
                    gasStationGeoMapper.selectGasStationGeoBySystemStationIdAndDistance(systemStation.getId(), distance / 1000.0);
            if (gasStationGeos != null) {
                return gasStationGeos;
            }
        }

        // 更新并返回最新结果
        updateStationGeo(systemStation, outSystemStationList);
        return gasStationGeoMapper.selectGasStationGeoBySystemStationIdAndDistance(systemStation.getId(), distance / 1000.0);
    }

    /**
     * 更新站点之间的路径代价信息
     * @param systemStation 系统内加油站
     * @param outSystemStationList 系统外加油站列表
     * @return 地理信息列表
     */
    private void updateStationGeo(GasStationInfo systemStation, List<GasStationInfo> outSystemStationList) {
        CountDownLatch countDownLatch = new CountDownLatch(outSystemStationList.size());
        for (GasStationInfo outSystemStation : outSystemStationList) {
            CompletableFuture.runAsync(() -> {
                try {
                    PathCost pathCost = mapService.getPathCost(systemStation.getLocation(), outSystemStation.getLocation());
                    String systemStationId = systemStation.getId();
                    String outSystemStationId = outSystemStation.getId();
                    GasStationGeo geo =
                            convertToGasStationGeo(systemStationId, outSystemStationId, pathCost);

                    // 如果加油站不符合条件，那么去除加油站
                    if (geo.getDistance() > GeoConstant.DEFAULT_DISTANCE / 1000.0) {
                        gasStationGeoMapper.deleteGasStationGeo(systemStationId, geo.getOutSystemStationId());
                    } else {
                        gasStationGeoMapper.updateGasStationGeo(geo);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, executor);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // Do Nothing
        }
    }

    /**
     * 将路径代价转换为加油站地理信息实体类
     * <p>设置相关参数并且计算相关参数</p>
     *
     * @param systemStationId    系统内ID
     * @param outSystemStationId 系统外ID
     * @param pathCost           路径代价
     * @return 加油站地理信息实体类
     */
    private GasStationGeo convertToGasStationGeo(String systemStationId, String outSystemStationId, PathCost pathCost) {
        GasStationGeo geo = new GasStationGeo();
        geo.setSystemStationId(systemStationId);
        geo.setOutSystemStationId(outSystemStationId);

        geo.setDistance(pathCost.getDistance() / 1000.0);
        geo.setTrafficLights(pathCost.getTrafficLight());
        geo.setRouteShape(String.join(",", pathCost.getRouteDirection()));

        Double trafficFactor = trafficLightsAlgorithm(pathCost.getTrafficLight());
        geo.setTrafficFactor(trafficFactor);
        Double routeShapeFactor = routeShapeFactorAlgorithm(pathCost.getRouteDirection());
        geo.setRouteShapeFactor(routeShapeFactor);
        Double routeFactor = routeFactorAlgorithm(trafficFactor, routeShapeFactor);
        geo.setRouteFactor(routeFactor);
        return geo;
    }

}
