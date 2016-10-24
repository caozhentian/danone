package com.threeti.danone.manager.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.threeti.danone.common.bean.BaseModel;

public interface DiaryApiService<T> {

	@Headers({"Content-Type: application/json; charset=utf-8","Accept: application/json"})//sync one stool
	@POST("users/new")
	Call<BaseModel<T>> sync(@Body T stool) ;
	
	@Headers({"Content-Type: application/json; charset=utf-8","Accept: application/json"})//sync many stools
	@POST("users/new")
	Call<BaseModel<List<T>>> sync(@Body List<T> stool) ;
}
