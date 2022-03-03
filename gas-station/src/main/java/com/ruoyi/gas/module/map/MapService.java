package com.ruoyi.gas.module.map;

import com.ruoyi.gas.module.map.model.PathCost;
import com.ruoyi.gas.module.map.model.PlaceInfo;

import java.util.List;

/**
 * 地图模块服务类 -- 主要用于搜索加油站
 * <p>地图模块主要负责对地图相关数据的查询工作，包括：
 * <ul>
 * <li>根据行政代码和关键字搜索该地区的某些地点信息</li>
 * <li>搜索某个地点周围指定半径以内的地点</li>
 * <li>搜索两个地点路线的代价信息</li>
 * </ul></p>
 * @author KlenKiven
 */
public interface MapService {

     /**
      * 根据行政代码和关键字搜索该地区加油站地点信息
      * <p>这个方法主要用于搜索某个地区内带有关键字的地点，例如搜索
      * 山西省，太原市，小店区的关键词带有“长城”的加油站信息。那么应
      * 该传入参数，adcode: 140105, keywords: 长城。然后就可
      * 以相关结果。</p>
      * @param adcode 中国行政区划代码（可以精确到县/区）
      * @param keywords 搜索地点的关键字
      * @return 返回地点信息
      */
     List<PlaceInfo> listPlaceByAdcodeAndKeyword(String adcode, String keywords);

     /**
      * 搜索某个地点周围指定半径以内的地点
      * <p>搜索指定地点周围半径的地点，传入经纬度信息，搜索的半径，
      * 即可得到地点周围的地区列表。例如，搜索 “长城加油站(平阳南路)” 半径为3.5千米范围内
      * 的所有加油站地点。那就应该传入参数，location: 112.551509,37.790016,
      * radius: 3500，然后就可以获得周围地区信息列表</p>
      * @param location 指定位置的经纬度
      * @param radius 搜索半径（米）
      * @return 返回地点信息列表
      */
     List<PlaceInfo> listPlaceAroundOrigin(String location, Integer radius);

     /**
      * 搜索两个地点路线的代价信息
      * <p>传入起点的经纬度，传入终点的经纬度，即可返回路线代价的信息。</p>
      * @param origin 起点位置的经纬度
      * @param destination 重点位置的经纬度
      * @return 两地点之间的路线代价信息
      */
     PathCost getPathCost(String origin, String destination);
}
