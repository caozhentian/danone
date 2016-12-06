/**
 * 
 */
package com.danone.comfit.application;

import cn.jesse.nativelogger.NLogger;
import android.app.Activity;
import android.os.Bundle;
import android.app.Application;
/**
 * @author Administrator
 *
 */
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    /** 切换到后台的时间戳*/
    //public long screenOnTime = 0l;
    /** 当前是否有activity在运行 */
    public int count = 0;
    /** 屏幕点亮*/
    //public boolean screenOn = true;
    /** 是否是在后台*/
    public boolean isBackGround = false;
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        
    }

    @Override
    public void onActivityStarted(Activity activity) {
        NLogger.e("onActivityStarted");
        NLogger.e(activity.getClass().getName());
        count++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        NLogger.e("onActivityResumed");
        isBackGround = false;
        //screenOnTime = 0l;

    }

    @Override
    public void onActivityPaused(Activity activity) {
        NLogger.e("onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        NLogger.e("onActivityStopped");
        count--;
        if(count == 0){
            isBackGround = true;
            //screenOnTime = System.currentTimeMillis();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        NLogger.e("onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        NLogger.e("onActivityDestroyed");
    }
}
