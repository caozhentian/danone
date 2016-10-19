package com.threeti.danone.android.respositoty;

import java.io.File;

import com.threeti.danone.common.bean.APIError;
import com.threeti.danone.common.bean.APIFail;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.UpLoadImgResult;
import com.threeti.danone.common.bean.UpProgress;
import com.threeti.danone.common.constant.HandlerWhatCode;
import com.threeti.danone.common.util.NetUpLoadUtil;
import com.threeti.danone.manager.net.ProgressListener;
import com.threeti.danone.manager.net.RetrofitFactory;
import com.threeti.danone.manager.net.UpLoadApiService;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpLoadRespositoty {

	private static String TAG = UpLoadRespositoty.class.getSimpleName();

	private static UpLoadRespositoty upLoadRespositoty;

	private UpLoadApiService uploadApiSerivce;

	private UpLoadRespositoty() {
		Retrofit retrofit = RetrofitFactory.getBaseRetrofit();
		uploadApiSerivce = retrofit.create(UpLoadApiService.class);
	}

	public static synchronized UpLoadRespositoty getInstance() {
		if (upLoadRespositoty == null) {

			upLoadRespositoty = new UpLoadRespositoty();
		}
		return upLoadRespositoty;

	}

	public void upload(File file, final int todoCode, final Handler handler) {
		Call<BaseModel<UpLoadImgResult>> call = uploadApiSerivce
				.uploadImg(NetUpLoadUtil.fileToMultPartAndListener(file, new ProgressListener() {

					@Override
					public void onProgress(long progress, long totalLen, boolean hasDone) {
						// TODO Auto-generated method stub
						Log.e(TAG, "totalLen------------" + totalLen + "\n" + "progress--------------" + progress);

						Message msg = handler.obtainMessage();
						msg.what = HandlerWhatCode.WHAT_UP_PROGRESS;
						msg.obj = new UpProgress(progress, totalLen, hasDone, todoCode);
						handler.sendMessage(msg);

					}
				}));

		call.enqueue(new Callback<BaseModel<UpLoadImgResult>>() {

			@Override
			public void onResponse(Call<BaseModel<UpLoadImgResult>> arg0, Response<BaseModel<UpLoadImgResult>> arg1) {
				// TODO Auto-generated method stub

				if (arg1.isSuccessful() && arg1.errorBody() == null) {
					BaseModel<UpLoadImgResult> model = arg1.body();
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
			public void onFailure(Call<BaseModel<UpLoadImgResult>> arg0, Throwable arg1) {
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
