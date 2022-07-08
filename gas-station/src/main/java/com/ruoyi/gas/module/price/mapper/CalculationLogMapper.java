package com.ruoyi.gas.module.price.mapper;

import com.ruoyi.gas.module.price.domain.CalculationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalculationLogMapper {
    /**
     * 插入日志数据
     * @param log 日志数据
     * @return 成功则返回1，失败则返回0
     */
    int insertSingle(CalculationLog log);
}
