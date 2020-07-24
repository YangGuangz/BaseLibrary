package com.ygz.baselibrary.util;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Date: 2019/10/10 17:10
 * @Author: YGZ
 * @Description: 时间相关工具类
 * @Version:
 */
public class TimeUtils {

    private static final long MINUTE = 60 * 1000;   // 1分钟
    private static final long HOUR = 60 * MINUTE;   // 1小时
    private static final long DAY = 24 * HOUR;      // 1天
    private static final long MONTH = 31 * DAY;     // 月
    private static final long YEAR = 12 * MONTH;    // 年

    /**
     * 日期格式化，转换为例如：1年前
     *
     * @param date
     * @return
     */
    public static String getFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - date.getTime();
        long r;
        if (diff > YEAR) {
            r = (diff / YEAR);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "个月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 获得网络时间
     *
     * @param spec    链接
     * @param pattern 日期格式化
     * @return
     */
    private String getNetworkTime(String spec, String pattern) {
        String format = null;
        // 取得资源对象
        URL url = null;
        try {
            url = new URL(StringUtils.isEmpty(spec) ?
                    // 中国科学院国家授时中心
                    "http://www.ntsc.ac.cn" :
                    spec);
            // 生成连接对象
            URLConnection urlConnection = url.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 发出连接
            urlConnection.connect();
            // 取得网站日期时间
            long currentTimeMillis = urlConnection.getDate();
            DateFormat sdf = new SimpleDateFormat(StringUtils.isEmpty(pattern) ?
                    "yyyy-MM-dd HH:mm:ss" :
                    pattern);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(currentTimeMillis);
            format = sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format;
    }


    /**
     * 获取系统时间
     *
     * @param pattern 日期格式化
     * @return
     */
    private String getSystemTime(String pattern) {
        long currentTimeMillis = System.currentTimeMillis();
        DateFormat sdf = new SimpleDateFormat(StringUtils.isEmpty(pattern) ?
                "yyyy-MM-dd HH:mm:ss" :
                pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        String format = sdf.format(calendar.getTime());
        return format;
    }

}