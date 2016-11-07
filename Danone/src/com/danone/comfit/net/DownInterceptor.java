package com.danone.comfit.net;

import java.io.IOException;

import android.os.Looper;
import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.Response;

public class DownInterceptor implements Interceptor{
	@Override
	public Response intercept(Chain chain) throws IOException {
		// TODO Auto-generated method stub
		 okhttp3.Response orginalResponse = chain.proceed(chain.request());
		 
		 return orginalResponse.newBuilder()
                 .body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
                     @Override
                     public void onProgress(long progress, long total, boolean done) {
//                         Log.e(TAG, Looper.myLooper()+"");
                         Log.e("OP", "onProgress: " + "total ---->" + total + "done ---->" + progress );
                     }
                 }))
                 .build();
	}

}
