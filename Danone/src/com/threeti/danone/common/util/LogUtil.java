/**
 * 
 */
package com.threeti.danone.common.util;

import com.facebook.stetho.Stetho;
import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.common.config.Debug;
import com.threeti.danone.common.config.FileConfig;

import cn.jesse.nativelogger.NLogger;
import cn.jesse.nativelogger.formatter.SimpleFormatter;
import cn.jesse.nativelogger.logger.LoggerLevel;
import cn.jesse.nativelogger.util.CrashWatcher;

/**
 * @author ztcao log 
 *
 */
public class LogUtil {

	public static void initLog(){
		if(Debug.DEV_MODE){
			//for log
			NLogger.getInstance()
	        .builder()
	        .tag(FileConfig.DANONE)
	        .loggerLevel(LoggerLevel.DEBUG)
	        .fileLogger(true)
	        .fileDirectory(FileConfig.APP_DEV_BASE_DIR + FileConfig.LOG_PATH)
	        .fileFormatter(new SimpleFormatter())
	        .expiredPeriod(3)
	        .catchException(true, new CrashWatcher.UncaughtExceptionListener() {
	            @Override
	            public void uncaughtException(Thread thread, Throwable ex) {
	                NLogger.e("uncaughtException", ex);
	                android.os.Process.killProcess(android.os.Process.myPid());
	            }
	        })
	        .build();
			Stetho.initializeWithDefaults(DanoneApplication.getInstance().getApplicationContext()); //for dubug
		}
		else{
			NLogger.getInstance()
	        .builder()
	        .tag(FileConfig.DANONE)
	        .loggerLevel(LoggerLevel.DEBUG) //publish final version ,set ERROR
	        .build();
		}
		
	}
}
