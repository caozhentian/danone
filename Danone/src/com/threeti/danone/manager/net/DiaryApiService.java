package com.threeti.danone.manager.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;

import com.threeti.danone.common.bean.BaseModel;

public interface DiaryApiService<T> {

	@Headers({"Content-Type: application/json","Accept: application/json"})//sync one stool
	Call<BaseModel<T>> sync(@Body T stool) ;
	
	@Headers({"Content-Type: application/json","Accept: application/json"})//sync many stools
	Call<BaseModel<List<T>>> sync(@Body List<T> stool) ;
}
