/**
 * 
 */
package com.danone.comfit.net;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import cn.jesse.nativelogger.NLogger;

import com.danone.comfit.R;
import com.danone.comfit.application.DanoneApplication;

/**
 * @author czt SslContextFactory for https
 *
 */
public class SslContextFactory {    
	
	private static final String CLIENT_TRUST_PASSWORD = "123456";//(信任证书密码，该证书默认密码是changeit)    
	private static final String CLIENT_AGREEMENT = "TLS";//(使用协议  )  
	private static final String CLIENT_TRUST_MANAGER = "X509";   
	private static final String CLIENT_TRUST_KEYSTORE = "BKS";    
	SSLContext sslContext = null;    
	public SSLContext getSslSocket() {   
		InputStream is = null ;
		try {            
			//get SSLContext instance            
			sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);            
			//get TrustManagerFactory X509  manager instance         
			TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);            
			//get BKS instance            
			KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);            
			is = DanoneApplication.getInstance().getResources().openRawResource(R.raw.danone);                         
		    tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());                     
			trustManager.init(tks);                        
			sslContext.init(null, trustManager.getTrustManagers(), null);        
		} catch (Exception e) {           
			NLogger.e("uncaughtException", e);
		} 
		finally{
			if(is != null){
				try {
					//close inputstream
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sslContext;    
	}
}
