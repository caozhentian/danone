package com.threeti.danone.manager.net;

import javax.net.ssl.SSLSocketFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.threeti.danone.common.config.Debug;
import com.threeti.danone.common.config.IpConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ztcao generate Retrofit 
 *
 */
public class RetrofitFactory {

	public static Retrofit getBaseRetrofit() {
		OkHttpClient client = getOkHttpClient() ;
		Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create();//define datefamat
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl(IpConfig.BASIC_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
		return retrofit;

	}
	
	/** support https 
	 * @return 
	 */
	public static Retrofit getBaseRetrofitHttps() {
		SSLSocketFactory sslSocketFactory = new SslContextFactory().getSslSocket().getSocketFactory();
		@SuppressWarnings("deprecation")
		OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory);
		//new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, null) ;
		
		
		Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create();//define datefamat
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl(IpConfig.BASIC_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient.build()).build();
		return retrofit;
	}

	public static Retrofit getDownRetrofit(DownInterceptor downInterceptor) {
		OkHttpClient client = null ;
		if(Debug.DEV_MODE){
			client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor())
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
			client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor()).build();
		}
		else{
			client = new OkHttpClient().newBuilder().build();
		}
		return client ;
	}
}
