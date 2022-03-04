package com.ruoyi.gas.module.geo.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.PinyinUtil;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.gas.module.geo.domain.GasStationGeo;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.form.GasStationGeoForm;
import com.ruoyi.gas.module.geo.mapper.GasStationGeoMapper;
import com.ruoyi.gas.module.geo.mapper.GasStationInfoMapper;
import com.ruoyi.gas.module.geo.service.IGasStationInfoService;
import com.ruoyi.gas.module.map.MapService;
import com.ruoyi.gas.module.map.amap.AmapClient;
import com.ruoyi.gas.module.map.amap.model.PlaceAroundRequest;
import com.ruoyi.gas.module.map.amap.model.PlaceAroundResult;
import com.ruoyi.gas.module.map.amap.model.place.POI;
import com.ruoyi.gas.module.map.model.PlaceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 加油站信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-01-21
 */
@Service
public class GasStationInfoServiceImpl implements IGasStationInfoService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MapService mapService;

    @Autowired
    private GasStationInfoMapper gasStationInfoMapper;

    @Autowired
    private GasStationGeoMapper gasStationGeoMapper;

    @Autowired
    @Deprecated
    private AmapClient amapClient;

    @Value("${gas.amap.key}")
    @Deprecated
    private String amapKey;

    @Override
    public GasStationInfo selectGasStationInfoById(String id) {
        return gasStationInfoMapper.selectGasStationInfoById(id);
    }

    @Override
    public List<GasStationInfo> selectGasStationInfoList(GasStationInfo GasStation) {
        return gasStationInfoMapper.selectGasStationInfoList(GasStation);
    }

    @Override
    public int insertGasStationInfo(GasStationInfo gasStation) {
        return gasStationInfoMapper.insertGasStationInfo(gasStation);
    }

    @Override
    public int updateGasStation(GasStationInfo GasStation) {
        return gasStationInfoMapper.updateGasStationInfo(GasStation);
    }

    @Override
    public int deleteGasStationInfoByIds(String[] ids) {
        return gasStationInfoMapper.deleteGasStationInfoByIds(ids);
    }

    @Override
    public int deleteGasStationInfoById(String id) {
        return gasStationInfoMapper.deleteGasStationInfoById(id);
    }

    @Override
    public GasStationInfo getSystemStationByLocation(String location, int radius) {
        GasStationInfo gasStationInfo = gasStationInfoMapper.selectOneByLocation(location);
        if (gasStationInfo == null) {
            List<PlaceInfo> placeInfos = mapService.listPlaceAroundOrigin(location, radius);
            gasStationInfo = handlePlaceInfo(placeInfos.get(0), true);
            String systemStationId = gasStationInfo.getId();
            for (int i = 1; i < placeInfos.size(); i++) {
                String outSystemId = handlePlaceInfo(placeInfos.get(i), false).getId();
                saveRelationBetweenSystemAndOutSystemStation(systemStationId, outSystemId);
            }
        }
        return gasStationInfo;
    }

    @Override
    public List<GasStationInfo> listOutSystemStationBySystemStationId(String systemId) {
        List<GasStationGeo> relations = gasStationGeoMapper.selectGasStationGeoBySystemStationId(systemId);
        if (relations == null || relations.size() == 0) {
            return new ArrayList<>();
        }
        List<String> outSystemIds = relations.stream()
                .map(GasStationGeo::getOutSystemStationId)
                .collect(Collectors.toList());
        return gasStationInfoMapper.selectByIds(outSystemIds);
    }

    /**
     * 将PlaceInfo进行处理，并返回GasStationInfo
     * @param placeInfo 地图API查询到的数据
     * @param isSystem 是否是系统内
     * @return GasStationInfo
     */
    private GasStationInfo handlePlaceInfo(PlaceInfo placeInfo, boolean isSystem) {
        GasStationInfo gasStationInfo = gasStationInfoMapper.selectOneByLocation(placeInfo.getLocation());
        if (gasStationInfo != null) { return gasStationInfo; }
        gasStationInfo = new GasStationInfo();
        gasStationInfo.setProvince(placeInfo.getPname());
        gasStationInfo.setLocation(placeInfo.getLocation());
        gasStationInfo.setCity(placeInfo.getCityname());
        gasStationInfo.setName(placeInfo.getName());
        gasStationInfo.setIsSystem(isSystem);
        gasStationInfo.setId(generateId(placeInfo.getPname(), placeInfo.getCityname(), isSystem));
        gasStationInfoMapper.insertGasStationInfo(gasStationInfo);
        return gasStationInfo;
    }

    /**
     * 生成加油站编码
     *
     * @param province 省
     * @param city     市
     * @param isSystem 是否是系统内
     * @return 加油站编码
     */
    private String generateId(String province, String city, Boolean isSystem) {
        if (province == null || city == null || isSystem == null)
            return null;
        GasStationInfo temp = new GasStationInfo();
        temp.setProvince(province);
        temp.setCity(city);
        temp.setIsSystem(isSystem);
        int number = gasStationInfoMapper.selectCount(temp);
        String provinceFirstLetter = PinyinUtil.getAllFirstLetter(province).toUpperCase();
        String cityFirstLetter = PinyinUtil.getAllFirstLetter(city).toUpperCase();
        String system;
        if (isSystem) {
            system = "0";
        } else {
            system = "Y";
        }

        return provinceFirstLetter + cityFirstLetter + system + new DecimalFormat("000").format(number + 1);
    }

    /**
     * 保存系统内站点和系统外站点的关系
     * @param systemStationId 系统内站点ID
     * @param outSystemId 系统外站点ID
     */
    private void saveRelationBetweenSystemAndOutSystemStation(String systemStationId, String outSystemId) {
        GasStationGeo relation = new GasStationGeo();
        relation.setSystemStationId(systemStationId);
        relation.setOutSystemStationId(outSystemId);
        gasStationGeoMapper.insertGasStationGeo(relation);
    }

    /**
     * 修改加油站信息
     *
     * @param gasStationInfo 加油站信息
     * @return 结果
     */
    @Override
    public int updateGasStationInfo(GasStationInfo gasStationInfo) {
        return gasStationInfoMapper.updateGasStationInfo(gasStationInfo);
    }


}
