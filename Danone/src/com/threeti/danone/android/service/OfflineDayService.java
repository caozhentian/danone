/**
 * 
 */
package com.threeti.danone.android.service;

/**
 * @author ztcao get offline save max days(获取离线存储的最大天数)
 *
 */
public class OfflineDayService {

	//default days
	public static final int OFFLINE_MAX_DAYS = 3 ;
	
	/**
	 * @return offline 最大天数
	 */
	public static final int getOfflineDay(){
		return OFFLINE_MAX_DAYS ;
	}
}
