/**
 * 
 */
package com.danone.comfit.fragment;

import com.danone.comfit.common.bean.TimeSpent;

/**
 * @author ztcao 如果Fragment 需要统计用户使用Crying模块的时间功能，需要继承
 *
 */
public class CryingStatisticsFragment extends StatisticsFragment{

	/**
	 * 子类根据需要设置不同的数据模型 ，如果是统计模块的使用时间，必须覆盖此方法
	 */
	protected void setStatistics(){
		statisticsSerivce.setStatisticsType(TimeSpent.CRYING_TYPE) ; 
	}
	
	@Override
	public void initView() {
	
	}

}
