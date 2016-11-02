/**
 * 
 */
package com.threeti.danone.android.activity;

import java.util.Date;

import com.threeti.danone.android.service.StatisticsSerivce;
import com.threeti.danone.common.bean.TimeSpent;
import com.threeti.danone.common.util.DateUtil;

/**
 * @author ztcao  统计app使用时间和每个模块的使用时间
 *
 */
public abstract class StatisticsActvity extends BaseActivity{

	protected StatisticsSerivce statisticsSerivce ; 
	/**
	 * 统计类型
	 */
	protected String       statisticsType ; 
	
	protected long         startTime    ;
	
	protected long         endTime      ;
	/**
	 * 子类根据需要设置不同的数据模型
	 */
	abstract protected void setStatistics() ;
	
	public void initData(){
		statisticsSerivce = new StatisticsSerivce() ;
		setStatistics() ;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Date endDate = new Date() ;
		endTime = endDate.getTime()/1000 ;
		TimeSpent timeSpent = new TimeSpent() ;
		timeSpent.setTime((int) (endTime - startTime)) ;
		timeSpent.setDdat(DateUtil.getBeforeDate(endDate ,0)) ;
		timeSpent.setType(statisticsType) ;
		saveStatistics(timeSpent) ;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Date startDate = new Date() ;
		startTime = startDate.getTime()/1000  ;
		
	}
	
	public void saveStatistics(TimeSpent timeSpent) {
		statisticsSerivce.save(timeSpent) ;
	}
}
