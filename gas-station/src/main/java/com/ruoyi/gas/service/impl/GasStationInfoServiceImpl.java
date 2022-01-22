package com.ruoyi.gas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.PinyinUtil;
import com.ruoyi.common.amap.AmapClient;
import com.ruoyi.common.amap.model.PlaceAroundRequest;
import com.ruoyi.common.amap.model.PlaceAroundResult;
import com.ruoyi.common.amap.model.place.POI;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.gas.domain.GasStationGeo;
import com.ruoyi.gas.domain.vo.GasStationGeoForm;
import com.ruoyi.gas.domain.vo.GasStationGeoVO;
import com.ruoyi.gas.service.IGasStationGeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gas.mapper.GasStationInfoMapper;
import com.ruoyi.gas.domain.GasStationInfo;
import com.ruoyi.gas.service.IGasStationInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 加油站信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
@Service
public class GasStationInfoServiceImpl implements IGasStationInfoService 
{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmapClient amapClient;
    @Autowired
    private GasStationInfoMapper gasStationInfoMapper;
    @Autowired
    private IGasStationGeoService geoService;

    /**
     * 查询加油站信息
     * 
     * @param id 加油站信息主键
     * @return 加油站信息
     */
    @Override
    public GasStationInfo selectGasStationInfoById(String id)
    {
        return gasStationInfoMapper.selectGasStationInfoById(id);
    }

    /**
     * 查询加油站信息列表
     * 
     * @param gasStationInfo 加油站信息
     * @return 加油站信息
     */
    @Override
    public List<GasStationInfo> selectGasStationInfoList(GasStationInfo gasStationInfo)
    {
        return gasStationInfoMapper.selectGasStationInfoList(gasStationInfo);
    }

    /**
     * 保存或更新加油站信息
     * 
     * @param info 加油站信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GasStationInfo saveGasStationInfo(GasStationGeoForm info)
    {
        long start = System.currentTimeMillis();
        int radius = info.getRadius();
        String location = info.getLocation();

        // 查询当前加油站的信息，以及周边加油站的信息
        PlaceAroundRequest aroundRequest = new PlaceAroundRequest();
        aroundRequest.setKey("25dc8e3377f5d44b60b5a7881cbb7cd2");
        aroundRequest.setLocation(location);
        aroundRequest.setTypes("010100");
        aroundRequest.setRadius(radius);
        PlaceAroundResult placeAroundResult = amapClient.placeAroundRequest(aroundRequest);
        List<POI> pois = placeAroundResult.getPois();

        // 保存所有加油站的信息
        List<GasStationInfo> collect = pois.stream()
                .map((poi -> {
                    GasStationInfo gasStationInfo = existInfo(poi);
                    if (gasStationInfo == null) {
                        gasStationInfo = mapToInfo(poi);
                        gasStationInfoMapper.insertGasStationInfo(gasStationInfo);
                    } else {
                        GasStationInfo newest = mapToInfo(poi);
                        newest.setId(gasStationInfo.getId());
                        updateGasStationInfo(gasStationInfo);
                    }
                    return gasStationInfo;
                }))
                .collect(Collectors.toList());

        // 保存系统内加油站结果
        String currOutId = collect.get(0).getId();
        GasStationInfo currentStation;
        if (currOutId.charAt(currOutId.length() - 4) == 'Y') {
            String sysPrefix = currOutId.substring(0, currOutId.lastIndexOf("Y")) + '0';
            currentStation = gasStationInfoMapper.selectOneByCondition(sysPrefix, collect.get(0).getLocation());
            if (currentStation == null) {
                // 如果不存在就新增
                currentStation = collect.get(0);
                currentStation.setId(keyGenerator(pois.get(0), true));
                gasStationInfoMapper.insertGasStationInfo(currentStation);
            } else {
                // 如果存在就更新
                GasStationInfo newest = collect.get(0);
                newest.setId(currentStation.getId());
                updateGasStationInfo(newest);
                currentStation = newest;
            }
        } else {
            throw new GlobalException("【站点信息保存】系统数据出现问题");
        }

        // 保存加油站之间的地理信息
        collect.remove(0);
        log.info("[GasStation-Info-save] currentStation ID: " + currentStation.getId() +
                ", currentStation name: " + currentStation.getName());
        log.debug("[GasStation-Info-save] full gasStation collection: " + collect);

        geoService.saveGeoInfo(radius, currentStation, collect);

        // 花费时间
        log.info("[GasStation-Info-save] Consume time: " + (System.currentTimeMillis() - start) + "ms");

        return currentStation;
    }

    /**
     * 修改加油站信息
     * 
     * @param gasStationInfo 加油站信息
     * @return 结果
     */
    @Override
    public int updateGasStationInfo(GasStationInfo gasStationInfo)
    {
        return gasStationInfoMapper.updateGasStationInfo(gasStationInfo);
    }

