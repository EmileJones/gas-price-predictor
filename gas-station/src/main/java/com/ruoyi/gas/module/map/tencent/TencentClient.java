package com.ruoyi.gas.module.map.tencent;

import com.alibaba.fastjson.JSON;
import com.ruoyi.gas.module.map.tencent.constant.LbsUrl;
import com.ruoyi.gas.module.map.tencent.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * 腾讯地图客户端
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
@Repository
public class TencentClient {

    private static final Logger log = LoggerFactory.getLogger(TencentClient.class);
    @Value("${gas.tencent.key}")
    private String key;

    /**
     * 周边搜索 -- 搜索半径内的目标
     * <p>request重要参数为：
     * <ul>
     * <li>key（已在配置文件中配置）: 腾讯用户应用Key</li>
     * <li>location: 搜索目标的中心经纬度</li>
     * <li>types：搜索目标的类型，默认为 010100（加油站）</li>
     * <li>keywords：搜索目标的关键字</li>
     * </ul>
     * </p>
     */
    public SearchResult nearbySearch(NearbySearchRequest request) {
        request.setKey(key);
        return this.doGetRequest(LbsUrl.SEARCH, request, SearchResult.class);
    }

    /**
     * 区域搜索 -- 搜索城市范围内的目标
     * <p>request重要参数为：
     * <ul>
     * <li>key（已在配置文件中配置）: 腾讯用户应用Key</li>
     * <li>city: 搜索目标的城市adcode</li>
     * <li>keywords：搜索目标的关键字</li>
     * </ul>
     * </p>
     */
    public SearchResult regionSearch(RegionSearchRequest request) {
        request.setKey(key);
        return this.doGetRequest(LbsUrl.SEARCH, request, SearchResult.class);
    }

    /**
     * 路径规划
     * <p>request重要参数为：
     * <ul>
     * <li>key（已在配置文件中配置）: 腾讯用户应用Key</li>
     * <li>origin: 起点的经纬度</li>
     * <li>destination: 终点的经纬度</li>
     * </ul>
     * </p>
     */
    public DrivingResult driving(DrivingRequest request) {
        request.setKey(key);
        return this.doGetRequest(LbsUrl.DRIVING, request, DrivingResult.class);
    }

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
