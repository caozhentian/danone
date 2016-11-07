package com.danone.comfit.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DownLoadApiSerivice {

	@GET
	Call<ResponseBody> downLoadFile(@Url String url);

	@GET("/img/test.jpg")
	Call<ResponseBody> downLoadFile();
}
