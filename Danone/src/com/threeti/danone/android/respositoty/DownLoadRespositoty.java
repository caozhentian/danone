package com.threeti.danone.android.respositoty;

import com.threeti.danone.common.util.DownLoadUtil;
import com.threeti.danone.manager.net.DownInterceptor;
import com.threeti.danone.manager.net.DownLoadApiSerivice;
import com.threeti.danone.manager.net.RetrofitFactory;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownLoadRespositoty {

	public void downImg(DownInterceptor downInterceptor, final Context context) {
		Retrofit retrofit = RetrofitFactory.getDownRetrofit(downInterceptor);
		DownLoadApiSerivice loadApiSerivice = retrofit.create(DownLoadApiSerivice.class);

		Call<ResponseBody> call = loadApiSerivice.downLoadFile();

		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> arg0, Response<ResponseBody> arg1) {
				// TODO Auto-generated method stub
				if (arg1.isSuccessful() && arg1 != null) {

					boolean writtenToDisk = DownLoadUtil.writeResponseBodyToDisk(arg1.body(), context);
				} else {

				}

			}

			@Override
			public void onFailure(Call<ResponseBody> arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}
		});

	}
}