    /**
     * 批量删除加油站信息
     * 
     * @param ids 需要删除的加油站信息主键
     * @return 结果
     */
    @Override
    public int deleteGasStationInfoByIds(String[] ids)
    {
        return gasStationInfoMapper.deleteGasStationInfoByIds(ids);
    }

    /**
     * 删除加油站信息信息
     * 
     * @param id 加油站信息主键
     * @return 结果
     */
    @Override
    public int deleteGasStationInfoById(String id)
    {
        return gasStationInfoMapper.deleteGasStationInfoById(id);
    }

    @Override
    public List<GasStationGeoVO> selectGasStationGeoInfoList(GasStationGeoForm gasStationGeo) {
        GasStationInfo gasStationInfo = gasStationInfoMapper.selectOneByLocation(gasStationGeo.getLocation());
        String idInSystem = existInfoInSystem(gasStationGeo);
        // 如果加油站信息在系统内不存在，那么就保存这些加油站信息
        if (gasStationInfo == null || "".equals(idInSystem)) {
            idInSystem = saveGasStationInfo(gasStationGeo).getId();
        }
        // 如果加油站信息在系统内存在，那么就查询地理信息列表
        GasStationGeo condition  = new GasStationGeo();
        condition.setSystemStationId(idInSystem);
        String systemStationName = gasStationInfoMapper.selectGasStationInfoById(idInSystem).getName();
        List<GasStationGeo> gasStationGeos = geoService.selectGasStationGeoList(condition);
        return gasStationGeos.stream()
                .map((geo) -> {
                    GasStationGeoVO vo = new GasStationGeoVO();
                    BeanUtils.copyProperties(geo, vo);
                    vo.setSystemStationName(systemStationName);
                    vo.setOutSystemStationName(gasStationInfoMapper
                            .selectGasStationInfoById(geo.getOutSystemStationId())
                            .getName());
                    return vo;
                })
                .collect(Collectors.toList());
    }

    /**
     * 判断在系统内是否存在这个加油站地点。
     * 如果存在，那么返回系统内的ID
     *
     * @param gasStationGeo 加油站信息
     * @return 加油站ID
     */
    private String existInfoInSystem(GasStationGeoForm gasStationGeo) {
        List<String> ids =
                gasStationInfoMapper.selectIdByLocation(gasStationGeo.getLocation());
        if (ids == null || ids.size() == 0) {
            return "";
        }
        for (String id : ids) {
            // 如果是系统内的ID，那么ID倒数第四个字符一定是 '0'
            if (id.charAt(id.length()-4) == '0') {
                return id;
            }
        }
        return "";
    }

    /**
     * 用于判断是否存在这条记录
     * 如果存在，那么就返回这个结果。
     * <p>
     *     NOTE: 此方法保证，找到的结果就是系统外的站点结果，因为这是由系统内外加油站的编码规则决定
     * </p>
     *
     * @param poi 高德地图API的地理信息
     * @return 系统内地理信息
     */
    private GasStationInfo existInfo(POI poi) {
        return gasStationInfoMapper.selectOneByLocation(poi.getLocation());
    }

    /**
     * 将高德地图返回的POI信息转换为系统的GasStationInfo
     * 默认设置为系统外的POI
     * @param poi POI
     * @return GasStationInfo
     */
    private GasStationInfo mapToInfo(POI poi) {
        GasStationInfo ret = new GasStationInfo();
        ret.setId(keyGenerator(poi, false));
        ret.setName(poi.getName());
        ret.setProvince(poi.getPname());
        ret.setCity(poi.getCityname());
        ret.setLocation(poi.getLocation());
        return ret;
    }

    /**
     * 根据地区前缀生成编码
     * @param poi POI
     * @param isSystemLocation 是否属于系统内的位置
     * @return 生成的主键
     */
    private String keyGenerator(POI poi, boolean isSystemLocation) {
        String shortProvinceName =
                poi.getPname()
                        .replace("省", "")
                        .replace("市", "");
        String shortCityName = poi.getCityname().replace("市", "");
        String province = PinyinUtil.getAllFirstLetter(shortProvinceName).toUpperCase();
        String city = PinyinUtil.getAllFirstLetter(shortCityName).toUpperCase();
        GasStationInfo highestOne =
                gasStationInfoMapper.selectOneByIdHighest(province + city + (isSystemLocation?'0':'Y'));
        int newestIdNum;
        if (highestOne != null) {
            newestIdNum = Integer.parseInt(highestOne.getId().replaceAll("[a-zA-Z]", ""));
        } else {
            newestIdNum = 0;
        }
        return String.format("%s%s%c%03d", province, city, (isSystemLocation?'0':'Y'), newestIdNum + 1);
    }
}
