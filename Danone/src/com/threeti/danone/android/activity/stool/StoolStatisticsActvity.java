/**
 * 
 */
package com.threeti.danone.android.activity.stool;

import com.threeti.danone.android.activity.StatisticsActvity;
import com.threeti.danone.android.service.StatisticsSerivce;
import com.threeti.danone.common.bean.TimeSpent;

/**
 * @author ztcao  统计app使用时间和每个模块的使用时间
 *
 */
public abstract class StoolStatisticsActvity extends StatisticsActvity{

	protected StatisticsSerivce statisticsSerivce ;
	
	public void initData(){
		statisticsSerivce = new StatisticsSerivce() ;
	}
	
	/**
	 * 子类根据需要设置不同的数据模型
	 */
    protected void setStatistics(){
    	statisticsType  = TimeSpent.APP_TYPE ;
    }
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		
		super.onPause();
	}
	
	@Override
	public void saveStatistics(TimeSpent timeSpent) {
		statisticsSerivce.save(timeSpent) ;
	}
}
