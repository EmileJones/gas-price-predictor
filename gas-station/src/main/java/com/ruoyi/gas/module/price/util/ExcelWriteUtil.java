package com.ruoyi.gas.module.price.util;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.util.Date;

public class ExcelWriteUtil {
    public static void write(Workbook workbook, String sheetName, int rowIndex, int colIndex, Object data) {
        if (data instanceof String) {
            writeString(workbook, sheetName, rowIndex, colIndex, data.toString());
        } else if (data instanceof Date) {
            writeDate(workbook, sheetName, rowIndex, colIndex, (Date) data);
        } else if (data instanceof Double) {
            writeDouble(workbook, sheetName, rowIndex, colIndex, ((Double) data).doubleValue());
        } else if (data instanceof Integer) {
            writeInt(workbook, sheetName, rowIndex, colIndex, ((Integer) data).intValue());
        } else if (data instanceof Float) {
            writeFloat(workbook, sheetName, rowIndex, colIndex, ((Float) data).floatValue());
        } else {
            writeString(workbook, sheetName, rowIndex, colIndex, data.toString());
        }
    }

    public static void writeString(Workbook workbook, String sheetName, int rowIndex, int colIndex, String data) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        cell.setCellValue(data);
    }

    public static void writeDouble(Workbook workbook, String sheetName, int rowIndex, int colIndex, double data) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        cell.setCellValue(data);
    }

    public static void writeFloat(Workbook workbook, String sheetName, int rowIndex, int colIndex, float data) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        cell.setCellValue(data);
    }

    public static void writeInt(Workbook workbook, String sheetName, int rowIndex, int colIndex, int data) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        cell.setCellValue(data);
    }

    public static void writeDate(Workbook workbook, String sheetName, int rowIndex, int colIndex, Date date) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        cell.setCellValue(date);
    }

    public static void writeFormula(Workbook workbook, String sheetName, int rowIndex, int colIndex, String formula) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        cell.setCellFormula(formula);
    }

    public static CellAddress getCellAddress(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        return cell.getAddress();
    }

    public static Row getRow(Workbook workbook, String sheetName, int rowIndex) {
        Sheet sheet = getSheet(workbook, sheetName);
        Row row = sheet.getRow(rowIndex);
        if (row == null)
            row = sheet.createRow(rowIndex);
        return row;
    }

    public static Sheet getSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null)
            sheet = workbook.createSheet(sheetName);
        return sheet;
    }

    public static Cell getCell(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        Row row = getRow(workbook, sheetName, rowIndex);
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(workbook.createFont());
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cell.setCellStyle(cellStyle);
        }
        return cell;
    }

    public static void setTextAlign(Workbook workbook, String sheetName, int rowIndex, int colIndex,
                                    HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setAlignment(horizontalAlignment);
        cellStyle.setVerticalAlignment(verticalAlignment);
    }

    public static void setTextSizeInPoints(Workbook workbook, String sheetName, int rowIndex, int colIndex, short size) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getCellStyle();
        XSSFFont font = cellStyle.getFont();
        font.setFontHeightInPoints(size);
    }

    public static void setBold(Workbook workbook, String sheetName, int rowIndex, int colIndex, boolean bold) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getCellStyle();
        XSSFFont font = cellStyle.getFont();
        font.setBold(bold);
    }

    public static void setRowHeightInPoints(Workbook workbook, String sheetName, int rowIndex, float height) {
        Row row = getRow(workbook, sheetName, rowIndex);
        row.setHeightInPoints(height);
    }

    public static void setColWidth(Workbook workbook, String sheetName, int colIndex, int width) {
        Sheet sheet = ExcelWriteUtil.getSheet(workbook, sheetName);
        sheet.setColumnWidth(colIndex, width);
    }

    public static void setMergeCell(Workbook workbook, String sheetName, int startRowIndex, int endRowIndex, int startColIndex, int endColIndex) {
        Sheet sheet = getSheet(workbook, sheetName);
        sheet.addMergedRegion(
                new CellRangeAddress(startRowIndex, endRowIndex, startColIndex, endColIndex)
        );
    }

    public static void setBorderTop(Workbook workbook, String sheetName, int rowIndex, int colIndex, BorderStyle borderStyle) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setBorderTop(borderStyle);
    }

    public static void setBorderBottom(Workbook workbook, String sheetName, int rowIndex, int colIndex, BorderStyle borderStyle) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setBorderBottom(borderStyle);
    }

    public static void setBorderLeft(Workbook workbook, String sheetName, int rowIndex, int colIndex, BorderStyle borderStyle) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setBorderLeft(borderStyle);
    }

    public static void setBorderRight(Workbook workbook, String sheetName, int rowIndex, int colIndex, BorderStyle borderStyle) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setBorderRight(borderStyle);
    }

    public static void setBackgroundColor(Workbook workbook, String sheetName, int rowIndex, int colIndex, short index) {
        Cell cell = getCell(workbook, sheetName, rowIndex, colIndex);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setFillForegroundColor(index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public static void setHidden(Workbook workbook, String sheetName, int colIndex, boolean isHidden) {
        Sheet sheet = getSheet(workbook, sheetName);
        sheet.setColumnHidden(colIndex, isHidden);
    }
}
