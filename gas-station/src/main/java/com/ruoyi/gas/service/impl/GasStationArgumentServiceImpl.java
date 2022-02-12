package com.ruoyi.gas.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.gas.constant.ArgumentConstant;
import com.ruoyi.gas.mapper.GasStationGeoMapper;
import com.ruoyi.gas.mapper.GasStationInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.ruoyi.gas.mapper.GasStationArgumentMapper;
import com.ruoyi.gas.domain.GasStationArgument;
import com.ruoyi.gas.service.IGasStationArgumentService;

import static com.ruoyi.gas.constant.ArgumentConstant.ARGUMENT_PREFIX;
import static com.ruoyi.gas.constant.ArgumentConstant.DIRECTION_MATRIX;

/**
 * 加油站关键参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-05
 */
@Service
public class GasStationArgumentServiceImpl implements IGasStationArgumentService 
{
    @Autowired
    private GasStationArgumentMapper gasStationArgumentMapper;
    @Autowired
    private GasStationInfoMapper gasStationInfoMapper;
    @Autowired
    private GasStationGeoMapper gasStationGeoMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询加油站关键参数
     * 
     * @param id 加油站关键参数主键
     * @return 加油站关键参数
     */
    @Override
    public GasStationArgument selectGasStationArgumentById(Long id)
    {
        return gasStationArgumentMapper.selectGasStationArgumentById(id);
    }

    /**
     * 查询加油站关键参数列表
     * 
     * @param gasStationArgument 加油站关键参数
     * @return 加油站关键参数
     */
    @Override
    public List<GasStationArgument> selectGasStationArgumentList(GasStationArgument gasStationArgument)
    {
        return gasStationArgumentMapper.selectGasStationArgumentList(gasStationArgument);
    }

    /**
     * 新增加油站关键参数
     * 
     * @param gasStationArgument 加油站关键参数
     * @return 结果
     */
    @Override
    public int insertGasStationArgument(GasStationArgument gasStationArgument)
    {
        return gasStationArgumentMapper.insertGasStationArgument(gasStationArgument);
    }

    /**
     * 修改加油站关键参数
     * 
     * @param gasStationArgument 加油站关键参数
     * @return 结果
     */
    @Override
    public int updateGasStationArgument(GasStationArgument gasStationArgument)
    {
        // 更新Redis中的值
        redisTemplate.opsForValue().set(ArgumentConstant.ARGUMENT_PREFIX + gasStationArgument.getName(),
                JSON.toJSONString(gasStationArgument));
        // 清除数据库所有地理数据
        gasStationGeoMapper.truncateTable();
        // 清除数据库所有滴露信息数据
        gasStationInfoMapper.truncateTable();
        return gasStationArgumentMapper.updateGasStationArgument(gasStationArgument);
    }

    /**
     * 批量删除加油站关键参数
     * 
     * @param ids 需要删除的加油站关键参数主键
     * @return 结果
     */
    @Override
    public int deleteGasStationArgumentByIds(Long[] ids)
    {
        return gasStationArgumentMapper.deleteGasStationArgumentByIds(ids);
    }

    /**
     * 删除加油站关键参数信息
     * 
     * @param id 加油站关键参数主键
     * @return 结果
     */
    @Override
    public int deleteGasStationArgumentById(Long id)
    {
        return gasStationArgumentMapper.deleteGasStationArgumentById(id);
    }

    @Override
    public GasStationArgument selectGasStationArgumentByName(String name) {
        String json = redisTemplate.opsForValue().get(ARGUMENT_PREFIX + DIRECTION_MATRIX);
        if (!StringUtils.isEmpty(json)) {
            return JSON.parseObject(json, GasStationArgument.class);
        }
        return gasStationArgumentMapper.selectGasStationArgumentByName(name);
    }
}
