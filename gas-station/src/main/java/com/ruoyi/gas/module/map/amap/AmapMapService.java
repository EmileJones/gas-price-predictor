package com.ruoyi.gas.module.map.amap;

import com.ruoyi.gas.module.map.MapService;
import com.ruoyi.gas.module.map.amap.model.*;
import com.ruoyi.gas.module.map.amap.model.driving.Paths;
import com.ruoyi.gas.module.map.amap.model.driving.Step;
import com.ruoyi.gas.module.map.amap.model.place.POI;
import com.ruoyi.gas.module.map.model.PathCost;
import com.ruoyi.gas.module.map.model.PlaceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmapMapService implements MapService {

    /** Log的类应该是 {@link MapService} 而不是自己的实现类 */
    private static final Logger log = LoggerFactory.getLogger(MapService.class);
    /** 高德地图API封装的客户端程序 */
    private final AmapClient amapClient;

    /**
     * 根据行政代码和关键字搜索该地区加油站地点信息
     * <p>这个方法主要用于搜索某个地区内带有关键字的地点，例如搜索
     * 山西省，太原市，小店区的关键词带有“长城”的加油站信息。那么应
     * 该传入参数，adcode: 140105, keywords: 长城。然后就可
     * 以相关结果。</p>
     * @param adcode 中国行政区划代码（可以精确到县/区）
     * @param keywords 搜索地点的关键字
     * @return 返回地点信息
     */
    @Override
    public List<PlaceInfo> listPlaceByAdcodeAndKeyword(String adcode, String keywords) {
        PlaceTextRequest request = new PlaceTextRequest();
        request.setCity(adcode);
        request.setKeywords(keywords);
        PlaceAroundResult placeAroundResult = amapClient.placeTextRequest(request);
        List<PlaceInfo> placeInfoList = getPlaceInfoListFromPOIs(placeAroundResult.getPois());
        log.info("高德地图-关键字搜索参数：adcode: {}, keywords: {}, 搜索到结果{}条",
                adcode, keywords, placeInfoList.size());
        return placeInfoList;
    }

    /**
     * 搜索某个地点周围指定半径以内的地点
     * <p>搜索指定地点周围半径的地点，传入经纬度信息，搜索的半径，
     * 即可得到地点周围的地区列表。例如，搜索 “长城加油站(平阳南路)” 半径为3.5千米范围内
     * 的所有加油站地点。那就应该传入参数，location: 112.551509,37.790016,
     * radius: 3500，然后就可以获得周围地区信息列表</p>
     * @param location 指定位置的经纬度
     * @param radius 搜索半径（米）
     * @return 返回地点信息列表
     */
    @Override
    public List<PlaceInfo> listPlaceAroundOrigin(String location, Integer radius) {
        PlaceAroundRequest aroundRequest = new PlaceAroundRequest();
        aroundRequest.setLocation(location);
        aroundRequest.setTypes("010100");
        aroundRequest.setRadius(radius);
        PlaceAroundResult placeAroundResult = amapClient.placeAroundRequest(aroundRequest);
        List<PlaceInfo> placeInfoList = getPlaceInfoListFromPOIs(placeAroundResult.getPois());
        log.info("高德地图-周边搜索参数：location: {}, radius: {}m, 搜索到结果{}条",
                location, radius, placeInfoList.size());
        return placeInfoList;
    }

    /**
     * 搜索两个地点路线的代价信息
     * <p>传入起点的经纬度，传入终点的经纬度，即可返回路线代价的信息。</p>
     * @param origin 起点位置的经纬度
     * @param destination 重点位置的经纬度
     * @return 两地点之间的路线代价信息
     */
    @Override
    public PathCost getPathCost(String origin, String destination) {
        PathCost pathCost = new PathCost();
        DrivingDirectionRequest request = new DrivingDirectionRequest();
        DrivingDirectionResult result = amapClient.drivingDirectionRequest(request);
        Paths path = result.getRoute().getPaths().get(0);

        pathCost.setDistance(path.getDistance());
        pathCost.setTrafficLight(path.getCost().getTraffic_lights());

        // 方向变化生成
        String[] pathDirection = getPathDirection(path.getSteps());
        pathCost.setRouteDirection(pathDirection);

        return pathCost;
    }

    /**
     * 根据高德地图返回的路线参数转换为方向变化数组
     * <p>例如，路线方向为，[东，东，西，西北]，最后得到的结果应该为，[东，西，西北]</p>
     * @param steps 路线每一步信息
     * @return 返回
     */
    private String[] getPathDirection(List<Step> steps) {
        List<String> routeDirection = new ArrayList<>();
        String prevDirection = "";
        for (int i = 0; i < steps.size(); i++) {
            // 路线小于30米，去除此段路线的计算
            if (steps.get(i).getStep_distance() < 30) {
                continue;
            }
            String currentDirection = steps.get(i).getOrientation();
            if (i == 0) {
                routeDirection.add(currentDirection);
            } else if (!prevDirection.equals(currentDirection)) {
                routeDirection.add(steps.get(i).getOrientation());
            }
            prevDirection = currentDirection;
        }
        return routeDirection.toArray(new String[0]);
    }

    /**
     * 将高德地图的POI参数转换为Map模块的{@link PlaceInfo}
     * @param pois 高德POI结果列表
     * @return Map模块 PlaceInfo 列表
     */
    private List<PlaceInfo> getPlaceInfoListFromPOIs(List<POI> pois) {
        if (pois == null || pois.size() == 0) { return new ArrayList<>(); }
        return pois.stream()
                .map(poi -> {
                    PlaceInfo placeInfo = new PlaceInfo();
                    placeInfo.setLocation(poi.getLocation());
                    placeInfo.setAddress(poi.getAddress());
                    placeInfo.setAdname(poi.getAdname());
                    placeInfo.setCityname(poi.getCityname());
                    placeInfo.setName(poi.getName());
                    placeInfo.setPname(poi.getPname());
                    return placeInfo;
                }).collect(Collectors.toList());
    }

    public AmapMapService(AmapClient amapClient) {
        this.amapClient = amapClient;
    }
}
