package com.danone.comfit.service;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import com.danone.comfit.common.bean.TimeSpent;
import com.danone.comfit.common.util.DateUtil;
import com.danone.comfit.respositoty.StatisticsRespository;


/**
 * @author ztcao 统计的app使用时间和模块使用时间的业务逻辑
 *
 */
public class StatisticsSerivce {

	/**
	 * 统计类型   默认值：统计app的使用时间
	 */
	protected String       statisticsType  = TimeSpent.APP_TYPE;
	
	/**
	 * 开始使用时间
	 */
	protected Date         startDate       ;
	
	/**
	 * 结束使用时间
	 */
	protected Date         endDate         ;
	
	protected StatisticsRespository  statisticsRespository ;
	
	ExecutorService singleThreadExecutor = ExecutorServiceFactory.getExecutorService();  
	
	public StatisticsSerivce() {
		statisticsRespository = new StatisticsRespository() ;
	}

	/**根据 timeSpent 的type值，采用不同的策略
	 * @param timeSpent
	 */
	public void statistics(){
		final TimeSpent timeSpent = new TimeSpent() ;
		long startTime = startDate.getTime()  ;
		long endTime   = endDate.getTime()    ;
		//没有考虑 跨天的问题导致的统计 
		timeSpent.setTime((int) (endTime - startTime)/1000) ;
		timeSpent.setDdat(DateUtil.getBeforeDate(startDate ,0)) ;
		timeSpent.setType(statisticsType) ;
		singleThreadExecutor.execute(new Runnable() { //耗时操作，运行在单独的线程
			@Override
			public void run() {
				
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
		});
		
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
	
	public void sync(){
		statisticsRespository.sync() ;
	}
	
	
	
	public String getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}

	
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
