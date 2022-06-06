package com.ruoyi.gas.module.geo.mapper;

import com.ruoyi.gas.module.geo.domain.OpponentMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpponentMessageMapper {
    /**
     * 添加对手加油站信息
     * @param opponentMessages 对手加油站信息
     * @return 返回添加成功的数据数
     */
    int insertOpponentMessage(@Param("opponentMessages") List<OpponentMessage> opponentMessages);

    /**
     * 查询对手加油站信息
     * @param opponentMessages 查询条件
     * @return 符合查询条件的数据
     */
    List<OpponentMessage> selectOpponentMessage(OpponentMessage opponentMessages);

    /**
     * 更新对手加油站信息
     * @param opponentMessages 需要更新加油站数据（id为必须属性）
     * @return 更新成功的对手加油站数据条数
     */
    int updateOpponentMessage(OpponentMessage opponentMessages);
}
