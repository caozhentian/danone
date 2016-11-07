/**
 * 
 */
package com.danone.comfit.activity.home;

import android.os.Bundle;

import com.danone.comfit.R;
import com.danone.comfit.activity.BaseActivity;

/**
 * @author ztcao Home Summary screen
 *
 */
public class HomeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.act_home_layout);
		initView() ;
		initData() ;
	}
	
	
	@Override
	public void initView() {	
		initBottomNavigationBar() ;
	}


	@Override
	public void initData() {
		
	}

	private void initBottomNavigationBar() {


    }
}
