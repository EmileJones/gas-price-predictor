package com.ruoyi.gas.module.price.math;

public class PriceMath {
    private Data data;
    /**
     * 覆盖面积
     */
    private Double y;
    /**
     * 日均销量
     */
    private Double asv;

    public PriceMath(Data data) {
        this.data = data;
        calculate();
    }

    public Double getY() {
        return y;
    }

    public Double getAsv() {
        return asv;
    }

    private void calculate() {
        y = y();
        asv = asv();
    }

    private double a(int n) {
        double var1 = data.getInSalesVolume(n) - data.getInSalesVolume(n + 1);
        var1 /= data.getInSalesVolume(n);
        var1 += 1;
        return var1;
    }

    private double b(int n) {
        double var1 = 0;
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            var1 += data.getOutMoney(i, n) * data.getRouteFactor(n);
        }
        var1 -= data.getInMoney(n);
        return var1;
    }

    private double c(int n) {
        double var1 = 0;
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            var1 += data.getOutMoney(i, n) * data.getRouteFactor(n) * data.getDistance(i);
        }
        return var1;
    }

    private double y(int x) {
        double var1 = a(x) * c(x) - c(x + 1);
        double var2 = a(x) * b(x) - b(x + 1);
        return var1 / var2;
    }

    private double y() {
        int var1 = 1;
        int var2 = data.getPeriodNumber();
        for (int i = 0; i < var2; i++) {
            var1 *= y(i);
        }
        return Math.pow(var1, var2 - 2);
    }


    private double asv() {
        double e = 0;
        double f = 0;
        double var1 = 0;
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            var1 = 1 - data.getDistance(i);
            var1 /= y;
            e += data.getChaseMoney(i) * data.getRouteFactor(i) * var1 - data.getInTargetMoney();
            f += data.getPresentMoney(i) * data.getRouteFactor(i) * var1 - data.getInPresentMoney();
        }
        return (2 - e / f) - data.getPresentAverageSalesVolume();
    }
}
