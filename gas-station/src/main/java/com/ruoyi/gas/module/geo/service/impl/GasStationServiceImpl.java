package com.ruoyi.gas.module.geo.service.impl;

import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;
import com.ruoyi.gas.module.geo.service.GasStationService;
import com.ruoyi.gas.module.geo.service.IGasStationUserOwnedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GasStationServiceImpl implements GasStationService {
    private final IGasStationUserOwnedService userOwnedService;

    @Override
    public List<UserStationVO> listGasStationByUserId(Long userId) {
        return userOwnedService.listUserOwnedStation(userId);
    }

    public GasStationServiceImpl(IGasStationUserOwnedService userOwnedService) {
        this.userOwnedService = userOwnedService;
    }
}
