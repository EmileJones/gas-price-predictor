package com.ruoyi.gas.module.geo.service.impl;

import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.OpponentMessage;
import com.ruoyi.gas.module.geo.domain.vo.OpponentMessageVO;
import com.ruoyi.gas.module.geo.mapper.OpponentMessageMapper;
import com.ruoyi.gas.module.geo.service.IOpponentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OpponentMessageServiceImpl implements IOpponentMessageService {
    @Autowired
    OpponentMessageMapper opponentMessageMapper;
    @Autowired
    GasStationInfoServiceImpl gasStationInfoService;

    @Override
    public List<OpponentMessageVO> getOpponentMessage(Long userId, String gasStationId) {
        OpponentMessage condition = new OpponentMessage();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        List<OpponentMessage> opponentMessages = opponentMessageMapper.selectOpponentMessage(condition);
        // 如果该用户第一次查看次加油站的信息
        if (opponentMessages.size() == 0) {
            List<GasStationInfo> gasStationInfos = gasStationInfoService.listOutSystemStationBySystemStationId(gasStationId);
            opponentMessages = convertGasStationInfo2OpponentMessage(userId, gasStationId, gasStationInfos);
            opponentMessageMapper.insertOpponentMessage(opponentMessages);
        }
        return convertOpponentMessage2OpponentMessageVO(opponentMessages);
    }

    @Override
    public boolean modifyOpponentGasStationName(Long id, String outGasStationName) {
        OpponentMessage update = new OpponentMessage();
        update.setUserId(id);
        update.setOutGasStationName(outGasStationName);
        int i = opponentMessageMapper.updateOpponentMessage(update);
        if (i == 0)
            return false;
        return true;
    }

    @Override
    public List<OpponentMessage> refreshOpponentMessage(Long userId, String gasStationId) {
        OpponentMessage condition = new OpponentMessage();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        // 收集更新的数据
        List<OpponentMessage> updateData = new LinkedList<>();
        // 获取gas_station_opponent_message表中的数据
        List<OpponentMessage> mapperData = opponentMessageMapper.selectOpponentMessage(condition);
        Map<String, OpponentMessage> mapperDataMap = new HashMap<>();
        // 获取gas_station_info表中的数据
        List<OpponentMessage> serviceData = convertGasStationInfo2OpponentMessage(userId, gasStationId, gasStationInfoService.listOutSystemStationBySystemStationId(gasStationId));
        Map<String, OpponentMessage> serviceDataMap = new HashMap<>();
        // 将gas_station_opponent_message中的数据存入map中
        mapperData.stream().forEach(opponentMessage -> {
            mapperDataMap.put(opponentMessage.getOutGasStationId(), opponentMessage);
        });
        // 将gas_station_info中的数据存入map中
        serviceData.stream().forEach(opponentMessage -> {
            serviceDataMap.put(opponentMessage.getOutGasStationId(), opponentMessage);
        });
        // 对比两个map中的数据
        Set<String> keySet = serviceDataMap.keySet();
        for (String key : keySet) {
            if (!mapperDataMap.containsKey(key)) {
                updateData.add(serviceDataMap.get(key));
            }
        }
        // 将结果存入gas_station_opponent_message表中
        if (updateData.size() != 0) {
            opponentMessageMapper.insertOpponentMessage(updateData);
        }
        return updateData;
    }


    @Override
    public OpponentMessage getOpponentMessageByStationId(Long userId, String gasStationId, String outGasStationId) {
        OpponentMessage condition = new OpponentMessage();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        condition.setOutGasStationId(outGasStationId);
        List<OpponentMessage> opponentMessages = opponentMessageMapper.selectOpponentMessage(condition);
        if (opponentMessages.size() == 1){
            return opponentMessages.get(0);
        }
        return null;
    }

    private List<OpponentMessageVO> convertOpponentMessage2OpponentMessageVO(List<OpponentMessage> opponentMessages) {
        return opponentMessages.stream()
                .map(opponentMessage -> {
                    OpponentMessageVO opponentMessageVO = new OpponentMessageVO();
                    opponentMessageVO.setId(opponentMessage.getId());
                    opponentMessageVO.setOutGasStationId(opponentMessage.getOutGasStationId());
                    opponentMessageVO.setOutGasStationName(opponentMessage.getOutGasStationName());
                    GasStationInfo gasStationInfo = gasStationInfoService.selectGasStationInfoById(opponentMessage.getOutGasStationId());
                    opponentMessageVO.setAddress(gasStationInfo.getAddress());
                    return opponentMessageVO;
                })
                .collect(Collectors.toList());
    }

    private List<OpponentMessage> convertGasStationInfo2OpponentMessage(Long userId, String gasStationId, List<GasStationInfo> gasStationInfos) {
        return gasStationInfos.stream()
                .map(gasStationInfo -> {
                    OpponentMessage opponentMessage = new OpponentMessage();
                    opponentMessage.setGasStationId(gasStationId);
                    opponentMessage.setUserId(userId);
                    opponentMessage.setOutGasStationId(gasStationInfo.getId());
                    opponentMessage.setOutGasStationName(gasStationInfo.getName());
                    return opponentMessage;
                })
                .collect(Collectors.toList());
    }
}
