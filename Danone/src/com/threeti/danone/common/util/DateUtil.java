/**
 * 
 */
package com.threeti.danone.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class DateUtil {

	public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd" ;
	
	public static Date getBeforeDate(int beforeDays){
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - beforeDays);
		return date.getTime() ;
	}
	
	public static String format(Date date , String format){
		SimpleDateFormat dft = new SimpleDateFormat(format);
		
		return dft.format(date) ;
	}
	
	
}
