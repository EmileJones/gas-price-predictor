package com.ruoyi.gas.module.geo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.GasStationUserOwned;
import com.ruoyi.gas.module.geo.domain.GasStationUserOwned.StationStatus;
import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;
import com.ruoyi.gas.module.geo.mapper.GasStationUserOwnedMapper;
import com.ruoyi.gas.module.geo.service.IGasStationUserOwnedService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 用户加油站Service业务层处理
 * 
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
@Service
public class GasStationUserOwnedServiceImpl implements IGasStationUserOwnedService
{
    @Autowired
    private GasStationUserOwnedMapper gasStationUserOwnedMapper;

    @Override
    public List<UserStationVO> listUserOwnedStation(Long userId) {
        GasStationUserOwned condition = new GasStationUserOwned();
        condition.setUserId(userId);
        List<GasStationUserOwned> stationEntities =
                gasStationUserOwnedMapper.selectGasStationUserOwnedList(condition);

        if (stationEntities == null || stationEntities.size() == 0) {
            return new ArrayList<>();
        }
        return stationEntities.stream()
                .filter(station -> !station.getStatus().equals(StationStatus.DELETED))
                .map(entity -> {
                    UserStationVO vo = new UserStationVO();
                    BeanUtils.copyProperties(entity, vo);
                    return vo;
                }).collect(Collectors.toList());
    }

    @Override
    public void createStationForUser(GasStationInfo station) {
        Long userId = SecurityUtils.getUserId();
        String stationId = station.getId();
        String stationName = station.getName();

        GasStationUserOwned userStation = new GasStationUserOwned();
        userStation.setUserId(userId);
        userStation.setStationId(stationId);
        userStation.setStationName(stationName);
        userStation.setStatus(StationStatus.CREATED);

        int affectRow = gasStationUserOwnedMapper.updateGasStation(userStation);
        if (affectRow == 0) {
            gasStationUserOwnedMapper.insertGasStationUserOwned(userStation);
        }
    }

    @Override
    public void changeStationStatus(Long userId, String stationId, Integer status) {
        GasStationUserOwned userStation = selectUserStationByUserIdAndStationId(userId, stationId);
        if (userStation == null) throw new ServiceException("用户加油站不存在");

        if (status == StationStatus.DISABLED) {
            userStation.setStatus(StationStatus.DISABLED);
        } else {
            if (userStation.getUpdateTime() == null) {
                userStation.setStatus(StationStatus.CREATED);
            } else {
                userStation.setStatus(StationStatus.ENABLED);
            }
        }

        gasStationUserOwnedMapper.updateGasStation(userStation);
    }

    @Override
    public void postImportSaleData(Set<String> stationIdSet) {
        Long userId = SecurityUtils.getUserId();
        for (String stationId : stationIdSet) {
            GasStationUserOwned station = selectUserStationByUserIdAndStationId(userId, stationId);
            if (station == null) continue;
            if (station.getUpdateTime() == null && station.getStatus() != StationStatus.DISABLED) {
                station.setStatus(StationStatus.ENABLED);
            }
            station.setUpdateTime(new Date());

            gasStationUserOwnedMapper.updateGasStation(station);
        }
    }

    @Override
    public void deleteGasStation(Long userId, String stationId) {
        GasStationUserOwned stationUserOwned = new GasStationUserOwned();
        // Locate
        stationUserOwned.setUserId(userId);
        stationUserOwned.setStationId(stationId);

        // Delete
        stationUserOwned.setStatus(StationStatus.DELETED);

        gasStationUserOwnedMapper.updateGasStation(stationUserOwned);
    }

    /**
     * 根据用户ID和加油站ID获取加油站信息
     */
    private GasStationUserOwned selectUserStationByUserIdAndStationId(Long userId, String stationId) {
        GasStationUserOwned condition = new GasStationUserOwned();
        condition.setUserId(userId);
        condition.setStationId(stationId);
        List<GasStationUserOwned> userStations = gasStationUserOwnedMapper.selectGasStationUserOwnedList(condition);
        if (userStations != null && userStations.size() != 0) {
            return userStations.get(0);
        }
        return null;
    }

}
