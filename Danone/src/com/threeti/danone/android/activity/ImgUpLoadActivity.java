package com.threeti.danone.android.activity;

import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.threeti.danone.R;
import com.threeti.danone.android.service.UpLoadSerivce;
import com.threeti.danone.common.bean.APIError;
import com.threeti.danone.common.bean.APIFail;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.UpLoadImgResult;
import com.threeti.danone.common.bean.UpProgress;
import com.threeti.danone.common.constant.APIOperationCode;
import com.threeti.danone.common.constant.HandlerWhatCode;
import com.threeti.danone.common.util.ImageUtils;

import de.greenrobot.event.EventBus;

public class ImgUpLoadActivity extends BaseActivity {

	private UpLoadSerivce uploadSerivce;

	private TextView upload_state_textView;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (msg.what == HandlerWhatCode.WHAT_UP_PROGRESS) {
				UpProgress progress = (UpProgress) msg.obj;

				upload_state_textView
						.setText("progress =" + progress.getProgress() + "\n" + "totalLen = " + progress.getTotalLen());
			}
		};
	};

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.img_upload_layout);
		initData();
		initView();
		EventBus.getDefault().register(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		uploadSerivce = new UpLoadSerivce();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		upload_state_textView = (TextView) findViewById(R.id.upload_state_textView);

	}

	public void submit_up_img_event(View view) {

		try {
			if (ImageUtils.saveImage(R.drawable.xx, this, "test")) {
				uploadSerivce.upLoadPic(new File("/sdcard/temp/" + "test" + ".jpg"), APIOperationCode.TO_UPLOAD_PIC,
						handler);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			showToast("保存图片异常！");

		}
	}

	/**
	 * Main 执行
	 * 
	 * @param action
	 */
	public void onEventMainThread(BaseModel<UpLoadImgResult> model) {
		if (model != null) {
			if (model.getCode() == 200) {
				upload_state_textView.setText(model.getMessage() + "\n");
			} else {
				upload_state_textView.setText(model.getMessage());
			}
		} else {
			upload_state_textView.setText("获取是数据失败！");
		}
	}

	public void onEventMainThread(APIError error) {
		if (error.getTodo_code() != APIOperationCode.TO_USER_LOGIN) {
			return;
		}
		upload_state_textView.setText(error.getThrowable().getMessage());
	}

	public void onEventMainThread(APIFail apiFail) {
		if (apiFail.getTodo_code() != APIOperationCode.TO_USER_LOGIN) {
			return;
		}
		upload_state_textView.setText(apiFail.getMessage() + "---------------" + apiFail.getCode());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
