package com.ruoyi.gas.module.price.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * 日期工具
 *
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
public class DateUtil {

    private DateUtil() {  }

    /**
     * 将 DateTime 转换为 Date
     */
    public static Date toDate(DateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.toDate();
    }

    /**
     * 将 Date 转换为 DateTime
     */
    public static DateTime toDateTime(Date date) {
        if (date == null) return null;
        return new DateTime(date.getTime());
    }
}
