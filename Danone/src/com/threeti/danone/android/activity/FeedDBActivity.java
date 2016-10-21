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
import com.threeti.danone.android.adpter.StudentAdpter;
import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.CryingDao;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.FeedDao;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.android.service.CryingService;
import com.threeti.danone.android.service.FeedingService;
import com.threeti.danone.android.service.StoolService;
import com.threeti.danone.android.service.StudentSerivice;
import com.threeti.danone.common.bean.Crying;
import com.threeti.danone.common.bean.DiaryResposityEvent;
import com.threeti.danone.common.bean.Feed;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.bean.Student;

import de.greenrobot.event.EventBus;

public class FeedDBActivity extends BaseActivity {

	private ListView result_listView;
	private EditText name_editText, score_editText, age_editText, fancy_editText;
	private StudentSerivice studentSerivice;

	private FeedAdpter feedAdpter;

	private List<Feed> list_students;

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
		list_students = new ArrayList<Feed>();
		
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

		FeedingService cryingService = new FeedingService() ;
		Feed feed = new Feed() ;
		feed.setDdat(new Date()) ;
		feed.setDeleteReason(age_editText.getText().toString())      ;
		if(fancy_editText.getText().toString().isEmpty()){
			feed.setBeverNumber(0);
		}else{
			feed.setBeverNumber(Integer.valueOf(fancy_editText.getText().toString()));
		}
		cryingService.save(feed) ;
		
	}
	public void deleteEvent(View view) {
		
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			FeedDao cryingDao  = daoSession.getFeedDao() ;
			list_students = cryingDao.loadAll() ;
			int size = list_students.size() ;
			FeedingService stoolService = new FeedingService() ;
			if(size!=0){
				stoolService.delete(list_students.get(0)) ;
			}
			feedAdpter = new FeedAdpter(this, list_students, R.layout.item_student_layout);
			result_listView.setAdapter(feedAdpter);
			feedAdpter.notifyDataSetChanged();
		}
		
	}
	public void serachEvent(View view) {
		
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();
		
		FeedDao stoolDao  = daoSession.getFeedDao() ;
		list_students = stoolDao.loadAll() ;
		feedAdpter = new FeedAdpter(this, list_students, R.layout.item_student_layout);
		result_listView.setAdapter(feedAdpter);
		feedAdpter.notifyDataSetChanged();
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
		}
	}

	
}
