package com.threeti.danone.android.activity;

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
import com.threeti.danone.android.adpter.FeedAdpter;
import com.threeti.danone.android.adpter.MvnAdpter;
import com.threeti.danone.android.adpter.StudentAdpter;
import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.CryingDao;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.FeedDao;
import com.threeti.danone.android.db.dao.MvnDao;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.android.service.CryingService;
import com.threeti.danone.android.service.FeedingService;
import com.threeti.danone.android.service.MvnService;
import com.threeti.danone.android.service.StoolService;
import com.threeti.danone.android.service.StudentSerivice;
import com.threeti.danone.common.bean.Crying;
import com.threeti.danone.common.bean.DiaryResposityEvent;
import com.threeti.danone.common.bean.Feed;
import com.threeti.danone.common.bean.Mvn;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.bean.Student;

import de.greenrobot.event.EventBus;

public class MvnDBActivity extends BaseActivity {

	private ListView result_listView;
	private EditText name_editText, score_editText, age_editText, fancy_editText;
	private StudentSerivice studentSerivice;

	private MvnAdpter mvnAdpter;

	private List<Mvn> list_students;

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
	void initData() {
		// TODO Auto-generated method stub
		list_students = new ArrayList<Mvn>();

	}

	@Override
	void initView() {
		// TODO Auto-generated method stub
		result_listView = (ListView) findViewById(R.id.result_listView);
		name_editText = (EditText) findViewById(R.id.name_editText);
		score_editText = (EditText) findViewById(R.id.score_editText);
		age_editText = (EditText) findViewById(R.id.age_editText);
		fancy_editText = (EditText) findViewById(R.id.fancy_editText);

		name_editText.setHint("哭类型");
		age_editText.setHint("原因");
		fancy_editText.setHint("量");
	}

	public void addEvent(View view) {

		MvnService mvnService = new MvnService() ;
		Mvn mvn = new Mvn() ;
		mvn.setDdat(new Date()) ;
		mvn.setType(5) ;
		mvn.setDeleteReason(age_editText.getText().toString())      ;
		mvn.setCmroute(fancy_editText.getText().toString());
		mvnService.save(mvn) ;

	}
	public void deleteEvent(View view) {

		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			MvnDao mvnDao  = daoSession.getMvnDao() ;
			list_students = mvnDao.loadAll() ;
			int size = list_students.size() ;
			MvnService mvnService = new MvnService() ;
			if(size!=0){
				mvnService.delete(list_students.get(0)) ;
			}
			mvnAdpter = new MvnAdpter(this, list_students, R.layout.item_student_layout);
			result_listView.setAdapter(mvnAdpter);
			mvnAdpter.notifyDataSetChanged();
		}

	}
	public void serachEvent(View view) {

		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		MvnDao mvnDao  = daoSession.getMvnDao() ;
		list_students = mvnDao.loadAll() ;
		mvnAdpter = new MvnAdpter(this, list_students, R.layout.item_student_layout);
		result_listView.setAdapter(mvnAdpter);
		mvnAdpter.notifyDataSetChanged();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (studentSerivice != null) {
			studentSerivice.close();
		}
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
