package cn.wangj.baslib.utils;

import android.text.format.DateFormat;

public class DateTimeUtil {
    private static String TAG = DateTimeUtil.class.getSimpleName();

    public final static String DATE_FORMAT_yyyyMM1 = "yyyyMM";
    public final static String DATE_FORMAT_yyyyMM2 = "yyyy-MM";
    public final static String DATE_FORMAT_yyyyMMdd1 = "yyyyMMdd";
    public final static String DATE_FORMAT_yyyyMMdd2 = "yyyy-MM-dd";
    public final static String DATE_FORMAT_yyyyMM_CH = "yyyy年MM月";
    public final static String DATE_FORMAT_yyyyMMdd_CH = "yyyy年MM月dd日";

    public final static String TIME_FORMAT_HHmm = "HH:mm";
    public final static String TIME_FORMAT_HHmmss = "HH:mm:ss";

    public final static String DATE_TIME_FORMAT1 = "yyyy-MM-dd HH:mm";
    public final static String DATE_TIME_FORMAT2 = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT3 = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String DATE_TIME_FORMAT4 = "MM-dd HH:mm";
    public final static String DATE_TIME_FORMAT5 = "yyyyMMddHHmm";
    public final static String DATE_TIME_FORMAT6 = "yyyyMMddHHmmss";

    public final static String DATE_TIME_FORMAT_LOG = "yyyy-MM-dd_HH:mm:ss";


    /**
     * get the time of device on designated format.
     *
     * @param format return format you want
     */
    public static String getDeviceDateTime(String format) {
        return DateFormat.format(format, System.currentTimeMillis()).toString();
    }

    public static String format() {
        return "";
    }
}
