package com.threeti.danone.android.activity.crying;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.threeti.danone.R;
import com.threeti.danone.android.adpter.CryingAdpter;
import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.CryingDao;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.service.CryingService;
import com.threeti.danone.common.bean.Crying;
import com.threeti.danone.common.bean.event.DiaryResposityEvent;

import de.greenrobot.event.EventBus;

public class CryingSumActivity extends CryingStatisticsActvity {

	private ListView result_listView;
	private EditText name_editText, score_editText, age_editText, fancy_editText;
	

	private CryingAdpter cryingAdpter;

	private List<Crying> list_students;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.db_layout);
		initData();
		initView();
		EventBus.getDefault().register(this) ;
	}

	@Override
	public void initData() {
		super.initData() ;
		list_students = new ArrayList<Crying>();
		cryingAdpter = new CryingAdpter(this, list_students, R.layout.item_student_layout);
	}

	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		result_listView = (ListView) findViewById(R.id.result_listView);
		name_editText = (EditText) findViewById(R.id.name_editText);
		score_editText = (EditText) findViewById(R.id.score_editText);
		age_editText = (EditText) findViewById(R.id.age_editText);
		fancy_editText = (EditText) findViewById(R.id.fancy_editText);
		result_listView.setAdapter(cryingAdpter);
		name_editText.setHint("哭类型");
		age_editText.setHint("开始时间");
		fancy_editText.setHint("结束时间");
	}

	public void addEvent(View view) {

		
		CryingService cryingService = new CryingService() ;
		Crying crying = new Crying() ;
		crying.setDdat(new Date()) ;
		crying.setCrytype(name_editText.getText().toString()) ;
		crying.setCrysttim(Integer.parseInt(age_editText.getText().toString()));
		crying.setCryentim(Integer.parseInt(fancy_editText.getText().toString()));
		cryingService.save(crying) ;
		
	}
	public void deleteEvent(View view) {
		
//		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
//		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();
//
//		if (daoSession != null) {
//			CryingDao cryingDao  = daoSession.getCryingDao() ;
//			list_students = cryingDao.loadAll() ;
//			int size = list_students.size() ;
//			CryingService cryingService = new CryingService() ;
//			cryingService.delete(list_students.get(0)) ;
//			cryingAdpter.notifyDataSetChanged();
//		}
		Crying crying = new Crying() ;
		crying.setDdat(new Date())   ;
		crying.setCrytype("override") ;
		crying.setCrysttim(15);
		crying.setCryentim(75);
		
		//CryingRespository cryingRespository = new CryingRespository() ;
		//cryingRespository.override(crying) ;
		
		CryingService cryingService = new CryingService() ;
		cryingService.override(crying) ;
	}

	public void serachEvent(View view) {
		
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		
		CryingDao cryingDao  = daoSession.getCryingDao() ;
		list_students = cryingDao.loadAll() ;
		cryingAdpter = new CryingAdpter(this, list_students, R.layout.item_student_layout);
		result_listView.setAdapter(cryingAdpter);
		cryingAdpter.notifyDataSetChanged();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		EventBus.getDefault().unregister(this) ;
	}
	
	public void onEventMainThread(DiaryResposityEvent even) {
		if(even.getEventType() == DiaryResposityEvent.EVENT_DIARY_LOCAL_OPP_SUCCESS){
			int i = 0 ;
			i++ ;
		}
	}

	
}
