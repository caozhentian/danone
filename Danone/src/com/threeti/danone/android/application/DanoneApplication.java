package com.threeti.danone.android.application;

import android.app.Application;

public class DanoneApplication extends Application {

    private static DanoneApplication danoneApplication ;
    
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		danoneApplication = this ;
	}
	
	/**after onCreate ,call this method
	 * @return
	 */
	public static DanoneApplication getInstance(){
		return danoneApplication ;
	}
}
