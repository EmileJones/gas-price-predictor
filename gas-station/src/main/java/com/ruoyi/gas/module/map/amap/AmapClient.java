package com.ruoyi.gas.module.map.amap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ruoyi.gas.module.map.amap.constant.AmapConstant;
import com.ruoyi.gas.module.map.amap.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 高德地图API封装客户端
 * <p>下面的API已经全部自动根据配置文件注入amapKey参数。</p>
 * @author klenkiven
 */
public class AmapClient {

    private static final Logger log = LoggerFactory.getLogger(AmapClient.class);
    @Value("${gas.amap.key}")
    private String amapKey;

    /**
     * 搜索POI - 周边搜索
     * <p>request重要参数为：
     * <ul>
     * <li>key（已在配置文件中配置）: 高德用户账号唯一key</li>
     * <li>location: 搜索目标的中心经纬度</li>
     * <li>types：搜索目标的类型，默认为 010100（加油站）</li>
     * <li>keywords：搜索目标的关键字</li>
     * </ul>
     * </p>
     */
    public PlaceAroundResult placeAroundRequest(PlaceAroundRequest request) {
        request.setKey(amapKey);
        return this.doGetRequest(AmapConstant.PLACE_AROUND, request, PlaceAroundResult.class);
    }

    /**
     * 搜索POI - 关键字搜索
     * <p>request重要参数为：
     * <ul>
     * <li>key（已在配置文件中配置）: 高德用户账号唯一key</li>
     * <li>city: 搜索目标的城市acode</li>
     * <li>types：搜索目标的类型，默认为 010100（加油站）</li>
     * <li>keywords：搜索目标的关键字</li>
     * </ul>
     * </p>
     */
    public PlaceAroundResult placeTextRequest(PlaceTextRequest request) {
        request.setKey(amapKey);
        return this.doGetRequest(AmapConstant.PLACE_TEXT, request, PlaceAroundResult.class);
    }

    /**
     * 路径规划 2.0
     * <p>request重要参数为：
     * <ul>
     * <li>key（已在配置文件中配置）: 高德用户账号唯一key</li>
     * <li>origin: 起点的经纬度</li>
     * <li>destination: 终点的经纬度</li>
     * <li>show_fields：搜索参数，默认只搜索 cost</li>
     * </ul>
     * </p>
     */
    public DrivingDirectionResult drivingDirectionRequest(DrivingDirectionRequest request) {
        request.setKey(amapKey);
        return this.doGetRequest(AmapConstant.DRIVING_DIRECTION, request, DrivingDirectionResult.class);
    }

    /**
     * 执行HTTP请求GET方法
     */
    private <T, R> R doGetRequest(String url, T requestParam, Class<R> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> entity = new HttpEntity<>(requestParam);
        log.debug("[HttpRequestUtils - DEBUG] " + url + requestParam);
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
