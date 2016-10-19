package com.threeti.danone.manager.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

		OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor()).build();
		
		Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create();//define datefamat
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl(IpConfig.BASIC_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
		return retrofit;

	}

	public static Retrofit getDownRetrofit(DownInterceptor downInterceptor) {
		OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor())
				.addInterceptor(new DownInterceptor()).build();
		Retrofit retrofit = new Retrofit.Builder().baseUrl(IpConfig.BASIC_URL)
				.addConverterFactory(GsonConverterFactory.create()).client(client).build();
		return retrofit;
	}
}
