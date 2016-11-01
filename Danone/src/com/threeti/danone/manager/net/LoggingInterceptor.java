package com.threeti.danone.manager.net;

import java.io.IOException;

import cn.jesse.nativelogger.NLogger;

import android.os.Debug;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {
	public static final String TAG = "LoggingInterceptor" ;
	
	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException {
		Request request = chain.request();

		long t1  = Debug.threadCpuTimeNanos() ;
		String s = String.format("发送请求: [%s] %s%n%s", request.url(), chain.connection(), request.headers()) ;
		
		NLogger.d(TAG, s) ;
        String requestBody = request.body().toString() ;
        NLogger.d(TAG, requestBody) ;
		Response response = chain.proceed(request);

		long t2 = Debug.threadCpuTimeNanos() ;;
		
		String repomseHeader = String.format("接收响应: [%s] %.1fms%n%s", 
				                          response.request().url(), (t2 - t1) / 1000*1000*1000, response.headers()) ;
		NLogger.d(TAG, repomseHeader);
		NLogger.d(TAG, response.body().string());
          
		return response;
	}
}
