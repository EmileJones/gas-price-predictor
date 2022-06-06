package com.ruoyi.gas.module.price.util;

import com.ruoyi.gas.module.price.domain.dto.ExportExcelDTO;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.util.*;

public class OpponentPriceExcelUtil {

    public static Workbook generateExcel(Map<Date, List<ExportExcelDTO>> data) {
        Workbook workbook = new XSSFWorkbook();
        Set<Date> dates = data.keySet();
        for (Date date : dates) {
            String dateStr = new DateTime(date).toString("yyyy-MM-dd");
            writeTitleIn0Row(workbook, dateStr);
            writeData(workbook, dateStr, data.get(date));
        }
        return workbook;
    }

    private static void writeTitleIn0Row(Workbook workbook, String sheetName) {
        OilType[] values = OilType.values();
        int i = 1;
        for (OilType oilType : values) {
            ExcelWriteUtil.writeString(workbook, sheetName, 0, i, oilType.getTypeName());
            ExcelWriteUtil.setTextAlign(workbook, sheetName, 0, i, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            i++;
        }
        ExcelWriteUtil.setHidden(workbook, sheetName, i, true);
    }

    private static void writeData(Workbook workbook, String sheetName, List<ExportExcelDTO> data) {
        int i = 1;
        OilType[] values = OilType.values();
        for (ExportExcelDTO dto : data) {
            ExcelWriteUtil.writeString(workbook, sheetName, i, 0, dto.getOutGasStationName());
            int j = 1;
            for (OilType oilType : values) {
                ExcelWriteUtil.writeDouble(workbook, sheetName, i, j, dto.getPrice(oilType));
                ExcelWriteUtil.setTextAlign(workbook, sheetName, i, j, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER);
                j++;
            }
            ExcelWriteUtil.writeString(workbook, sheetName, i, 0, dto.getOutGasStationName());
            i++;
        }
    }
}
