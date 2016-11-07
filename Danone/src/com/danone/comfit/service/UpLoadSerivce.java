package com.danone.comfit.service;

import java.io.File;

import android.os.Handler;

import com.danone.comfit.respositoty.UpLoadRespositoty;


public class UpLoadSerivce {

	private static String  TAG = UpLoadSerivce.class.getSimpleName();
	
	public void upLoadPic(File file, final int todoCode,Handler handler ){
		UpLoadRespositoty.getInstance().upload(file,todoCode,handler);
	}
	
}
