package com.ruoyi.gas.module.price.domain.framwork;

public enum OilType {
    Oil92("92号汽油", 92), Oil95("95号汽油", 95), Oil98("98号汽油", 98), Oil00("柴油", 00);
    private String typeName;
    private int typeNumber;

    OilType(String typeName, int typeNumber) {
        this.typeName = typeName;
        this.typeNumber = typeNumber;
    }

    public static OilType getOilByTypeName(String typeName){
        switch (typeName){
            case "92号汽油":
                return Oil92;
            case "95号汽油":
                return Oil95;
            case "98号汽油":
                return Oil98;
            case "柴油":
                return Oil00;
            default:
                return null;
        }
    }

    public static OilType getOilByTypeNumber(int typeNumber){
        switch (typeNumber){
            case 92:
                return Oil92;
            case 95:
                return Oil95;
            case 98:
                return Oil98;
            case 00:
                return Oil00;
            default:
                return null;
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
