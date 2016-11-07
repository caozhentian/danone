/**
 * 
 */
package com.danone.comfit.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author ztcao 
 *
 */
public class DateUtil {

	public static final String YYYY_MM_DD_FORMAT = "yyyy/MM/dd" ;
	
	public static Date getBeforeDate(int beforeDays){
		return getBeforeDate(new Date() , beforeDays) ;
	}
	
	/**yyyy-MM-dd  (去掉 小时 分钟 和秒 毫秒数设置为0)
	 * @param curDate
	 * @param beforeDays
	 * @return
	 */
	public static Date getBeforeDate(Date curDate , int beforeDays){
		if(beforeDays < 0 ){
			throw new IllegalArgumentException("beforeDays < 0") ;
		}
		if(curDate == null){
			return getBeforeDate(new Date() , beforeDays) ; 
		}
		Calendar date = Calendar.getInstance();
		date.setTime(curDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - beforeDays);
		date.set(Calendar.HOUR_OF_DAY, 0) ;
		date.set(Calendar.MINUTE, 0) ;
		date.set(Calendar.SECOND, 0) ;
		date.set(Calendar.MILLISECOND, 0) ;
		return date.getTime() ;
	}
	
	
	//WeekDay day month
	public static String format(Date date ){
		DateFormat dateformat = DateFormat.getDateInstance(DateFormat.LONG) ;
		return dateformat.format(date) ;
	}
	
	/**
	 * @param date
	 * @return yyyy/mm/dd
	 */
	public static String formatYYYYMMDD(Date date ){
		SimpleDateFormat sdf1 = new SimpleDateFormat(YYYY_MM_DD_FORMAT,Locale.CHINA) ;
		return sdf1.format(date) ;
	}
}
