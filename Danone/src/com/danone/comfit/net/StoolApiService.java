package com.danone.comfit.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;

import com.danone.comfit.common.bean.BaseModel;
import com.danone.comfit.common.bean.Stool;

/**
 * @author ztcao stool sync interface
 *
 */
public interface StoolApiService {

	@Headers({"Content-Type: application/json; charset=utf-8","Accept: application/json"})//sync one stool
	Call<BaseModel<Stool>> sync(@Body Stool stool) ;
	
	@Headers({"Content-Type: application/json; charset=utf-8","Accept: application/json"})//sync many stools
	Call<BaseModel<List<Stool>>> sync(@Body List<Stool> stool) ;
	
}
