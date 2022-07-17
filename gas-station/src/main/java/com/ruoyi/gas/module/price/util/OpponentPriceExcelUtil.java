package com.ruoyi.gas.module.price.util;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.gas.module.price.domain.OpponentPrice;
import com.ruoyi.gas.module.price.domain.dto.ExportExcelDTO;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OpponentPriceExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(OpponentPriceExcelUtil.class);

    public static Workbook generateExcel(Map<Date, List<ExportExcelDTO>> data) {
        Workbook workbook = new XSSFWorkbook();
        data.keySet().stream().sorted((a, b) -> (int) ((a.getTime() - b.getTime()) / 1000))
                .forEach(date -> {
                    String dateStr = new DateTime(date).toString("yyyy-MM-dd");
                    writeTitleIn0Row(workbook, dateStr);
                    writeData(workbook, dateStr, data.get(date));
                });
        return workbook;
    }

    public static Map<Date, List<ExportExcelDTO>> importExcel(MultipartFile file) {
        Map<Date, List<ExportExcelDTO>> data = new HashMap<>();
        try (InputStream inputStream = file.getInputStream()) {
            Workbook sheets = WorkbookFactory.create(inputStream);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            for (Sheet sheet : sheets) {
                Date period = formatter.parse(sheet.getSheetName());
                List<ExportExcelDTO> periodData = new ArrayList<>();
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    ExportExcelDTO dto = new ExportExcelDTO();
                    dto.setOutGasStationName(row.getCell(0).getStringCellValue());
                    dto.setPrice92(getCellValueAsDouble(row, 1));
                    dto.setPrice95(getCellValueAsDouble(row, 2));
                    dto.setPrice98(getCellValueAsDouble(row, 3));
                    dto.setPrice00(getCellValueAsDouble(row, 4));
                    periodData.add(dto);
                }
                data.put(period, periodData);
            }
        } catch (IOException | ParseException e) {
            log.error("竞争对手数据导入错误");
            throw new ServiceException("竞争对手数据导入错误");
        }
        return data;
    }

    /**
     * 保证客户获取到的数据是数值类型的
     * @param row 数据列
     * @param cellNum 单元格位置
     * @return 返回结果
     */
    private static double getCellValueAsDouble(Row row, int cellNum) {
        CellType cellType = row.getCell(cellNum).getCellType();
        try {
            switch (cellType) {
                case STRING:
                    return Double.parseDouble(row.getCell(cellNum).getStringCellValue());
                case NUMERIC:
                    return row.getCell(cellNum).getNumericCellValue();
                default:
                    return 0.0;
            }
        } catch (Exception e) {
            log.error("数据解析出现异常：" + e);
            return 0.0;
        }
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
