package com.threeti.danone.manager.net;

import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.LoginData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApiService {

	@FormUrlEncoded
	@POST("user/login")
	Call<BaseModel<LoginData>> login(@Field("mobile") String mobile, @Field("device") String device,
			@Field("code") String code);

	 

}
