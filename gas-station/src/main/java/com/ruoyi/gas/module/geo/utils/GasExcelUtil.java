package com.ruoyi.gas.module.geo.utils;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.UtilException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.reflect.ReflectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Excel的工具类 <br>
 * 目前只支持导出全部为String的数据，且将数据转换成excel表的时候，只支持变成String格式
 *
 * @param <T> 封装数据的实体类
 */
public class GasExcelUtil<T> {
    private Workbook workbook;

    private Map<String, CellStyle> styleMap;
    private Pair[] fields;
    private Class<T> clazz;

    public GasExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
        init();
    }

    private void init() {
        this.workbook = new SXSSFWorkbook();
        this.styleMap = createStyles(this.workbook);
    }

    /**
     * 倒入Excel文件
     *
     * @param inputStream stream to load the Excel
     */
    public void loadForm(InputStream inputStream) {
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new UtilException("读取Excel失败，请联系管理员");
        }
    }

    /**
     * 获取一个指定的sheet的数据
     *
     * @param sheetName sheet名称
     * @return return a data list, which is not null <br>if the sheet is not exists, the method will return a empty list
     */
    public List<T> getData(String sheetName) {
        LinkedList<T> list = new LinkedList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        if (isNullSheet(sheet)) {
            return list;
        }

        for (int i = 1, length = sheet.getPhysicalNumberOfRows(); i < length; i++) {
            Row row = sheet.getRow(i);
            if (isRowEmpty(row)) {
                continue;
            }
            list.add(parseData(row));
        }
        ((SXSSFWorkbook)workbook).dispose();
        return list;
    }

    /**
     * 获取当前Excel文件的所有sheet数据
     *
     * @return a map from sheet name to data list
     */
    public Map<String, List<T>> getData() {
        HashMap<String, List<T>> map = new HashMap<>();
        for (int i = 0, length = workbook.getActiveSheetIndex() + 1; i < length; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            List<T> data = getData(sheetName);
            map.put(sheetName, data);
        }
        return map;
    }

    /**
     * 创建一个没有数据的空sheet
     *
     * @param sheetName sheet名称
     */
    public void createSheet(String sheetName, String... remarks) {
        createSheet(sheetName, null, remarks);
    }

    /**
     * 创建一个sheet，并且将数据写入
     *
     * @param sheetName sheet名称
     * @param list      数据
     */
    public void createSheet(String sheetName, List<T> list, String... remarks) {
        Sheet sheet = workbook.createSheet(sheetName);
        Row titleRow = sheet.createRow(0);
        String[] fieldExcelName = getFieldExcelName();
        for (int i = 0; i < fieldExcelName.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(fieldExcelName[i]);
            cell.setCellStyle(styleMap.get("header"));
        }

        if (Objects.nonNull(list)) {
            int rowIndex = 1;
            for (T t : list) {
                Row row = sheet.createRow(rowIndex++);
                Object[] fieldValues = getFieldValues(t);
                for (int i = 0; i < fieldValues.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(fieldValues[i].toString());
                    cell.setCellStyle(styleMap.get("data"));
                }
            }
        }

        if (Objects.nonNull(remarks)) {
            for (int i = 0; i < remarks.length; i++) {
                Row row = sheet.getRow(i + 1);
                if (isRowEmpty(row)) {
                    row = sheet.createRow(i + 1);
                }
                Cell cell = row.createCell(fieldExcelName.length + 2);
                cell.setCellStyle(styleMap.get("remark"));
                cell.setCellValue(remarks[i]);
            }
        }
    }

    /**
     * 将excel写出到OutputStream中
     *
     * @param out OutputStream instance
     */
    public void exportTo(OutputStream out) {
        try {
            workbook.write(out);
        } catch (IOException e) {
            throw new UtilException("导出Excel失败，请联系网站管理员！");
        } finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将excel写出到Http的entity body中
     *
     * @param response http response message
     */
    public void exportTo(HttpServletResponse response) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            exportTo(outputStream);
        } catch (IOException e) {
            throw new UtilException("导出Excel失败，请联系网站管理员！");
        }

    }

    /**
     * 读取一行数据（目前尚不可读取Excel的图片格式）
     *
     * @param row 行
     * @return 封装好的数据
     */
    private T parseData(Row row) {
        T t = createTInstance();
        Pair[] fields = getFields();

        for (int i = 0, length = fields.length; i < length; i++) {
            Cell cell = row.getCell(i);
            Class<?> fieldType = fields[i].field.getType();
            Object val = getCellValue(cell);

            if (String.class == fieldType) {
                String s = Convert.toStr(val);
                if (StringUtils.endsWith(s, ".0")) {
                    val = StringUtils.substringBefore(s, ".0");
                } else {
                    String dateFormat = fields[i].excelAnnotation.dateFormat();
                    if (StringUtils.isNotEmpty(dateFormat)) {
                        val = DateUtils.parseDateToStr(dateFormat, (Date) val);
                    } else {
                        val = Convert.toStr(val);
                    }
                }
            } else if ((Integer.TYPE == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val))) {
                val = Convert.toInt(val);
            } else if (Long.TYPE == fieldType || Long.class == fieldType) {
                val = Convert.toLong(val);
            } else if (Double.TYPE == fieldType || Double.class == fieldType) {
                val = Convert.toDouble(val);
            } else if (Float.TYPE == fieldType || Float.class == fieldType) {
                val = Convert.toFloat(val);
            } else if (BigDecimal.class == fieldType) {
                val = Convert.toBigDecimal(val);
            } else if (Date.class == fieldType) {
                if (val instanceof String) {
                    val = DateUtils.parseDate(val);
                } else if (val instanceof Double) {
                    val = DateUtil.getJavaDate((Double) val);
                }
            } else if (Boolean.TYPE == fieldType || Boolean.class == fieldType) {
                val = Convert.toBool(val, false);
            }

            ReflectUtils.invokeSetter(t, fields[i].field.getName(), val);
        }
        return t;
    }

    /**
     * 获取单元格值
     *
     * @param cell 单元格对象
     * @return 单元格值
     */
    public Object getCellValue(Cell cell) {
        Object val = "";
        try {
            if (StringUtils.isNotNull(cell)) {
                if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (DateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                    } else {
                        if ((Double) val % 1 != 0) {
                            val = new BigDecimal(val.toString());
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellType() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellType() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }

            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }

    public Object[] getFieldValues(T object) {
        Pair[] fields = getFields();
        Object[] objects = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                objects[i] = fields[i].field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return objects;
    }

    public String[] getFieldExcelName() {
        Pair[] fields = getFields();
        String[] fieldExcelName = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Excel annotation = fields[i].excelAnnotation;
            if (annotation == null) {
                fieldExcelName[i] = fields[i].field.getName();
            } else {
                fieldExcelName[i] = annotation.name();
            }
        }
        return fieldExcelName;
    }

    private T createTInstance() {
        T t;
        try {
            t = clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    static class Pair {
        Field field;
        Excel excelAnnotation;
    }

    private Pair[] getFields() {
        if (Objects.nonNull(fields)) {
            return fields;
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        LinkedList<Pair> pairs = new LinkedList<>();
        for (int i = 0; i < declaredFields.length; i++) {
            Pair pair = new Pair();
            pair.field = declaredFields[i];
            pair.field.setAccessible(true);
            pair.excelAnnotation = declaredFields[i].getAnnotation(Excel.class);
            if (Objects.nonNull(pair.excelAnnotation)) {
                pairs.add(pair);
            }
        }
        this.fields = pairs.toArray(new Pair[0]);
        return this.fields;
    }

    public boolean isNullSheet(Sheet sheet) {
        return sheet == null;
    }

    public boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        Pair[] fields = getFields();
        for (int i = row.getFirstCellNum(); i < fields.length; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    public static final Map<String, CellStyle> createStyles(Workbook wb) {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font totalFont = wb.createFont();
        totalFont.setFontName("Arial");
        totalFont.setFontHeightInPoints((short) 10);
        style.setFont(totalFont);
        styles.put("total", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        styles.put("data3", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font remarkFont = wb.createFont();
        remarkFont.setFontName("Arial");
        remarkFont.setFontHeightInPoints((short) 10);
        remarkFont.setColor(IndexedColors.RED.getIndex());
        style.setFont(remarkFont);
        styles.put("remark", style);
        return styles;
    }
}
