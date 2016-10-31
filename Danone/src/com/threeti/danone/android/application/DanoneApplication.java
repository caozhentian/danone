package com.threeti.danone.android.application;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.threeti.danone.common.config.Debug;
import com.threeti.danone.common.config.FileConfig;
import com.threeti.danone.common.util.LogUtil;
import com.threeti.danone.common.util.PasswordUtil;

public class DanoneApplication extends Application {

    private static DanoneApplication danoneApplication ;
    
	@Override
	public void onCreate() {
		super.onCreate();
		if(Debug.DEV_MODE){
			Stetho.initializeWithDefaults(this); //for dubug
		}
		danoneApplication = this ;
		FileConfig.initFileConfig() ;
		LogUtil.initLog() ;
		PasswordUtil.init() ;
		
	}
	
	
	/**after onCreate ,call this method
	 * @return
	 */
	public static DanoneApplication getInstance(){
		return danoneApplication ;
	}
}
