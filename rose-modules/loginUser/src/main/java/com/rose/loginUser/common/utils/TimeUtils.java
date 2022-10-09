package com.rose.loginUser.common.utils;

/**
 * @author rose
 * @create 2021/8/7
 */

import com.rose.common.exception.RRException;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间工具类
 */
public class TimeUtils {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期为时间字符串
     *
     * @param nowDate 时间
     * @param format  字符串格式 例：yyyy-MM-dd HH:mm:ss
     * @return 时间字符串
     */
    public static String formatDate2String(@NonNull Date date, @NonNull String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 格式化毫秒值为时间字符串
     *
     * @param timeMillis 时间
     * @param format     字符串格式 例：yyyy-MM-dd HH:mm:ss
     * @return 时间字符串
     */
    public static String formatDate2String(@NonNull long timeMillis, @NonNull String format) {
        return formatDate2String(new Date(timeMillis), format);
    }

    /**
     * 解析日期字符串为毫秒值 错误时抛出异常
     *
     * @param dateStr 日期字符串
     * @param format  日期格式
     * @return 返回毫秒值
     */
    public static long parseString2Time(@NonNull String dateStr, @NonNull String format) {
        try {
            return new SimpleDateFormat(format, Locale.CHINA).parse(dateStr).getTime();
        } catch (ParseException e) {
            throw new RRException("格式化日期错误", e);
        }
    }

    /**
     * 解析日期字符串为日期对象
     *
     * @param dateStr 日期字符串
     * @param format  日期格式
     * @return 返回日期对象
     */
    public static Date parseString2Date(@NonNull String dateStr, @NonNull String format) {
        return new Date(parseString2Time(dateStr, format));
    }

    /**
     * 日期加指定间隔 TimeUtils.addDate(new Date(), "d", -1)减一天
     * @param date 日期
     * @param interval 间隔类型
     * @param num 间隔
     * @return
     */
    public static Date addDate(@NonNull Date date, @NonNull String interval, int num) {
        int field = 5;
        switch (interval) {
            case "y": {
                field = Calendar.YEAR;
                break;
            }
            case "M": {
                field = Calendar.MONDAY;
                break;
            }
            case "d": {
                field = Calendar.DATE;
                break;
            }
            case "h": {
                field = Calendar.HOUR;
                break;
            }
            case "m": {
                field = Calendar.MINUTE;
                break;
            }
            case "s": {
                field = Calendar.SECOND;
                break;
            }
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(field, num);
        Date addDate = calendar.getTime();
        return addDate;
    }
}
