package com.ruoyi.gas.module.map.amap;

import com.ruoyi.gas.module.map.MapService;
import com.ruoyi.gas.module.map.model.PathCost;
import com.ruoyi.gas.module.map.model.PlaceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmapMapService implements MapService {

    @Override
    public List<PlaceInfo> listPlaceByAdcodeAndKeyword(String adcode, String keywords) {
        // TODO listPlaceByAcodeAndKeyword
        return null;
    }

    @Override
    public List<PlaceInfo> listPlaceAroundOrigin(String location, Integer radius) {
        // TODO listPlaceAroundOrigin
        return null;
    }

    @Override
    public PathCost getPathCost(String origin, String destination) {
        // TODO getPathCost
        return null;
    }
}
