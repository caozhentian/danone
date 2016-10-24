package com.threeti.danone.common.config;

import java.io.File;

import android.os.Environment;

public class FileConfig {

	public static final String APP_DEV_BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + 
			                                      File.separator + "danone" + File.separator;  
    
    public static final String DB_NAME = "danone.db" ;
    
    public static void initFileConfig(){
    	if(Debug.DEV_MODE){
    		File dirFile = new File(FileConfig.APP_DEV_BASE_DIR);  
            if (!dirFile.exists())  
                dirFile.mkdirs();
    	}
    }
}
