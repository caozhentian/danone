package com.threeti.danone.manager.net;

import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.LoginData;
import com.threeti.danone.common.bean.UpLoadImgResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UpLoadApiService {

 
	@Multipart
	@POST("store/uploadImg")
	Call<BaseModel<UpLoadImgResult>> uploadImg(@Part MultipartBody.Part part);

	@Multipart
	@POST("store/uploadImg")
	Call<BaseModel<UpLoadImgResult>> uploadImgs(@Body MultipartBody body);

}
