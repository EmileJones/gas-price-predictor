package com.ruoyi.gas.module.geo.utils;

import com.ruoyi.gas.constant.DirectionConstant;

/**
 * 距离相关参数计算工具类
 * <p>主要职责：
 * <ul>
 * <li>计算加油站相关参数，并将参数所得结果返回到调用者</li>
 * </ul></p>
 * @author KlenKiven
 */
public final class DistanceCalculateUtils {

    private DistanceCalculateUtils() {  }

    /**
     * 计算路线因子
     * @param trafficFactor 红绿灯因子
     * @param routeShapeFactor 路线曲折度因子
     * @return 路线因子
     */
    public static Double routeFactorAlgorithm(Double trafficFactor, Double routeShapeFactor) {
        return trafficFactor * routeShapeFactor;
    }

    /**
     * 计算红绿灯因子
     *
     * @param trafficLights 红绿灯数
     * @return 红绿灯因子
     */
    public static Double trafficLightsAlgorithm(Integer trafficLights) {
        if (trafficLights > 10) {
            return 1.0;
        } else if (trafficLights < 0) {
            return 0.0001;
        } else {
            return (10 - trafficLights) / 10.0;
        }
    }

    /**
     * 计算路线曲折度因子
     * @param directions 方向
     * @return 路线曲折度因子
     */
    public static Double routeShapeFactorAlgorithm(String[] directions) {
        double ret = 1.0;
        if (directions == null || directions.length < 2) {
            return ret;
        }
        for (int i = 1; i < directions.length; i++) {
            int currDirection = DirectionConstant.getDirectionNum(directions[i]);
            if (currDirection == -1) {
                directions[i] = directions[i-1];
                currDirection = DirectionConstant.getDirectionNum(directions[i]);
            }
            int prevDirection =
                    DirectionConstant.getDirectionNum(directions[i-1]) == -1?
                            DirectionConstant.getDirectionNum(directions[i]):
                            DirectionConstant.getDirectionNum(directions[i-1]);
            ret -= DirectionConstant.DIRECTION_MATRIX[prevDirection][currDirection];
        }
        return ret;
    }
}
