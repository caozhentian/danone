package com.threeti.danone.manager.net;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.threeti.danone.R;
import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.common.config.Debug;
import com.threeti.danone.common.config.IpConfig;
import com.threeti.danone.common.util.DateUtil;
import com.threeti.danone.manager.net.HttpsUtils.SSLParams;

/**
 * @author ztcao generate Retrofit 
 *
 */
public class RetrofitFactory {
    
	public static Retrofit getBaseRetrofit() {
		OkHttpClient client = getOkHttpClient() ;
		Gson gson = new GsonBuilder()
        .setDateFormat(DateUtil.YYYY_MM_DD_FORMAT)
        .create();//define datefamat
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl(IpConfig.BASIC_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
		return retrofit;

	}
	
	/** support https 
	 * @return 
	 */
	public static Retrofit getBaseRetrofitHttps() {
		//first solution
//		SSLSocketFactory sslSocketFactory = new SslContextFactory().getSslSocket().getSocketFactory();
//		@SuppressWarnings("deprecation")
//		OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory);
		
		//second solution
		InputStream bksFileIo = DanoneApplication.getInstance().getResources().openRawResource(R.raw.danone);
		SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, bksFileIo, "pw12306") ;
		OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
		                                        .sslSocketFactory(sslParams.sSLSocketFactory ,
		                                        		          sslParams.trustManager   );
		
		Gson gson = new GsonBuilder()
        .setDateFormat(DateUtil.YYYY_MM_DD_FORMAT)
        .create();//define datefamat
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl(IpConfig.BASIC_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient.build()).build();
		return retrofit;
	}

	public static Retrofit getDownRetrofit(DownInterceptor downInterceptor) {
		OkHttpClient client = null ;
		if(Debug.DEV_MODE){
			client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor())
				.addInterceptor(new StethoInterceptor())
				.addInterceptor(new DownInterceptor()).build();
		}
		else{
			client = new OkHttpClient().newBuilder()
					.addInterceptor(new DownInterceptor()).build();
		}
		Retrofit retrofit = new Retrofit.Builder().baseUrl(IpConfig.BASIC_URL)
				.addConverterFactory(GsonConverterFactory.create()).client(client).build();
		return retrofit;
	}
	
	private static OkHttpClient getOkHttpClient(){
		OkHttpClient client = null ;
		if(Debug.DEV_MODE){
			client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor())
					                   .addInterceptor(new StethoInterceptor()).build();
		}
		else{
			client = new OkHttpClient().newBuilder().build();
		}
		return client ;
	}
}
