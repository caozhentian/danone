package com.danone.comfit.common.util;

import com.danone.comfit.application.DanoneApplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

/**
 * @author ztcao 
 *
 */
public class DeviceUtil {

	/**
	* device model name, e.g: GT-I9100
	* 
	* @return the user_Agent
	*/
	public static String getDevice() {
		return Build.MODEL;
	}


	/**
	* device factory name, e.g: Samsung
	* 
	* @return the vENDOR
	*/
	public static String getVendor() {
		return Build.BRAND;
	}


	/**
	* @return the SDK version
	*/
	public static int getSDKVersion() {
		return Build.VERSION.SDK_INT;
	}


	/**
	* @return the OS version
	*/
	public static String getOSVersion() {
		return Build.VERSION.RELEASE;
	}



	/**
	* Retrieves application's version number from the manifest
	* 
	* @return versionName
	*/
	public static String getVersion() {
		String version = "1.0.0";
		try {
			PackageInfo packageInfo = DanoneApplication.getInstance().getPackageManager().getPackageInfo(
					DanoneApplication.getInstance().getPackageName(), 0);
		    version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}
}
