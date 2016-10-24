/**
 * 
 */
package com.threeti.danone.manager.net;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.threeti.danone.R;
import com.threeti.danone.android.application.DanoneApplication;

/**
 * @author czt SslContextFactory for https
 *
 */
public class SslContextFactory {    
	public static final Logger loger = LoggerFactory.getLogger(SslContextFactory.class) ;
	
	private static final String CLIENT_TRUST_PASSWORD = "changeit";//(信任证书密码，该证书默认密码是changeit)    
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
			is = DanoneApplication.getInstance().getResources().openRawResource(R.raw.suplcerts);                         
		    tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());                     
			trustManager.init(tks);                        
			sslContext.init(null, trustManager.getTrustManagers(), null);        
		} catch (Exception e) {           
			loger.error(e.getMessage())    ;  
		} 
		finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sslContext;    
	}
}
