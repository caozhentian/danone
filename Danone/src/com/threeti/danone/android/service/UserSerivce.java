package com.threeti.danone.android.service;

import java.io.File;

import com.threeti.danone.android.respositoty.UserRespositoty;


public class UserSerivce {

	private static String  TAG = UserSerivce.class.getSimpleName();
	
	public void login(String mobile,  String device, String code,int todoCode){
 
		UserRespositoty.getInstance().login(mobile,device,code,todoCode);
	} 
	
	
	 
	
}
