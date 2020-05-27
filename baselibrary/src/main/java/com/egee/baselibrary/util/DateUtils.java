package com.egee.baselibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Date: 2019/10/10 11:39
 * @Author: YGZ
 * @Description: 日期格式化工具类
 * @Version:
 */
public final class DateUtils {

    /**
     * 英文简写（默认）如：2018-08-08
     */
    public static String PATTERN_SHORT = "yyyy-MM-dd";

    /**
     * 英文简写 如：20180808
     */
    public static String PATTERN_SHORT_WITHOUT_STRIKE = "yyyyMMdd";

    /**
     * 英文全称 如：2018-08-08 08:08:08
     */
    public static String PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的英文全称 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写 如：2018年08月08日
     */
    public static String PATTERN_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称 如：2018年08月08日 08时08分06秒
     */
    public static String PATTERN_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的中文全称
     */
    public static String PATTERN_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 使用默认日期格式返回当前日期
     *
     * @return 当前日期格式化字符串
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据指定日期格式返回当前日期
     *
     * @param pattern 日期格式
     * @return 当前日期格式化字符串
     */
    public static String getNow(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 获得默认的日期格式
     */
    private static String getPattern() {
        return PATTERN_SHORT;
    }

    /**
     * 使用默认日期格式格式化日期
     *
     * @param date 日期
     * @return 日期格式化字符串
     */
    public static String format(Date date) {
        return format(date, getPattern());
    }

    /**
     * 根据指定日期和日期格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期格式化字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // 设置时区
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        return sdf.format(date);
    }

    /**
     * 指定格式的格式化日期字符串转换成日期
     *
     * @param source
     * @param pattern
     * @return date
     */
    public static Date formatToDate(String source, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 两个时间戳是否是同一天 时间戳是long型的（11或者13）
     * @param currentTime 当前时间
     * @param lastTime 最后保存时间
     * @return
     */
    public static boolean isSameData(String currentTime, String lastTime) {
        try {
            Calendar nowCal = Calendar.getInstance();
            Calendar dataCal = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            Long nowLong = new Long(currentTime);
            Long dataLong = new Long(lastTime);
            String data1 = df1.format(nowLong);
            String data2 = df2.format(dataLong);
            java.util.Date now = df1.parse(data1);
            java.util.Date date = df2.parse(data2);
            nowCal.setTime(now);
            dataCal.setTime(date);
            return isSameDay(nowCal, dataCal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                    && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                    && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        } else {
            return false;
        }
    }

}