package com.ruoyi.gas.module.price.domain.framwork;

import com.ruoyi.gas.module.price.exception.NotExistOilTypeException;
import org.apache.poi.ss.formula.functions.T;

public enum OilType {
    Oil92("92号汽油", 92), Oil95("95号汽油", 95), Oil98("98号汽油", 98), Oil00("柴油", 00);
    private String typeName;
    private int typeNumber;

    OilType(String typeName, int typeNumber) {
        this.typeName = typeName;
        this.typeNumber = typeNumber;
    }

    public static OilType getOilByTypeName(String typeName) {
        if (typeName.contains("92")) {
            return Oil92;
        } else if (typeName.contains("95")) {
            return Oil95;
        } else if (typeName.contains("98")) {
            return Oil98;
        } else if (typeName.contains(("柴油"))) {
            return Oil00;
        } else {
            throw new NotExistOilTypeException("不存在的石油类型: " + typeName);
        }
    }

    public static OilType getOilByTypeNumber(int typeNumber) {
        switch (typeNumber) {
            case 92:
                return Oil92;
            case 95:
                return Oil95;
            case 98:
                return Oil98;
            case 00:
                return Oil00;
            default:
                throw new NotExistOilTypeException("不存在的石油型号: " + typeNumber);
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public int getTypeNumber() {
        return typeNumber;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
