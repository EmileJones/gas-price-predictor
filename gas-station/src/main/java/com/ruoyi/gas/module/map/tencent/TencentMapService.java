package com.ruoyi.gas.module.map.tencent;

import cn.hutool.core.util.URLUtil;
import com.ruoyi.gas.module.map.MapService;
import com.ruoyi.gas.module.map.model.PathCost;
import com.ruoyi.gas.module.map.model.PlaceInfo;
import com.ruoyi.gas.module.map.tencent.model.*;
import com.ruoyi.gas.module.map.tencent.model.driving.Routes;
import com.ruoyi.gas.module.map.tencent.model.driving.Steps;
import com.ruoyi.gas.module.map.tencent.model.search.Ad_info;
import com.ruoyi.gas.module.map.tencent.model.search.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TencentMapService implements MapService {
    private final Logger log = LoggerFactory.getLogger(MapService.class);
    private final TencentClient client;

    @Override
    public List<PlaceInfo> listPlaceByAdcodeAndKeyword(String adcode, String keywords) {
        keywords = URLUtil.encode(keywords, "UTF-8");
        RegionSearchRequest request = new RegionSearchRequest();
        request.setAdcode(adcode);
        request.setKeyword(keywords);
        SearchResult searchResult = client.regionSearch(request);
        List<Data> resultData = searchResult.getData();
        return constructPlaceInfoFromData(resultData);
    }

    @Override
    public List<PlaceInfo> listPlaceAroundOrigin(String location, Integer radius) {
        NearbySearchRequest request = new NearbySearchRequest();
        request.setLocation(location);
        request.setRadius(radius);
        SearchResult searchResult = client.nearbySearch(request);
        List<Data> resultData = searchResult.getData();
        return constructPlaceInfoFromData(resultData);
    }

    @Override
    public PathCost getPathCost(String origin, String destination) {
        DrivingRequest request = new DrivingRequest();
        request.setFrom(origin);
        request.setTo(destination);
        DrivingResult driving = client.driving(request);
        Routes routes = driving.getResult().getRoutes().get(0);

        PathCost pathCost = new PathCost();
        pathCost.setDistance((int) routes.getDistance());
        pathCost.setTrafficLight(routes.getTraffic_light_count());

        String[] pathDirection = getPathDirection(routes.getSteps());
        pathCost.setRouteDirection(pathDirection);
        return pathCost;
    }

    private List<PlaceInfo> constructPlaceInfoFromData(List<Data> resultData) {
        List<PlaceInfo> list = new ArrayList<>();
        if (resultData == null) {
            log.error("搜索结果可能发生错误");
            return list;
        }
        
        for (Data data : resultData) {
            PlaceInfo placeInfo = new PlaceInfo();
            placeInfo.setName(data.getTitle());
            placeInfo.setLocation(data.getLocation().toString());
            placeInfo.setDirectDistance(Math.round(data.get_distance()));

            placeInfo.setAddress(data.getAddress());
            Ad_info ad_info = data.getAd_info();
            placeInfo.setPname(ad_info.getProvince());
            placeInfo.setCityname(ad_info.getCity());
            placeInfo.setAdname(ad_info.getDistrict());

            list.add(placeInfo);
        }
        return list;
    }

    /**
     * 根据高德地图返回的路线参数转换为方向变化数组
     * <p>例如，路线方向为，[东，东，西，西北]，最后得到的结果应该为，[东，西，西北]</p>
     * @param steps 路线每一步信息
     * @return 返回
     */
    private String[] getPathDirection(List<Steps> steps) {
        List<String> routeDirection = new ArrayList<>();
        String prevDirection = "";
        for (Steps step : steps) {
            // 路线小于30米，去除此段路线的计算
            if (step.getRoad_name() == null || step.getDistance() < 30) {
                continue;
            }
            String currentDirection = step.getDir_desc();
            if ("".equals(prevDirection)) {
                routeDirection.add(currentDirection);
            } else if (!prevDirection.equals(currentDirection)) {
                routeDirection.add(step.getDir_desc());
            }
            prevDirection = currentDirection;
        }
        return routeDirection.toArray(new String[0]);
    }

    public TencentMapService(TencentClient client) {
        this.client = client;
    }
}
