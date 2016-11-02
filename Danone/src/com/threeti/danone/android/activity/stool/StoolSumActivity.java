package com.threeti.danone.android.activity.stool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import cn.jesse.nativelogger.NLogger;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.threeti.danone.R;
import com.threeti.danone.android.adpter.StoolAdpter;
import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.android.service.StoolService;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.bean.TimeSpent;
import com.threeti.danone.common.bean.event.DiaryResposityEvent;
import com.threeti.danone.jni.DanoneJni;
import com.threeti.danone.manager.net.HttpsUtils;
import com.threeti.danone.manager.net.HttpsUtils.SSLParams;

import de.greenrobot.event.EventBus;

public class StoolSumActivity extends StoolStatisticsActvity {

	private ListView result_listView;
	private EditText name_editText, score_editText, age_editText, fancy_editText;

    private StoolAdpter stoolAdpter    ;
	private List<Stool> list_students ;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.db_layout);
		initData();
		initView();
		EventBus.getDefault().register(this) ;
		NLogger.i("nlogger teest") ;
	}

	@Override
	public void initData() {
		super.initData() ;
		list_students = new ArrayList<Stool>();
		NLogger.e("nlogger teest2") ;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					test() ;
					//testDeamon() ;
				} catch (IOException e) {
					NLogger.e( "adc" ,e);
				}
			}
		}).start() ;
		
		 String s = DanoneJni.dbPasswordFromJNI() + ":" +  DanoneJni.keyPasswordFromJNI() ;
		 
		 NLogger.e("nlogger s ") ;
		
	}

	private void test() throws IOException{
		//test 12306
		//InputStream bksFileIo = DanoneApplication.getInstance().getResources().openRawResource(R.raw.danone);
		InputStream bksFileIo = DanoneApplication.getInstance().getResources().openRawResource(R.raw.srca);
		InputStream bksFileIos[] = {bksFileIo} ;
		SSLParams sslParams = HttpsUtils.getSslSocketFactory(bksFileIos, null, null) ;
		//SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, bksFileIo, "pw12306") ;
		OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
		                                        .addNetworkInterceptor(new StethoInterceptor())
		                                        .sslSocketFactory(sslParams.sSLSocketFactory ,
		                                        		          sslParams.trustManager   );
		OkHttpClient client = okHttpClient.build() ;
		Request request = new Request.Builder()
        .url("https://kyfw.12306.cn/")
        .build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
		    throw new IOException("Unexpected code " + response);
		Headers responseHeaders = response.headers();
		for (int i = 0; i < responseHeaders.size(); i++) {
		    NLogger.e( (responseHeaders.name(i) + ": " + responseHeaders.value(i)));
		}
		NLogger.e(response.body().string());
	}
	
	private void testDeamon() throws IOException{
		System.setProperty ("jsse.enableSNIExtension", "false");
		//test 12306
		InputStream keyFileIo    = DanoneApplication.getInstance().getResources().openRawResource(R.raw.tomcat);
		InputStream[] keyFileIos = new InputStream[1] ;
		keyFileIos[0]            = keyFileIo ;
		InputStream bksFileIo = DanoneApplication.getInstance().getResources().openRawResource(R.raw.key);
		SSLParams sslParams = HttpsUtils.getSslSocketFactory(keyFileIos, bksFileIo, "123456") ;
		//SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null) ; //single auth
		
		//SSLParams sslParams = HttpsUtils.getSslSocketFactory(keyFileIos, null, null) ; //single auth
//		OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
//		                                        .sslSocketFactory(sslParams.sSLSocketFactory ,
//		                                        		          sslParams.trustManager   );
		OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
        .sslSocketFactory(sslParams.sSLSocketFactory   , sslParams.trustManager);
		okHttpClient.hostnameVerifier(new HostnameVerifier() {
	        @Override
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    });
		OkHttpClient client = okHttpClient.build() ;
		
		Request request = new Request.Builder()
        .url("https://192.168.29.106:8443/")
        .build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
		    throw new IOException("Unexpected code " + response);
		Headers responseHeaders = response.headers();
		for (int i = 0; i < responseHeaders.size(); i++) {
		    NLogger.e( (responseHeaders.name(i) + ": " + responseHeaders.value(i)));
		}
		NLogger.e(response.body().string());
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		result_listView = (ListView) findViewById(R.id.result_listView);
		name_editText = (EditText) findViewById(R.id.name_editText);
		score_editText = (EditText) findViewById(R.id.score_editText);
		age_editText = (EditText) findViewById(R.id.age_editText);
		fancy_editText = (EditText) findViewById(R.id.fancy_editText);


	}

	public void addEvent(View view) {


		StoolService stoolService = new StoolService() ;
		Stool stool = new Stool() ;
		stool.setDdat(new Date()) ;
		stool.setStoolyn("Y") ;      
		stoolService.save(stool) ;

	}
	public void deleteEvent(View view) {

		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			StoolDao stoolDao  = daoSession.getStoolDao() ;
			list_students = stoolDao.loadAll() ;
			int size = list_students.size() ;
			StoolService stoolService = new StoolService() ;
			if(size!=0){
				stoolService.delete(list_students.get(0)) ;
			}

		}

	}
	public void serachEvent(View view) { 
		
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();


		StoolDao stoolDao  = daoSession.getStoolDao() ;
		list_students = stoolDao.loadAll() ;
		stoolAdpter = new StoolAdpter(this, list_students, R.layout.item_student_layout);
		result_listView.setAdapter(stoolAdpter);
		stoolAdpter.notifyDataSetChanged();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		EventBus.getDefault().unregister(this) ;
	}

	public void onEventMainThread(DiaryResposityEvent even) {
		if(even.getEventType() == DiaryResposityEvent.EVENT_DIARY_LOCAL_OPP_SUCCESS){
			showToast("OK");
			int i = 0 ;
			i++ ;
		}
	}

	

}
