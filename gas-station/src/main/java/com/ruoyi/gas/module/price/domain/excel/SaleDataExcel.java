package com.ruoyi.gas.module.price.domain.excel;

import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class SaleDataExcel {
    private static final long serialVersionUID = 1L;

    @Excel(name = "物料名称")
    private String materialName;

    @Excel(name = "业务发生日期", dateFormat = "yyyy/MM/dd")
    private Date businessDate;

    @Excel(name = "销售数量KG", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal quantityKG;

    @Excel(name = "销售金额", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal amount;

    @Excel(name = "销售数量L", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal quantityL;

    @Excel(name = "销售单价", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal price;

    @Excel(name = "销售订单类型", defaultValue = SaleDataExcel.SaleType.BY_CASH)
    private String type;

    /**
     * 销售的订单类型
     */
    public static final class SaleType {
        /**
         * 现金销售
         */
        public static final String BY_CASH = "XJ";

        /**
         * 刷卡销售
         */
        public static final String BY_CARD = "YK";
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public BigDecimal getQuantityKG() {
        return quantityKG;
    }

    public void setQuantityKG(BigDecimal quantityKG) {
        this.quantityKG = quantityKG;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getQuantityL() {
        return quantityL;
    }

    public void setQuantityL(BigDecimal quantityL) {
        this.quantityL = quantityL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ImportSaleDataExcel{" +
                "materialName='" + materialName + '\'' +
                ", businessDate=" + businessDate +
                ", quantityKG=" + quantityKG +
                ", amount=" + amount +
                ", quantityL=" + quantityL +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
