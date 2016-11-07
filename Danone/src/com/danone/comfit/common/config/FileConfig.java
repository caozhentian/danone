package com.danone.comfit.common.config;

import java.io.File;

import android.os.Environment;

public class FileConfig {

	public static final String DANONE  = "danone" ;
	
	public static final String APP_DEV_BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + 
			                                      File.separator + DANONE + File.separator;  
    
    public static final String DB_NAME  = "danone.db" ;
    
    public static final String LOG_PATH = "log" ;
    
    public static void initFileConfig(){
    	
		File dirFile = new File(FileConfig.APP_DEV_BASE_DIR);  
        if (!dirFile.exists())  
            dirFile.mkdirs();
        
        File dirLog = new File(FileConfig.APP_DEV_BASE_DIR + File.separator + LOG_PATH);  
        if (!dirLog.exists())  
        	dirLog.mkdirs();
    	
    }
}
