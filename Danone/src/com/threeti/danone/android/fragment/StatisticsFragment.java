package com.threeti.danone.android.fragment;

import java.util.Date;

import com.threeti.danone.android.service.StatisticsSerivce;
import com.threeti.danone.common.bean.TimeSpent;

/**
 * @author ztcao 如果Fragment 需要统计用户使用时间功能，需要继承
 *
 */
public abstract class StatisticsFragment extends BaseFragment{

    protected StatisticsSerivce statisticsSerivce ; 
	
	/**
	 * 子类根据需要设置不同的数据模型 ，如果是统计模块的使用时间，必须覆盖此方法
	 */
	protected void setStatistics(){
		statisticsSerivce.setStatisticsType(TimeSpent.APP_TYPE) ; 
	}
	
	@Override
	public void initData(){//子类必须调用父类的initData方法
		statisticsSerivce = new StatisticsSerivce() ;
		setStatistics() ;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Date endDate = new Date() ;
		statisticsSerivce.setEndDate(endDate) ;
		statisticsSerivce.statistics() ;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Date startDate = new Date() ;
		statisticsSerivce.setStartDate(startDate) ;
	}

	@Override  
	//如果嵌套到ViewPaper中，会回调此方法
    public void setUserVisibleHint(boolean isVisibleToUser) {  
       super.setUserVisibleHint(isVisibleToUser);  
       if (isVisibleToUser) {  //相当于Fragment的onResume 
    	   Date startDate = new Date() ;
   		   statisticsSerivce.setStartDate(startDate) ;
       } else {  
           //相当于Fragment的onPause  
    	   Date endDate = new Date() ;
   		   statisticsSerivce.setEndDate(endDate) ;
   		   statisticsSerivce.statistics() ;
       }  
    }  
}
