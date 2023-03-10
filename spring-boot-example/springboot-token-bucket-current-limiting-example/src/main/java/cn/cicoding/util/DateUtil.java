package cn.cicoding.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    //锁对象
    private static final Object lockObj = new Object();

    //存放不同的日期模板格式的sdf的Map
    private static Map<String,ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String,ThreadLocal<SimpleDateFormat>>();

    //缺省日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    //全日期格式
    public static final String DATE_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //全日期格式无秒
    public static final String DATE_NO_SS_FORMAT = "yyyy-MM-dd HH:mm";

    /*
        *时间转字符串
        */
    public static String dateToString(Date date, String pattern) {
        if (pattern == null || "".equals(pattern)) {
            pattern = DATE_DEFAULT_FORMAT;
        }
        if (date != null) {
            return getSimpleDateFormat(pattern).format(date);
        } else {
            return null;
        }
    }

    public static String dateToString(Date date) {
        return dateToString(date,null);
    }

    /*
        *得到格式化对象
        */
    public static SimpleDateFormat getSimpleDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> t1 = sdfMap.get(pattern);
        synchronized(lockObj) {
            t1 = sdfMap.get(pattern);
            if (t1 == null) {
                t1 = new ThreadLocal<SimpleDateFormat>() {
                    @Override
                    protected SimpleDateFormat initialValue() {
                        return new SimpleDateFormat(pattern);
                    }
                };
                sdfMap.put(pattern,t1);
            }
            return t1.get();
        }
    }

    /*
        *字符串转时间
        */
    public static Date stringToDate(String date,String pattern) throws ParseException {
        if (pattern == null || "".equals(pattern)) {
            pattern = DATE_DEFAULT_FORMAT;
        }
        return getSimpleDateFormat(pattern).parse(date);
    }

    /*
        *字符串转时间
        */
    public static Date stringToDate(String date) throws ParseException {
        Date result = null;
        if (date == null) {
            return null;
        }
        if (date.length() == DATE_DEFAULT_FORMAT.length()) {
            try {
                result = stringToDate(date, DATE_DEFAULT_FORMAT);
            } finally {

            }
        }
        return result;
    }

    /*
        *获取当天日期
        */
    public static String getToday(String pattern) {
        return dateToString(new Timestamp(System.currentTimeMillis()),pattern);
    }
}