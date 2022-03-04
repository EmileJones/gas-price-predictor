package com.ruoyi.gas.module.geo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

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
                                                   List<GasStationInfo> outSystemStationList) {
        List<GasStationGeo> systemStationGeos =
                gasStationGeoMapper.selectGasStationGeoBySystemStationId(systemStation.getId());
        if (systemStationGeos == null) {
            return listStationDistance(systemStation, outSystemStationList, true);
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
                                                   boolean newest) {
        if (outSystemStationList == null || outSystemStationList.size() == 0) {
            return new ArrayList<>();
        }

        if (!newest) {
            List<GasStationGeo> gasStationGeos =
                    gasStationGeoMapper.selectGasStationGeoBySystemStationId(systemStation.getId());
            if (gasStationGeos != null) {
                return gasStationGeos;
            }
        }

        return updateStationGeo(systemStation, outSystemStationList);
    }

    /**
     * 更新站点之间的路径代价信息
     * @param systemStation 系统内加油站
     * @param outSystemStationList 系统外加油站列表
     * @return 地理信息列表
     */
    private List<GasStationGeo> updateStationGeo(GasStationInfo systemStation, List<GasStationInfo> outSystemStationList) {
        List<GasStationGeo> result = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(outSystemStationList.size());
        for (GasStationInfo outSystemStation : outSystemStationList) {
            CompletableFuture.runAsync(() -> {
                PathCost pathCost = mapService.getPathCost(systemStation.getLocation(), outSystemStation.getLocation());
                String systemStationId = systemStation.getId();
                String outSystemStationId = outSystemStation.getId();
                GasStationGeo geo =
                        convertToGasStationGeo(systemStationId, outSystemStationId, pathCost);
                gasStationGeoMapper.updateGasStationGeo(geo);
                result.add(geo);
                countDownLatch.countDown();
            }, executor);
        }
        return result;
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
        Double routeShapFactor = routeShapeFactorAlgorithm(pathCost.getRouteDirection());
        geo.setRouteShapeFactor(routeShapFactor);
        Double routeFactor = routeFactorAlgorithm(trafficFactor, routeShapFactor);
        geo.setRouteFactor(routeFactor);
        return geo;
    }

}
