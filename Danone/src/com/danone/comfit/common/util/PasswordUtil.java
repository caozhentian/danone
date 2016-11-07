/**
 * 
 */
package com.danone.comfit.common.util;

import com.danone.comfit.common.config.DatabaseConfig;
import com.threeti.danone.jni.DanoneJni;

/** init  database ,keystore password
 * @author ztcao  
 *
 */
public class PasswordUtil {

	public static void init(){
		DatabaseConfig.ENCRYP_PASSWORD = DanoneJni.dbPasswordFromJNI() ;
		
	}
}
