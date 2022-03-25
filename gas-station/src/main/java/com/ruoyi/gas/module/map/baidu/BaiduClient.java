package com.ruoyi.gas.module.map.baidu;

import com.alibaba.fastjson.JSON;
import com.ruoyi.gas.module.map.baidu.constant.BaiduMapConstant;
import com.ruoyi.gas.module.map.baidu.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * 封装的百度地图客户端
 * @author KlenKiven
 */
@Repository
public class BaiduClient {

    @Value("${gas.baidu.ak}")
    private String baiduAk;

    /**
     * 路径规划
     * <p>request重要参数为：
     * <ul>
     * <li>ak（已在配置文件中配置）: 百度地图访问AK</li>
     * <li>origin: 起点的经纬度</li>
     * <li>destination: 终点的经纬度</li>
     * </ul>
     * </p>
     */
    public DrivingResult drivingRequest(DrivingRequest request) {
        request.setAk(baiduAk);
        return this.doGetRequest(BaiduMapConstant.DRIVING_DIRECTION, request, DrivingResult.class);
    }

    /**
     * 地点检索 -- 行政区域关键字检索
     * <p>request重要参数为：
     * <ul>
     * <li>ak（已在配置文件中配置）: 百度地图访问AK</li>
     * <li>region: 搜索目标的城市acode</li>
     * <li>tag：搜索目标的类型，默认为加油站</li>
     * <li>query：搜索目标的关键字</li>
     * </ul>
     * </p>
     */
    public PlaceSearchResult regionSearchRequest(RegionSearchRequest request) {
        request.setAk(baiduAk);
        return this.doGetRequest(BaiduMapConstant.SEARCH_PLACE, request, PlaceSearchResult.class);
    }

    /**
     * 地点检索 -- 周边目标检索
     * <p>request重要参数为：
     * <ul>
     * <li>ak（已在配置文件中配置）: 百度地图访问AK</li>
     * <li>location: 目标位置的经纬度</li>
     * <li>tag：搜索目标的类型，默认为加油站</li>
     * <li>radius：搜索的半径</li>
     * </ul>
     * </p>
     */
    public PlaceSearchResult aroundSearchRequest(AroundSearchRequest request) {
        request.setAk(baiduAk);
        return this.doGetRequest(BaiduMapConstant.SEARCH_PLACE, request, PlaceSearchResult.class);
    }

    /**
     * 执行HTTP请求GET方法
     */
    private <T, R> R doGetRequest(String url, T requestParam, Class<R> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> entity = new HttpEntity<>(requestParam);
        System.out.println("[HttpRequestUtils - DEBUG] " + url + requestParam);
        ResponseEntity<String> exchange =
                restTemplate.exchange(url + requestParam, HttpMethod.GET, entity, String.class);
        // 下面的操作是用来替换高德地图空数据使用 '[]' 来表示，导致数据出现问题。例如，
        // address: []  ==处理==>  address: ""
        // 直接将数据替换为空字符串就可以解决。
        if (exchange.getBody() == null) { return null; }
        String json = exchange.getBody().replace("[]", "\"\"");
        return JSON.parseObject(json, responseType);
    }

}
