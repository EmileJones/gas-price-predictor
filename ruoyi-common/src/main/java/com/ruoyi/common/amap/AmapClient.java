package com.ruoyi.common.amap;

import com.ruoyi.common.amap.constant.AmapConstant;
import com.ruoyi.common.amap.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * 高德地图API封装客户端
 *
 * @author klenkiven
 */
@Repository
public class AmapClient {

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
        System.out.println("[HttpRequestUtils - DEBUG] " + url + requestParam);
        ResponseEntity<R> exchange =
                restTemplate.exchange(url + requestParam, HttpMethod.GET, entity, responseType);
        return exchange.getBody();
    }
}
