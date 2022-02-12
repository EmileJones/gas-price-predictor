package com.ruoyi.gas.utils;

import java.util.Arrays;

/**
 * 关于数组的一些工具
 */
public final class ArrayUtils {
    private ArrayUtils() {}

    /**
     * 将字符串反序列化为Double二维数组
     * @param source 字符串
     * @return 反序列化后的结果
     */
    public static double[][] deserialize2DimDoubleArray(String source) {
        source = source.substring(1, source.length()-2);
        String[] dim1 = source.split("],");
        double[][] result = new double[dim1.length][];
        for (int i = 0; i < dim1.length; i++) {
            String[] elements = dim1[i].substring(1).split(",");
            double[] e = new double[elements.length];
            for (int j = 0; j < elements.length; j++) {
                e[j] = Double.parseDouble(elements[j]);
            }
            result[i] = e;
        }
        return result;
    }
}
