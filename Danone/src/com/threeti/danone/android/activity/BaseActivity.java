package com.threeti.danone.android.activity;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public abstract class BaseActivity extends FragmentActivity{

	public abstract void initData();
	public abstract void initView();
	
	
	
	public void showToast(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}
 