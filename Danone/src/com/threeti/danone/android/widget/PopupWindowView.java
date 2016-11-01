package com.threeti.danone.android.widget;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
public class PopupWindowView {
	private Context context;
	private PopupWindow popupWindow;

	// PopupWindow的width
	private int mPopupWindowWidth;
	// PopupWindow的height
	private int mPopupWindowHeight;
	private int flag;
	View view;
	View downview;

	public PopupWindowView(Context context, int w, int h, View view,
			View downview, int flag) {
		super();
		this.context = context;
		this.mPopupWindowWidth = w;
		this.mPopupWindowHeight = h;
		this.view = view;
		this.flag = flag;
		this.downview = downview;
		init();
	}

	public PopupWindowView(Context context, int w, int h, View view,
			View downview, int flag, ImageView iv) {
		super();
		this.context = context;
		this.mPopupWindowWidth = w;
		this.mPopupWindowHeight = h;
		this.view = view;
		this.flag = flag;
		this.downview = downview;
		init();
	}

	public void init() {
		if(popupWindow!=null){
			return;
		}
		popupWindow = new PopupWindow(view, mPopupWindowWidth,
				mPopupWindowHeight);
		// view.setBackgroundResource(R.drawable.popupwindow);
		ColorDrawable cd = new ColorDrawable(-0000);
		popupWindow.setBackgroundDrawable(cd);
		popupWindow.setOutsideTouchable(true);// 设置外部能点击
		if (flag == 1) {
			popupWindow.setFocusable(true);
			popupWindow.showAtLocation(downview,Gravity.CENTER, 0, 0);
		}else if(flag == 2){
			popupWindow.setFocusable(true);
			popupWindow.showAtLocation(downview,Gravity.BOTTOM, 0, 0);
			//			popupWindow.setAnimationStyle(R.style.popwin_anim_style2);
			//			popupWindow.setOnDismissListener(new OnDismissListener() {
			//
			//				@Override
			//				public void onDismiss() {
			//					downview.setSelected(false);;
			//					popupWindow = null;
			//				}
			//			});
		}else if(flag==3){
			popupWindow.setFocusable(true);
			popupWindow.showAsDropDown(downview);
		}else if(flag==4){
			popupWindow.setFocusable(false);
			popupWindow.showAsDropDown(downview);
		}
		popupWindow.update();

		// setting popupWindow 点击消失
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow.dismiss();
					return true;
				}
				return false;
			}
		});

	}
	public PopupWindow getwindow(){
		return popupWindow;
	}
	/**
	 * 点击消失方法
	 */
	public void popupWindowDismiss() {
		popupWindow.dismiss();
	}
	public Boolean popupWindowisshowing() {
		if (popupWindow.isShowing()) {
			return true;
		}
		return false;
	}
	// 下拉式 弹出 pop菜单 parent 右下角
	public void showAsDropDown(View parent) {
		popupWindow.showAsDropDown(parent, 0,
				// 保证尺寸是根据屏幕像素密度来的
				0);

		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 刷新状态
		popupWindow.update();
	}
}
