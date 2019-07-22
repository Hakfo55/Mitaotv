package com.mountain.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
* @Title: DateUtil.java 
* @Package com.slarts.common.util 
* @Description: TODO 时间  
* @author porridge
* @date 2016年12月6日 下午6:14:31 
* @version V1.0
 */
public class DateUtil {
	public static DateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static DateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

	public static String getTimeDesc(Date date) {
		if (date == null) {
			return "";
		}
		Date now = new Date();
		long diff = (now.getTime() - date.getTime()) / 1000;
		if (diff > 6 * 30 * 24 * 60 * 60) {
			return yyyyMMdd.format(date);
		}
		if (diff > 30 * 24 * 60 * 60) {
			return diff / (30 * 24 * 60 * 60) + "月前";
		}
		if (diff > 24 * 60 * 60) {
			return diff / (24 * 60 * 60) + "天前";
		}
		if (diff > 60 * 60) {
			return diff / (60 * 60) + "小时前";
		}
		if (diff > 60) {
			return diff / 60 + "分钟前";
		}
		return "刚刚";
	}

	public static String yyyyMMdd(Date date) {
		return getFormatString(date, yyyyMMdd);
	}

	public static String yyyyMMddHHmmss(Date date) {
		return getFormatString(date, yyyyMMddHHmmss);
	}

	public static String yyyyMMddHHmm(Date date) {
		return getFormatString(date, yyyyMMddHHmm);
	}
	
	public static Date yyyyMMdd(String dateString){
		try{
			return yyyyMMdd.parse(dateString);
		}catch (Exception ignored) {
		}
		return null;
	}

	public static String getFormatString(Date date, DateFormat format) {
		if (date == null) {
			return "";
		}
		return format.format(date);
	}
	
	public static Date getYYYYMMDD(Integer day) {
		Calendar calendar = Calendar.getInstance();      
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(calendar.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
