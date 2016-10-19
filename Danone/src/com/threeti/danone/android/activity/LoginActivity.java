package com.threeti.danone.android.activity;

import com.threeti.danone.R;
import com.threeti.danone.android.respositoty.UserRespositoty;
import com.threeti.danone.android.service.UserSerivce;
import com.threeti.danone.common.bean.APIError;
import com.threeti.danone.common.bean.APIFail;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.LoginData;
import com.threeti.danone.common.constant.APIOperationCode;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import de.greenrobot.event.EventBus;

public class LoginActivity extends BaseActivity {

	private TextView longinfo_textView;
	private TextView account_textView;
	private TextView verification_code_textView;
	private UserSerivce userSerivice;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.login_layout);
		initData();
		initView();
		EventBus.getDefault().register(this);
	}

	@Override
	void initData() {
		// TODO Auto-generated method stub
		userSerivice = new UserSerivce();

	}

	@Override
	void initView() {
		// TODO Auto-generated method stub
		longinfo_textView = (TextView) findViewById(R.id.longinfo_textView);
		account_textView = (TextView) findViewById(R.id.account_textView);
		verification_code_textView = (TextView) findViewById(R.id.verification_code_textView);

	}

	public void submitLoginEvent(View view) {

		userSerivice.login(account_textView.getText().toString(), "3", verification_code_textView.getText().toString(),
				APIOperationCode.TO_USER_LOGIN);
	}

	/**
	 * Main 执行
	 * 
	 * @param action
	 */
	public void onEventMainThread(BaseModel<LoginData> model) {
		if (model != null) {
			if (model.getCode() == 200) {
				longinfo_textView.setText(model.getMessage() + "\n" + model.getData().getUseFlg());
			} else {
				longinfo_textView.setText(model.getMessage());
			}
		} else {
			longinfo_textView.setText("获取是数据失败！");
		}
	}

	public void onEventMainThread(APIError error) {
		if (error.getTodo_code() != APIOperationCode.TO_USER_LOGIN) {
			return;
		}
		longinfo_textView.setText(error.getThrowable().getMessage());
	}

	public void onEventMainThread(APIFail apiFail) {
		if (apiFail.getTodo_code() != APIOperationCode.TO_USER_LOGIN) {
			return;
		}
		longinfo_textView.setText(apiFail.getMessage() + "---------------" + apiFail.getCode());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
