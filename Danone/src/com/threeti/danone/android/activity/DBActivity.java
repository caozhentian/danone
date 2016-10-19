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
import com.threeti.danone.android.adpter.StudentAdpter;
import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.android.service.StoolService;
import com.threeti.danone.android.service.StudentSerivice;
import com.threeti.danone.common.bean.DiaryResposityEvent;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.bean.Student;

import de.greenrobot.event.EventBus;

public class DBActivity extends BaseActivity {

	private ListView result_listView;
	private EditText name_editText, score_editText, age_editText, fancy_editText;
	private StudentSerivice studentSerivice;

	private StudentAdpter studentAdpter;

	private List<Student> list_students;

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
		list_students = new ArrayList<Student>();
		studentAdpter = new StudentAdpter(this, list_students, R.layout.item_student_layout);
	}

	@Override
	void initView() {
		// TODO Auto-generated method stub
		result_listView = (ListView) findViewById(R.id.result_listView);
		name_editText = (EditText) findViewById(R.id.name_editText);
		score_editText = (EditText) findViewById(R.id.score_editText);
		age_editText = (EditText) findViewById(R.id.age_editText);
		fancy_editText = (EditText) findViewById(R.id.fancy_editText);
		result_listView.setAdapter(studentAdpter);

	}

	public void addEvent(View view) {

		if (studentSerivice == null) {
			studentSerivice = new StudentSerivice();
		}
//		studentSerivice.addStudent(new Student(null, name_editText.getText().toString(),
//				Integer.valueOf(age_editText.getText().toString()), Double.valueOf(score_editText.getText().toString()),
//				fancy_editText.getText().toString(), System.currentTimeMillis() + ""), this);
		
		StoolService stoolService = new StoolService() ;
		Stool stool = new Stool() ;
		stool.setDdat(new Date()) ;
		stool.setStoolyn("Y") ;
		stool.setType(1)      ;
		stoolService.save(stool) ;
		
	}

	public void serachEvent(View view) {
		if (studentSerivice == null) {
			studentSerivice = new StudentSerivice();
		}
		List<Student> temps = studentSerivice.findAllStudents(this);
		if (temps != null) {
			list_students.clear();
			list_students.addAll(temps);
			studentAdpter.notifyDataSetChanged();
		}
		
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			StoolDao stoolDao  = daoSession.getStoolDao() ;
			List<Stool> stools = stoolDao.loadAll() ;
			int size = stools.size() ;
			StoolService stoolService = new StoolService() ;
			stoolService.delete(stools.get(0)) ;
		}
		
		StoolDao stoolDao  = daoSession.getStoolDao() ;
		List<Stool> stools = stoolDao.loadAll() ;
		int size = stools.size() ;
		size = 0 ;
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
			int i = 0 ;
			i++ ;
		}
	}

	
}
