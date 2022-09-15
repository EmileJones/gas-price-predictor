package com.ruoyi.gas.module.geo.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.form.GasStationAddForm;
import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;
import com.ruoyi.gas.module.geo.service.GasStationService;
import com.ruoyi.gas.module.geo.service.GeoService;
import com.ruoyi.gas.module.geo.service.IGasStationInfoService;
import com.ruoyi.gas.module.geo.service.IGasStationUserOwnedService;
import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.excel.SaleDataExcel;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.service.ISaleDataService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GasStationServiceImpl implements GasStationService {
    private final IGasStationUserOwnedService userOwnedService;
    private final IGasStationInfoService infoService;
    private final ISaleDataService saleDataService;
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

    @Override
    public void importSaleData(Map<String, List<SaleDataExcel>> map) {
        Long userId = SecurityUtils.getUserId();
        Map<String, String> stationNameAndIdMap = userOwnedService.listUserOwnedStation(userId).stream()
                .collect(Collectors.toMap(UserStationVO::getStationName, UserStationVO::getStationId));

        List<OilSaleData> oilSaleData = map.entrySet().stream()
                .flatMap(entry -> {
                    if (!stationNameAndIdMap.containsKey(entry.getKey())){
                        return Stream.empty();
                    }
                    String stationId = stationNameAndIdMap.get(entry.getKey());
                    return entry.getValue().stream()
                            .map(data -> convertToOilSaleData(userId, stationId, data));
                }).collect(Collectors.toList());

        Future<Set<String>> stationIdSet = saleDataService.addOilSaleDatas(oilSaleData);
        userOwnedService.postImportSaleData(userId, stationIdSet);
    }

    private OilSaleData convertToOilSaleData(Long userId, String stationId, SaleDataExcel excel) {
        OilSaleData oilSaleData = new OilSaleData();
        DateTime businessDate = new DateTime(excel.getBusinessDate());

        oilSaleData.setUserId(userId);
        oilSaleData.setGasStationId(stationId);
        oilSaleData.setDate(businessDate);
        oilSaleData.setOilType(OilType.getOilByTypeName(excel.getMaterialName()));
        oilSaleData.setLNumber(excel.getQuantityL().doubleValue());
        oilSaleData.setKgNumber(excel.getQuantityKG().doubleValue());
        oilSaleData.setPrice(excel.getPrice().doubleValue());
        return oilSaleData;
    }

    public GasStationServiceImpl(IGasStationUserOwnedService userOwnedService,
                                 IGasStationInfoService infoService,
                                 ISaleDataService saleDataService,
                                 GeoService geoService) {
        this.userOwnedService = userOwnedService;
        this.infoService = infoService;
        this.saleDataService = saleDataService;
        this.geoService = geoService;
    }
}
