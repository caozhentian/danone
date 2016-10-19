/**
 * 
 */
package com.threeti.danone.common.util;

import com.threeti.danone.android.application.DanoneApplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author ztcao
 *
 */
public class NetUtil {

	public static boolean isConnected(){
		 Context context = DanoneApplication.getInstance().getApplicationContext();
	        // get wi-fi,net connection
	        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        
	        if (connectivityManager == null)
	        {
	            return false;
	        }
	        else
	        {
	            // 获取NetworkInfo对象
	            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
	            
	            if (networkInfo != null && networkInfo.length > 0)
	            {
	                for (int i = 0; i < networkInfo.length; i++)
	                {
	                    System.out.println(i + "===状态===" + networkInfo[i].getState());
	                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
	                    // 判断当前网络状态是否为连接状态
	                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
	                    {
	                        return true;
	                    }
	                }
	            }
	        }
	        return false;
	}
}
