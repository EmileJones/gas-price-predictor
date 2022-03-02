package com.ruoyi.gas.module.map;

import com.ruoyi.gas.module.map.model.PathCost;
import com.ruoyi.gas.module.map.model.PlaceInfo;

import java.util.List;

public interface MapService {

     List<PlaceInfo> listPlaceByAdcodeAndKeyword(String adcode, String keywords);

     List<PlaceInfo> listPlaceAroundOrigin(String location, Integer radius);

     PathCost getPathCost(String origin, String destination);
}
