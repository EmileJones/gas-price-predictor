package com.ruoyi.gas.module.geo.service;

import com.ruoyi.gas.module.geo.domain.OpponentMessage;
import com.ruoyi.gas.module.geo.domain.vo.OpponentMessageVO;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 提供关于 对手信息 的类
 */

public interface IOpponentMessageService {
    /**
     * 返回该用户的此加油站的所有对手信息
     *
     * @param userId       用户唯一ID
     * @param gasStationId 系统内加油站ID
     * @return 所有竞争对手加油站的信息
     */
    List<OpponentMessageVO> getOpponentMessage(Long userId, String gasStationId);

    /**
     * 为该用户修改对手加油站的名字
     *
     * @param id                数据唯一标识
     * @param outGasStationName 竞争对手加油站名字
     * @return 是否修改成功
     */
    boolean modifyOpponentGasStationName(Long id, String outGasStationName);

    /**
     * 刷新该用户加油站的竞争对手信息
     *
     * @param userId       用户ID
     * @param gasStationId 加油站ID
     * @return 新增的对手加油站的信息
     */
    List<OpponentMessage> refreshOpponentMessage(Long userId, String gasStationId);

    /**
     * 通过系统外加油站ID获取对手信息
     * @param userId 用户ID
     * @param gasStationId 加油站ID
     * @param outGasStationId 系统外加油站ID
     * @return 加油站信息
     */
    OpponentMessage getOpponentMessageByStationId(Long userId, String gasStationId, String outGasStationId);
}
