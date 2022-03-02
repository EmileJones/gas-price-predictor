package com.ruoyi.gas.module.geo.service.impl;

import com.ruoyi.gas.module.geo.domain.GasStationGeo;
import com.ruoyi.gas.module.geo.service.GeoService;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.form.GasStationForm;
import com.ruoyi.gas.module.geo.domain.form.GasStationGeoForm;
import com.ruoyi.gas.module.geo.domain.vo.GasStationCandidateVO;
import com.ruoyi.gas.module.geo.domain.vo.GasStationGeoVO;
import com.ruoyi.gas.module.geo.service.IGasStationGeoService;
import com.ruoyi.gas.module.geo.service.IGasStationInfoService;
import com.ruoyi.gas.module.map.MapService;
import com.ruoyi.gas.module.map.model.PlaceInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO to implement
 */
@Service
public class GeoServiceImpl implements GeoService {

    private final IGasStationGeoService gasStationGeoService;
    private final IGasStationInfoService gasStationInfoService;
    private final MapService mapService;

    @Override
    public GasStationInfo listGasStationInfoByUserId() {
        // TODO IMPL
        return null;
    }

    @Override
    public List<GasStationGeoVO> listStationGeo(GasStationGeoForm geoForm) {
        GasStationInfo systemInfo = gasStationInfoService.getSystemStationByLocation(geoForm.getLocation());
        List<GasStationInfo> outSystemStationList =
                gasStationInfoService.listOutSystemStationBySystemStationId(systemInfo.getId());

        List<GasStationGeo> gasStationGeos = gasStationGeoService.listStationDistance(systemInfo, outSystemStationList);

        return null;
    }

    @Override
    public List<GasStationCandidateVO> listCandidateStations(GasStationForm form) {
        List<PlaceInfo> placeInfos = mapService.listPlaceByAdcodeAndKeyword(form.getAdcode(), form.getQuery());

        if (placeInfos == null || placeInfos.size() == 0) return new ArrayList<>();
        return placeInfos.stream()
                .map(placeInfo -> {
                    GasStationCandidateVO candidateVO = new GasStationCandidateVO();
                    candidateVO.setLocation(placeInfo.getLocation());
                    candidateVO.setStationName(placeInfo.getName());
                    return candidateVO;
                }).collect(Collectors.toList());
    }

    public GeoServiceImpl(IGasStationGeoService gasStationGeoService, IGasStationInfoService gasStationInfoService, MapService mapService) {
        this.gasStationGeoService = gasStationGeoService;
        this.gasStationInfoService = gasStationInfoService;
        this.mapService = mapService;
    }
}
