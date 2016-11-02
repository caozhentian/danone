/**
 * 
 */
package com.threeti.danone.android.activity;

import com.threeti.danone.common.bean.TimeSpent;

/**
 * @author ztcao  统计app使用时间和每个模块的使用时间
 *
 */
public abstract class CryingStatisticsActvity extends StatisticsActvity{

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
}
