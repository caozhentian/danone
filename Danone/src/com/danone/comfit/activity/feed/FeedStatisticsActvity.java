/**
 * 
 */
package com.danone.comfit.activity.feed;

import com.danone.comfit.activity.StatisticsActvity;
import com.danone.comfit.common.bean.TimeSpent;

/**
 * @author ztcao  统计app使用时间和每个模块的使用时间
 *
 */
public abstract class FeedStatisticsActvity extends StatisticsActvity{

    @Override
	protected void setStatistics(){
		statisticsSerivce.setStatisticsType(TimeSpent.FEEDINF_TYPE) ; 
	}
}
