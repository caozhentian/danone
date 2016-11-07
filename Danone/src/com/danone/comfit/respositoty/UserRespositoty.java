package com.danone.comfit.respositoty;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.util.Log;

import com.danone.comfit.common.bean.APIError;
import com.danone.comfit.common.bean.APIFail;
import com.danone.comfit.common.bean.BaseModel;
import com.danone.comfit.common.bean.LoginData;
import com.danone.comfit.net.RetrofitFactory;
import com.danone.comfit.net.UserApiService;

import de.greenrobot.event.EventBus;

public class UserRespositoty {

	private static String TAG = UserRespositoty.class.getSimpleName();

	private static UserRespositoty UserRespositoty;

	private UserApiService userApiService;

	private UserRespositoty() {
		Retrofit retrofit = RetrofitFactory.getBaseRetrofit();
		userApiService = retrofit.create(UserApiService.class);
	}

	public static synchronized UserRespositoty getInstance() {
		if (UserRespositoty == null) {

			UserRespositoty = new UserRespositoty();
		}
		return UserRespositoty;

	}

	public void login(String mobile, String device, String code, final int todoCode) {

		Call<BaseModel<LoginData>> call = userApiService.login(mobile, device, code);

		call.enqueue(new Callback<BaseModel<LoginData>>() {

			@Override
			public void onResponse(Call<BaseModel<LoginData>> arg0, Response<BaseModel<LoginData>> arg1) {
				// TODO Auto-generated method stub

				if (arg1.isSuccessful() && arg1.errorBody() == null) {
					BaseModel<LoginData> model = arg1.body();
					if (model == null) {
						Log.e(TAG, "数据解析出现异常");
					} else {
						Log.e(TAG, "成功----------------");
					}
					postEvent(model);
				} else {
					postEvent(new APIFail(arg1.code(), arg1.message()));
					Log.e(TAG, arg1.code() + "");
					Log.e(TAG, arg1.message() + "");
				}
			}

			@Override
			public void onFailure(Call<BaseModel<LoginData>> arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				Log.e(TAG, "onFailure            " + arg1.getMessage());
				arg1.printStackTrace();
				postEvent(new APIError(todoCode, arg1));

			}
		});
	}

	
 
	private void postEvent(Object model) {
		EventBus.getDefault().post(model);
	}

}
