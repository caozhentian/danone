package com.threeti.danone.android.service;

import java.io.File;

import com.threeti.danone.android.respositoty.UpLoadRespositoty;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;


public class UpLoadSerivce {

	private static String  TAG = UpLoadSerivce.class.getSimpleName();
	
	public void upLoadPic(File file, final int todoCode,Handler handler ){
		UpLoadRespositoty.getInstance().upload(file,todoCode,handler);
	}
	
}
