package com.ruoyi.gas.module.map.baidu;

import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.gas.module.map.MapService;
import com.ruoyi.gas.module.map.baidu.model.*;
import com.ruoyi.gas.module.map.baidu.model.driving.Routes;
import com.ruoyi.gas.module.map.baidu.model.driving.Steps;
import com.ruoyi.gas.module.map.baidu.model.place.Detail_info;
import com.ruoyi.gas.module.map.baidu.model.place.Results;
import com.ruoyi.gas.module.map.model.PathCost;
import com.ruoyi.gas.module.map.model.PlaceInfo;
import com.ruoyi.system.service.ISysDictTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaiduMapService implements MapService {
    public static final String REGION_CODE_DICT = "region_adcode";
    public static final String TRANSPORT_FACILITY_TAG = "交通设施";
    public static final String GAS_STATION_TAG = "加油加气站";

    private static final Logger log = LoggerFactory.getLogger(MapService.class);
    private final BaiduClient client;
    private final ISysDictTypeService dictTypeService;

    @Override
    public List<PlaceInfo> listPlaceByAdcodeAndKeyword(String adcode, String keywords) {
        String region = getRegionFromAdcode(adcode);
        RegionSearchRequest request = new RegionSearchRequest();
        request.setQuery(keywords);
        request.setRegion(region);
        PlaceSearchResult regionSearchResult = client.regionSearchRequest(request);
        return constructPlaceInfoList(regionSearchResult.getResults());
    }

    @Override
    public List<PlaceInfo> listPlaceAroundOrigin(String location, Integer radius) {
        AroundSearchRequest request = new AroundSearchRequest();
        request.setRadius(radius);
        request.setLocation(location);
        PlaceSearchResult placeSearchResult = client.aroundSearchRequest(request);

        return constructPlaceInfoList(placeSearchResult.getResults());
    }

    @Override
    public PathCost getPathCost(String origin, String destination) {
        DrivingRequest request = new DrivingRequest();
        request.setOrigin(origin);
        request.setDestination(destination);
        DrivingResult result = client.drivingRequest(request);
        Routes routes = result.getResult().getRoutes().get(0);

        PathCost pathCost = new PathCost();
        pathCost.setDistance(routes.getDistance());
        pathCost.setTrafficLight(routes.getTraffic_light());
        String[] pathDirection = getPathDirection(routes.getSteps());
        pathCost.setRouteDirection(pathDirection);
        return pathCost;
    }

    /**
     * 根据地区的行政区划代码获取地区信息
     * <p>因为缓存很有可能失效，失效后系统无法正常运行。
     * 所以需要在获取缓存前，检测缓存是否存在</p>
     *
     * @param adcode 行政区划代码
     * @return 行政地区名
     */
    private String getRegionFromAdcode(String adcode) {
        try {
            return DictUtils.getDictLabel(REGION_CODE_DICT, adcode);
        } catch (Exception e) {
            dictTypeService.loadingDictCache();
            return DictUtils.getDictLabel(REGION_CODE_DICT, adcode);
        }
    }

    /**
     * 根据查询结果构造地区信息
     *
     * @param results 查询结果集
     * @return 地区信息
     */
    private List<PlaceInfo> constructPlaceInfoList(List<Results> results) {
        List<PlaceInfo> list = new ArrayList<>();
        for (Results result : results) {
            Detail_info detail_info = result.getDetail_info();

            PlaceInfo placeInfo = new PlaceInfo();
            placeInfo.setPname(result.getProvince());
            placeInfo.setCityname(result.getCity());
            placeInfo.setAdname(result.getArea());
            placeInfo.setAddress(result.getAddress());
            placeInfo.setName(result.getName());
            placeInfo.setLocation(result.getLocation().toString());
            placeInfo.setDirectDistance((long) detail_info.getDistance());

            if (isGasStation(detail_info.getTag())) {
                list.add(placeInfo);
            }
        }

        return list;
    }

    /**
     * 根据标签判断是否是加油站
     * @param tag 标签
     * @return 是否是加油站
     */
    private boolean isGasStation(String tag) {
        return tag.contains(TRANSPORT_FACILITY_TAG) && tag.contains(GAS_STATION_TAG);
    }

    /**
     * 根据高德地图返回的路线参数转换为方向变化数组
     * <p>例如，路线方向为，[东，东，西，西北]，最后得到的结果应该为，[东，西，西北]</p>
     *
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
            String currentDirection = getMapServiceDirection(step.getDirection());
            if ("".equals(prevDirection)) {
                routeDirection.add(currentDirection);
            } else if (!prevDirection.equals(currentDirection)) {
                routeDirection.add(currentDirection);
            }
            prevDirection = currentDirection;
        }
        return routeDirection.toArray(new String[0]);
    }

    /**
     * 将百度地图的方向规则转换为系统已有的方向规则
     * 0：北
     * 1,2：东北
     * 3：东
     * 4,5：东南
     * 6：南
     * 7,8：西南
     * 9：西
     * 10,11：西北
     *
     * @param direction 方向代码
     * @return 方向
     */
    private String getMapServiceDirection(int direction) {
        if (direction == 0) {
            return "北";
        } else if (direction == 1 || direction == 2) {
            return "东北";
        } else if (direction == 3) {
            return "东";
        } else if (direction == 4 || direction == 5) {
            return "东南";
        } else if (direction == 6) {
            return "南";
        } else if (direction == 7 || direction == 8) {
            return "西南";
        } else if (direction == 9) {
            return "西";
        } else {
            return "西北";
        }
    }

    public BaiduMapService(BaiduClient client, ISysDictTypeService dictTypeService) {
        this.client = client;
        this.dictTypeService = dictTypeService;
    }
}
