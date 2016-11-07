package com.danone.comfit.application;

import android.app.Application;

import com.danone.comfit.common.config.Debug;
import com.danone.comfit.common.config.FileConfig;
import com.danone.comfit.common.util.LogUtil;
import com.danone.comfit.common.util.PasswordUtil;
import com.facebook.stetho.Stetho;

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
