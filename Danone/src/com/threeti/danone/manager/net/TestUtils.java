package com.threeti.danone.manager.net;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import com.threeti.danone.R;
import com.threeti.danone.android.application.DanoneApplication;

import okhttp3.OkHttpClient;

import android.content.Context;

public class TestUtils {

	 public static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

	        if (context == null) {
	            throw new NullPointerException("context == null");
	        }

	        //CertificateFactory用来证书生成
	        CertificateFactory certificateFactory;
	        try {
	            certificateFactory = CertificateFactory.getInstance("X.509");
	            //Create a KeyStore containing our trusted CAs
	            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	            keyStore.load(null, null);

	            for (int i = 0; i < certificates.length; i++) {
	                //读取本地证书
	                InputStream is = context.getResources().openRawResource(certificates[i]);
	                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));

	                if (is != null) {
	                    is.close();
	                }
	            }
	            //Create a TrustManager that trusts the CAs in our keyStore
	            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	            trustManagerFactory.init(keyStore);

	            //Create an SSLContext that uses our TrustManager
	            SSLContext sslContext = SSLContext.getInstance("TLS");
	            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
	            return sslContext.getSocketFactory();

	        } catch (Exception e) {

	        }
	        return null;
	    }
	 
	 public void onHttpCertficates(OkHttpClient.Builder builder) {
	        int[] certficates = new int[]{R.raw.key};
	        builder.socketFactory(getSSLSocketFactory(DanoneApplication.getInstance().getApplicationContext(), 
	        		certficates));
	    }
}
