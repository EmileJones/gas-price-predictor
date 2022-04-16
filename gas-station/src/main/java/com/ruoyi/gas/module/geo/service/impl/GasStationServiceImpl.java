package com.ruoyi.gas.module.geo.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.form.GasStationAddForm;
import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;
import com.ruoyi.gas.module.geo.service.GasStationService;
import com.ruoyi.gas.module.geo.service.GeoService;
import com.ruoyi.gas.module.geo.service.IGasStationInfoService;
import com.ruoyi.gas.module.geo.service.IGasStationUserOwnedService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class GasStationServiceImpl implements GasStationService {
    private final IGasStationUserOwnedService userOwnedService;
    private final IGasStationInfoService infoService;
    private final GeoService geoService;

    @Override
    public List<UserStationVO> listGasStationByUserId(Long userId) {
        return userOwnedService.listUserOwnedStation(userId);
    }

    @Override
    public void addUserStation(GasStationAddForm stationAddForm) {
        String formLocation = stationAddForm.getLocation();
        geoService.loadGasStation(formLocation);

        GasStationInfo station = infoService.getSystemStationByLocation(formLocation);
        Assert.notNull(station, "加油站位置异常");
        userOwnedService.createStationForUser(station);
    }

    @Override
    public void removeStation(String stationId) {
        Long userId = SecurityUtils.getUserId();
        userOwnedService.deleteGasStation(userId, stationId);
    }

    @Override
    public void changeStationStatus(String stationId, Integer status) {
        Long userId = SecurityUtils.getUserId();
        userOwnedService.changeStationStatus(userId, stationId, status);
    }

    public GasStationServiceImpl(IGasStationUserOwnedService userOwnedService,
                                 IGasStationInfoService infoService,
                                 GeoService geoService) {
        this.userOwnedService = userOwnedService;
        this.infoService = infoService;
        this.geoService = geoService;
    }
}
