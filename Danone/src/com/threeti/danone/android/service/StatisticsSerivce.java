package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.StatisticsRespository;
import com.threeti.danone.common.bean.TimeSpent;


/**
 * @author ztcao 统计的app使用时间和模块使用时间的业务逻辑
 *
 */
public class StatisticsSerivce {

	protected StatisticsRespository  statisticsRespository ;
	
	public StatisticsSerivce() {
		statisticsRespository = new StatisticsRespository() ;
	}

	public void save(TimeSpent timeSpent){
		if(timeSpent.isModuleTimeSpent()){ // module statistics use time
			add(timeSpent) ; 
			TimeSpent appTimeSpent = new TimeSpent() ;
			appTimeSpent.setDdat(timeSpent.getDdat()) ;
			appTimeSpent.setTime(timeSpent.getTime()) ;
			appTimeSpent.setType(TimeSpent.APP_TYPE)  ;
			add(appTimeSpent) ;
		}
		else{ // app statistics use time
			add(timeSpent) ;
		}
	}
	
	/**保存一条timeSpent
	 * @param timeSpent
	 */
	private void add(TimeSpent timeSpent){
		TimeSpent dbTimeSpent = statisticsRespository.queryTimeSpent(timeSpent) ;
		if(dbTimeSpent != null){
			int time = dbTimeSpent.getTime() + timeSpent.getTime() ;
			dbTimeSpent.setTime(time) ;
			statisticsRespository.localUpdate(dbTimeSpent)  ;
		}
		else{
			statisticsRespository.loacalInsert(timeSpent)   ;
		}
	}
}
