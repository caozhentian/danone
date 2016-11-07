package com.danone.comfit.net;


import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpsApiTest {

	@GET("examples/jsp")
	Call<Object> https();
	
	@GET("otn/leftTicket/init")
	Call<String> https12306();
}
