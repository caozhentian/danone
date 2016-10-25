package com.threeti.danone.android.application;

import android.app.Application;

import com.threeti.danone.common.config.FileConfig;
import com.threeti.danone.common.util.LogUtil;

public class DanoneApplication extends Application {

    private static DanoneApplication danoneApplication ;
    
	@Override
	public void onCreate() {
		super.onCreate();
		danoneApplication = this ;
		FileConfig.initFileConfig() ;
		LogUtil.initLog() ;
	}
	
	
	/**after onCreate ,call this method
	 * @return
	 */
	public static DanoneApplication getInstance(){
		return danoneApplication ;
	}
}
