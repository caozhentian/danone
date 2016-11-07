/**
 * 
 */
package com.danone.comfit.activity;

import java.util.Date;

import com.danone.comfit.common.bean.TimeSpent;
import com.danone.comfit.service.StatisticsSerivce;

/**
 * @author ztcao  统计app使用时间和每个模块的使用时间
 *
 */
public abstract class StatisticsActvity extends BaseActivity{

	protected StatisticsSerivce statisticsSerivce ; 
	
	/**
	 * 子类根据需要设置不同的数据模型 ，如果是统计模块的使用时间，必须覆盖此方法
	 */
	protected void setStatistics(){
		statisticsSerivce.setStatisticsType(TimeSpent.APP_TYPE) ; 
	}
	
	public void initData(){//子类必须调用父类的initData方法
		statisticsSerivce = new StatisticsSerivce() ;
		setStatistics() ;
	}
	
	@Override
	protected void onPause() {
		
		super.onPause();
		Date endDate = new Date() ;
		statisticsSerivce.setEndDate(endDate) ;
		statisticsSerivce.statistics() ;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Date startDate = new Date() ;
		statisticsSerivce.setStartDate(startDate) ;
	}
	
}
