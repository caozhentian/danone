package com.threeti.danone.common.config;

import java.io.File;

import android.os.Environment;

public class FileConfig {

	public static final String DANONE  = "danone" ;
	
	public static final String APP_DEV_BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + 
			                                      File.separator + DANONE + File.separator;  
    
    public static final String DB_NAME  = "danone.db" ;
    
    public static final String LOG_PATH = "log" ;
    
    public static void initFileConfig(){
    	if(Debug.DEV_MODE){
    		File dirFile = new File(FileConfig.APP_DEV_BASE_DIR);  
            if (!dirFile.exists())  
                dirFile.mkdirs();
    	}
    }
}
