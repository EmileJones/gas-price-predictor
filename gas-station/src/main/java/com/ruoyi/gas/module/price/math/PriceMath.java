package com.ruoyi.gas.module.price.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceMath {
    private final int DEFAULT_SCALE_NUMBER = 10;
    private PriceData data;
    /**
     * 覆盖面积
     */
    private BigDecimal y;
    /**
     * 日均销量
     */
    private BigDecimal asv;

    public PriceMath(PriceData data) {
        this.data = data;
        calculate();
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getAsv() {
        return asv;
    }

    private void calculate() {
        y = y();
        asv = asv();
    }

    private BigDecimal a(int n) {
        BigDecimal var1 = getBigDecimal(data.getInSalesVolume(n))
                .subtract(getBigDecimal(data.getInSalesVolume(n + 1)))
                .divide(getBigDecimal(data.getInSalesVolume(n)), DEFAULT_SCALE_NUMBER, RoundingMode.HALF_UP)
                .add(getBigDecimal(1.0));
        return var1;
    }

    private BigDecimal b(int n) {
        BigDecimal var1 = getBigDecimal(0.0);
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            BigDecimal var2 = getBigDecimal(data.getOutAverageMoney(i, n))
                    .multiply(getBigDecimal(data.getRouteFactor(i)));
            var1 = var1.add(var2);
        }
        return var1.subtract(getBigDecimal(data.getInMoney(n)));
    }

    private BigDecimal c(int n) {
        BigDecimal var1 = getBigDecimal(0.0);
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            BigDecimal var2 = getBigDecimal(data.getOutAverageMoney(i, n))
                    .multiply(getBigDecimal(data.getRouteFactor(i)))
                    .multiply(getBigDecimal(data.getDistance(i)));
            var1 = var1.add(var2);
        }
        return var1;
    }

    private BigDecimal y(int x) {
        BigDecimal var1 = a(x).multiply(c(x)).subtract(c(x + 1));
        BigDecimal var2 = a(x).multiply(b(x)).subtract(b(x + 1));
        return var1.divide(var2, DEFAULT_SCALE_NUMBER, RoundingMode.HALF_UP);
    }

    private BigDecimal y() {
        BigDecimal var1 = getBigDecimal(1.0);
        int var2 = data.getPeriodNumber() - 2;
        int var3 = 0;
        for (int i = 0; i < var2; i++) {
            try {
                BigDecimal yi = y(i);
                if (yi.compareTo(getBigDecimal(0.0)) > 0) {
                    var1 = var1.multiply(yi);
                    var3++;
                }
            } catch (ArithmeticException e) {
                System.err.println("[" + i + "]被舍去");
            }
        }
        double pow = Math.pow(var1.doubleValue(), 1.0 / var3);
        return getBigDecimal(pow);
    }


    private BigDecimal asv() {
        BigDecimal e = getBigDecimal(0.0);
        BigDecimal f = getBigDecimal(0.0);
        BigDecimal var1;
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            var1 = getBigDecimal(data.getDistance(i))
                    .divide(y, DEFAULT_SCALE_NUMBER, RoundingMode.HALF_UP);
            var1 = getBigDecimal(1.0).subtract(var1);
            e = e.add(getBigDecimal(data.getOutChaseMoney(i)).multiply(getBigDecimal(data.getRouteFactor(i))).multiply(var1));
            f = f.add(getBigDecimal(data.getOutPresentMoney(i)).multiply(getBigDecimal(data.getRouteFactor(i))).multiply(var1));
        }
        e = e.subtract(getBigDecimal(data.getInTargetMoney()));
        f = f.subtract(getBigDecimal(data.getInPresentMoney()));
        return getBigDecimal(2.0).subtract(e.divide(f, DEFAULT_SCALE_NUMBER, RoundingMode.HALF_UP))
                .multiply(getBigDecimal(data.getInPresentAverageSalesVolume()));
    }

    private BigDecimal getBigDecimal(Double number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal.setScale(5, RoundingMode.HALF_UP);
        return bigDecimal;
    }
}
