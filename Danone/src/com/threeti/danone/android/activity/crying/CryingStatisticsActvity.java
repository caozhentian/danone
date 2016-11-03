/**
 * 
 */
package com.threeti.danone.android.activity.crying;

import com.threeti.danone.android.activity.StatisticsActvity;
import com.threeti.danone.common.bean.TimeSpent;

/**
 * @author ztcao  统计app使用时间和每个模块的使用时间
 *
 */
public abstract class CryingStatisticsActvity extends StatisticsActvity{

	@Override
	protected void setStatistics(){
		statisticsSerivce.setStatisticsType(TimeSpent.CRYING_TYPE) ; 
	}
	
}
