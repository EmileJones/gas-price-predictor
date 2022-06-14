package com.ruoyi.gas.module.price.math;

public class PriceMath {
    private PriceData data;
    /**
     * 覆盖面积
     */
    private Double y;
    /**
     * 日均销量
     */
    private Double asv;

    public PriceMath(PriceData data) {
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

    private Double a(int n) {
        Double var1 = data.getInSalesVolume(n) - data.getInSalesVolume(n + 1);
        var1 /= data.getInSalesVolume(n);
        var1 += 1;
        return var1;
    }

    private Double b(int n) {
        Double var1 = 0.0;
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            var1 += data.getOutMoney(i, n) * data.getRouteFactor(i);
        }
        var1 -= data.getInMoney(n);
        return var1;
    }

    private Double c(int n) {
        Double var1 = 0.0;
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            var1 += data.getOutMoney(i, n) * data.getRouteFactor(i) * data.getDistance(i);
        }
        return var1;
    }

    private Double y(int x) {
        Double var1 = a(x) * c(x) - c(x + 1);
        Double var2 = a(x) * b(x) - b(x + 1);
        return var1 / var2;
    }

    private Double y() {
        int var1 = 1;
        int var2 = data.getPeriodNumber() - 2;
        for (int i = 0; i < var2; i++) {
            Double temp = y(i);
            if (!temp.isNaN(temp))
                var1 *= y(i);
        }
        return Math.pow(var1, var2);
    }


    private Double asv() {
        Double e = 0.0;
        Double f = 0.0;
        Double var1 = 0.0;
        for (int i = 0; i < data.getGasStationNumber(); i++) {
            var1 = 1 - data.getDistance(i);
            var1 /= y;
            e += data.getChaseMoney(i) * data.getRouteFactor(i) * var1 - data.getInTargetMoney();
            f += data.getPresentMoney(i) * data.getRouteFactor(i) * var1 - data.getInPresentMoney();
        }
        return (2 - e / f) - data.getPresentAverageSalesVolume();
    }
}
