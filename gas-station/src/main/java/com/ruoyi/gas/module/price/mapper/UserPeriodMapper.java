package com.ruoyi.gas.module.price.mapper;

import com.ruoyi.gas.module.price.domain.UserPeriod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface UserPeriodMapper {
    String ID = "id";
    String USER_ID = "user_id";
    String GAS_STATION_ID = "gas_station_id";
    String TIME_STAMP = "time_stamp";

    /**
     * 查询符合条件的数据
     * @param condition 条件
     * @return 符合条件的数据集合
     */
    List<UserPeriod> selectUserPeriod(@Param("condition") UserPeriod condition,
                                      @Param("isDesc") Boolean isDesc,
                                      @Param("orderField") String orderField,
                                      @Param("startIndex") Long startIndex,
                                      @Param("amount") Long amount);

    /**
     * 新增数据
     * @param userPeriods 新增的数据（id自增，可为null）
     * @return 受到影响的数据条数
     */
    int insertUserPeriod(List<UserPeriod> userPeriods);

    /**
     * 删除符合条件的数据数
     * @param condition 条件
     * @return 受到影响的数据条数
     */
    int deleteUserPeriod(UserPeriod condition);

    /**
     * 获取用户周期数量
     * @param userId 用户ID
     * @param gasStationId 加油站ID
     * @return 用户周期总数
     */
    long countUserPeriod(@Param("userId") Long userId, @Param("gasStationId") String gasStationId);
}
