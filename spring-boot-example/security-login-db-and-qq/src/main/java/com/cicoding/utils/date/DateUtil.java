package com.cicoding.utils.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***
 * 日期工具类
 * 
 * @author cicoding.cn
 *
 */
public class DateUtil {
	
	private final static String SDF_YEAR = new String("yyyy");
	private final static String SDF_DAY = new String("yyyy-MM-dd");
	private final static String SDF_DAYS = new String("yyyyMMdd");
	private final static String SDF_TIME = new String("yyyy-MM-dd HH:mm:ss");
	private final static String MINUTE_TIME = new String("yyyy-MM-dd HH:mm");
	private final static String SDF_TIMES = new String("yyyyMMddHHmmss");
	private final static String SDF_YEAR_MONTH = new String("yyyy-MM");
	
	public static String formatDate(Date date, String formate) {
		return DateFormatUtils.format(date, formate);
	}
	
	/**
	 * 获取yyyyMMddHHmmss格式
	 *
	 * @return
	 */
	public static String getSdfTimes(Date date) {
		return formatDate(date, SDF_TIMES);
	}
	
	/***
	 * 获得yyyy-MM-dd HH:mm 格式
	 *
	 * @param date
	 * @return
	 */
	public static String getDateAndMinute(Date date) {
		return formatDate(date, MINUTE_TIME);
	}
	
	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYear() {
		return formatDate(new Date(), SDF_YEAR);
	}
	
	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay() {
		return formatDate(new Date(), SDF_DAY);
	}
	
	/***
	 * 获取YYYY-MM-DD格式
	 *
	 * @param date
	 * @return
	 */
	public static String getDay(Date date) {
		if (date != null) {
			return formatDate(date, SDF_DAY);
		} else {
			return "";
		}
	}
	
	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays() {
		return formatDate(new Date(), SDF_DAYS);
	}
	
	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime() {
		return getTime(new Date());
	}
	
	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime(Date date) {
		if (date != null) {
			return formatDate(date, SDF_TIME);
		} else {
			return "";
		}
	}
	
	/**
	 * @Title: compareDate
	 * @Description:
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             fh
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}
	
	/***
	 * 获得指定日期的当天开始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateStart(String date) {
		Date dDate = fomatDate(date);
		return getDateStart(dDate);
	}
	
	/****
	 * 获得指定日期的开始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/***
	 * 获得指定日期的结束时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateEnd(String date) {
		Date dDate = fomatDate(date);
		return getDateEnd(dDate);
	}
	
	/***
	 * 获得指定日期的结束时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}
	
	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date fomatDate(String date) {
		if (date == null) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// long aa=0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	
	/**
	 * <li>功能描述：时间相减得到天数
	 *
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;
		
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		
		return day;
	}
	
	/**
	 * 得到n天之后的日期
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);
		
		Calendar canlendar = Calendar.getInstance();
		canlendar.add(Calendar.DATE, daysInt);
		Date date = canlendar.getTime();
		
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);
		
		return dateStr;
	}
	
	/**
	 * 得到n天之后是周几
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);
		Calendar canlendar = Calendar.getInstance();
		canlendar.add(Calendar.DATE, daysInt);
		Date date = canlendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/***
	 * 获得指定日期的当天开始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartTimeOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/***
	 * 获得指定日期的当天开始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartTimeOfDate(String date) {
		Date dDate = fomatDate(date);
		return getStartTimeOfDate(dDate);
	}
	
	/***
	 * 获得指定日期的当前结束时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndTimeOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MILLISECOND, -1);
		return c.getTime();
	}
	
	/***
	 * 获得指定日期的当前结束时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndTimeOfDate(String date) {
		Date dDate = fomatDate(date);
		return getEndTimeOfDate(dDate);
	}
	
	/***
	 * 获得指定日期的当月开始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartTimeOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/***
	 * 获得指定日期的当月结束时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndTimeOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MILLISECOND, -1);
		return c.getTime();
	}
	
	/***
	 * 获得上个月的开始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastMonthStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/****
	 * 获得上个月的结束时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastMonthEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MILLISECOND, -1);
		return c.getTime();
	}
	
	/***
	 * 获得昨天起始时间
	 *
	 * @return
	 */
	public static Date getYesterdayStartTime() {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	
	/****
	 * 获得昨天结束时间
	 *
	 * @return
	 */
	public static Date getYesterdayEndTime() {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MILLISECOND, -1);
		return c.getTime();
	}
	
	/***
	 * 比较月份是否是本月
	 *
	 * @param date
	 * @return
	 */
	public static boolean isCurrentMonth(Date date) {
		if (date == null) {
			return false;
		}
		Date now = new Date();
		return formatDate(date, SDF_YEAR_MONTH).equals(formatDate(now, SDF_YEAR_MONTH));
	}
	
	/**
	 * 获得时间的月份
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	
	/***
	 * 时间格式 YYYY-mm
	 *
	 * @param date
	 * @return
	 */
	public static String getYearMonth(Date date) {
		if (date == null) {
			return "";
		}
		return formatDate(date, SDF_YEAR_MONTH);
	}
	
	/***
	 * 获取n时间加n个日后的日期Date
	 *
	 * @param date
	 * @return
	 */
	public static Date getAddDate(Date date, int num) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DATE, num);
		return cd.getTime();
	}
	
	/***
	 * 获取n时间减n个日后的日期Date
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinusDate(Date date, int day) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DATE, -day);
		return cd.getTime();
	}
	
	/***
	 * 获取n时间加n个分钟的日期Date
	 *
	 * @param date
	 * @return
	 */
	public static Date getAddMinuteDate(Date date, int num) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.MINUTE, num);
		return cd.getTime();
	}
	
	/***
	 * 获取当前时间加n个月后的日期Date
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateMonth(int num) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(new Date());
		cd.add(Calendar.MONTH, num);
		return cd.getTime();
	}
	
	public static String getDate(Date date) {
		return getDay(date);
	}
	
	public static String getDatetime(Date date) {
		return getTime(date);
	}
	
}
