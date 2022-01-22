package com.ruoyi.common.amap;

import com.ruoyi.common.amap.constant.AmapConstant;
import com.ruoyi.common.amap.model.*;
import org.springframework.stereotype.Repository;

/**
 * Amap Client
 * @author klenkiven
 */
@Repository
public class AmapClient {

    /**
     * 搜索POI - 周边搜索
     */
    public PlaceAroundResult placeAroundRequest(PlaceAroundRequest request) {
        return HttpRequestUtils.doGetRequest(AmapConstant.PLACE_AROUND, request, PlaceAroundResult.class);
    }

    /**
     * 路径规划 2.0
     */
    public DrivingDirectionResult drivingDirectionRequest(DrivingDirectionRequest request) {
        return HttpRequestUtils.doGetRequest(AmapConstant.DRIVING_DIRECTION, request, DrivingDirectionResult.class);
    }

    /**
     * 搜索POI - 关键字搜索
     */
    public PlaceAroundResult placeTextRequest(PlaceTextRequest request) {
        return HttpRequestUtils.doGetRequest(AmapConstant.PLACE_TEXT, request, PlaceAroundResult.class);
    }
}
