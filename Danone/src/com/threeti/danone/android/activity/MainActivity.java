package com.threeti.danone.android.activity;

import com.threeti.danone.R;
import com.threeti.danone.common.bean.BaseModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

	TextView textView;

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void eventBusPost() {
		BaseModel<String> model = new BaseModel<String>();
		EventBus.getDefault().post(model);
	}

	public void onEventMainThread(BaseModel<String> action) {

	}

	public void CringEvent(View view) {
		startActivity(new Intent(this, CryingDBActivity.class));
	}

	public void feedEvent(View view) {
		startActivity(new Intent(this, FeedDBActivity.class));
	}

	public void mvnEvent(View view) {
		
	}

	public void DBEvent(View view) {
		startActivity(new Intent(this, DBActivity.class));
	}

	/**
	 * Main 执行
	 * 
	 * @param action
	 */
	public void onEventMainThread(String action) {

	}

	/**
	 * 如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，
	 * 那么onEventBackground函数直接在该子线程中执行。
	 * 
	 * @param action
	 */
	public void onEventBackground(String action) {

	}

	/**
	 * 无论事件在哪个线程发布，都会创建新的子线程在执行
	 * 
	 * @param action
	 */
	public void onEventAsync(String action) {

	}

	/**
	 * 
	 * 那个线程发布，那个执行
	 */
	public void onEvent(String action) {

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	void initView() {
		// TODO Auto-generated method stub

	}

}
