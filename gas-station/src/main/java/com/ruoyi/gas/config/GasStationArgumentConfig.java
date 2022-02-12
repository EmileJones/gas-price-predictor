package com.ruoyi.gas.config;

import com.alibaba.fastjson.JSON;
import com.ruoyi.gas.domain.GasStationArgument;
import com.ruoyi.gas.service.IGasStationArgumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;

import static com.ruoyi.gas.constant.ArgumentConstant.ARGUMENT_PREFIX;
import static com.ruoyi.gas.constant.ArgumentConstant.DIRECTION_MATRIX;

/**
 * 加油站参数配置
 * @author KlenKiven
 */
@Configuration
public class GasStationArgumentConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private IGasStationArgumentService argumentService;

    @PostConstruct
    public void init() {
        // 方向权值参数：加载
        GasStationArgument value = argumentService.selectGasStationArgumentByName(DIRECTION_MATRIX);
        redisTemplate.opsForValue().set(ARGUMENT_PREFIX + DIRECTION_MATRIX, JSON.toJSONString(value));
    }

}
