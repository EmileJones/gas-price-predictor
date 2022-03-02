package com.ruoyi.common.amap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP 请求工具类
 * @author KlenKiven
 */
public class HttpRequestUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static <T, R> R doGetRequest(String url, T requestParam, Class<R> responseType) {
        HttpEntity<T> entity = new HttpEntity<>(requestParam);
        System.out.println("[HttpRequestUtils - DEBUG] " + url + requestParam);
        ResponseEntity<R> exchange =
                restTemplate.exchange(url + requestParam, HttpMethod.GET, entity, responseType);
        return exchange.getBody();
    }

}
