package com.danone.comfit.activity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.danone.comfit.R;

import android.os.Bundle;
import android.widget.ImageView;

public class ShowImgActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.show_img_activity);
		initData();
		initView();
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		ImageView showImg = (ImageView) findViewById(R.id.showImgView);

		Picasso.with(this)
				.load("http://hiphotos.baidu.com/%B3%F5%BC%B6%BE%D1%BB%F7%CA%D6/pic/item/929b56443840bfc6b3b7dc64.jpg")
				// .placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).centerCrop().resize(100,
				// 100)
				// .transform(new Transformation() {
				//
				// @Override
				// public Bitmap transform(Bitmap arg0) {
				// // TODO Auto-generated method stub
				// return null;
				// }
				//
				// @Override
				// public String key() {
				// // TODO Auto-generated method stub
				// return null;
				// }
				// }) .centerCrop()

				.into(showImg, new Callback() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError() {
						// TODO Auto-generated method stub

					}
				});
		;

	}

}
