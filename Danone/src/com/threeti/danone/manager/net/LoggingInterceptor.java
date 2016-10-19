package com.threeti.danone.manager.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {
	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException {
		Request request = chain.request();

		long t1 = System.nanoTime();
		System.out.println(String.format("发送请求: [%s] %s%n%s", request.url(), chain.connection(), request.headers()));

		Response response = chain.proceed(request);

		long t2 = System.nanoTime();
		
//		System.out.println(response.body().string());
		
		System.out.println(
				String.format("接收响应: [%s] %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

		return response;
	}
}
