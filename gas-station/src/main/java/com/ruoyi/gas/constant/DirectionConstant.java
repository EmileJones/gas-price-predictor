package com.ruoyi.gas.constant;

/**
 * 方向常量
 * @author KlenKiven
 */
public class DirectionConstant {

    public static final Integer EAST = 0;
    public static final Integer WEST = 1;
    public static final Integer NORTH = 2;
    public static final Integer SOUTH = 3;
    public static final Integer EAST_NORTH = 4;
    public static final Integer EAST_SOUTH = 5;
    public static final Integer WEST_NORTH = 6;
    public static final Integer WEST_SOUTH = 7;

    /**
     * 方向权值比重：东、西、北、南、东北、东南、西北、西南
     * 常量将从数据库加载，每次更新同步更新这个常量
     */
    public static double[][] DIRECTION_MATRIX = {
            {0, 0.05, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1},
            {0.05, 0, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1},
            {0.1, 0.1, 0, 0.05, 0.1, 0.1, 0.1, 0.1},
            {0.1, 0.1, 0.05, 0, 0.1, 0.1, 0.1, 0.1},
            {0.1, 0.1, 0.1, 0.1, 0, 0.1, 0.1, 0.05},
            {0.1, 0.1, 0.1, 0.1, 0.1, 0, 0.05, 0.1},
            {0.1, 0.1, 0.1, 0.1, 0.05, 0.1, 0, 0.1},
            {0.1, 0.1, 0.1, 0.05, 0.1, 0.1, 0.1, 0}
    };

    /**
     * 将方向转换为数字
     * @param direction 方向
     * @return 数字
     */
    public static Integer getDirectionNum(String direction) {
        switch (direction) {
            case "东":
                return EAST;
            case "西":
                return WEST;
            case "北":
                return NORTH;
            case "南":
                return SOUTH;
            case "东北":
                return EAST_NORTH;
            case "东南":
                return EAST_SOUTH;
            case "西北":
                return WEST_NORTH;
            case "西南":
                return WEST_SOUTH;
            default:
                return -1;
        }
    }

}
