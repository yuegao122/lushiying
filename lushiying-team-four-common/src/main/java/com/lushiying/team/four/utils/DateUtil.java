package com.lushiying.team.four.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yuzhibo
 * @date 2019/6/10 11:03
 */
public class DateUtil {
    /**
     * 英文简写（默认）如：2019-06-10
     */
    private static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文全称 如：2019-06-10 11:11:06
     */
    private static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    private static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写 如：2019年6月10日
     */
    private static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称 如：2019年6月10日 11时17分32秒
     */
    private static String FORMAT_LONG_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    private static String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时mm分ss秒sss毫秒";

    /**
     * 获取默认的data pattern
     *
     * @return
     */
    public static String getDataPattern() {
        return FORMAT_LONG;
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String fromat(Date date, String pattern) {
        String result = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            result = df.format(date);
        }
        return result;
    }

    /**
     * 日期增加天数
     * @param date
     * @param days
     * @return
     */
    public static Date dateAdd(Date date,int days){
        if (date == null || days < 0){
            return new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }

    /**
     * 日期减少天数
     * @param date
     * @param days
     * @return
     */
    public static Date dateSub(Date date,int days){
        if (date == null || days < 0){
            return new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE) - days);
        return calendar.getTime();
    }
}
