package com.ruoyi.gas.module.geo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 查询用户加油站
     * 
     * @param id 用户加油站主键
     * @return 用户加油站
     */
    @Override
    public GasStationUserOwned selectGasStationUserOwnedById(Long id)
    {
        return gasStationUserOwnedMapper.selectGasStationUserOwnedById(id);
    }

    /**
     * 查询用户加油站列表
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 用户加油站
     */
    @Override
    public List<GasStationUserOwned> selectGasStationUserOwnedList(GasStationUserOwned gasStationUserOwned)
    {
        return gasStationUserOwnedMapper.selectGasStationUserOwnedList(gasStationUserOwned);
    }

    /**
     * 新增用户加油站
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 结果
     */
    @Override
    public int insertGasStationUserOwned(GasStationUserOwned gasStationUserOwned)
    {
        return gasStationUserOwnedMapper.insertGasStationUserOwned(gasStationUserOwned);
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
}
