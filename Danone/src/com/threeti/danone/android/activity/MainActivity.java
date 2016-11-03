package com.threeti.danone.android.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.threeti.danone.R;
import com.threeti.danone.android.activity.crying.CryingSumActivity;
import com.threeti.danone.android.activity.feed.FeedSumActivity;
import com.threeti.danone.android.activity.home.HomeActivity;
import com.threeti.danone.android.activity.stool.StoolSumActivity;
import com.threeti.danone.android.respositoty.StatisticsRespository;
import com.threeti.danone.android.wheelview.ArrayWheelAdapter;
import com.threeti.danone.android.wheelview.OnWheelChangedListener;
import com.threeti.danone.android.wheelview.WheelView;
import com.threeti.danone.android.widget.PopupWindowView;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.TimeSpent;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {
private PopupWindowView pop=null;
private LinearLayout ll_home;
private WheelView wheel1,wheel2,wheel3;
private Button btCN,btEN;
protected int w,h;
boolean langua=false;
	TextView textView;

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		WindowManager manage = getWindowManager();
		Display display = manage.getDefaultDisplay();
		w = display.getWidth();
		h = display.getHeight();
		ll_home=(LinearLayout) findViewById(R.id.ll_home);
		btCN=(Button) findViewById(R.id.btCN);
		btEN=(Button) findViewById(R.id.btEN);
		btCN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				langua=false;
			}
		});
		btEN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				langua=true;
			}
		});
		
		StatisticsRespository statisticsRespository = new StatisticsRespository() ;
		List<TimeSpent> timeSpents = statisticsRespository.getAllTimeSpent() ;
		int size = timeSpents.size() ;
		size++ ;
	}

	public void eventBusPost() {
		BaseModel<String> model = new BaseModel<String>();
		EventBus.getDefault().post(model);
	}

	public void onEventMainThread(BaseModel<String> action) {

	}

	public void CringEvent(View view) {
		startActivity(new Intent(this, CryingSumActivity.class));
	}

	public void feedEvent(View view) {
		startActivity(new Intent(this, FeedSumActivity.class));
	}

	public void mvnEvent(View view) {
		
	}

	public void DBEvent(View view) {
		startActivity(new Intent(this, StoolSumActivity.class));
		//showOrder(langua);
		
	}
	
	public void fragmentEvent(View view){
		startActivity(new Intent(this, HomeActivity.class));
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
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	private void showOrder(boolean langua) {
		if (pop != null) {
			return;
		}
		LayoutInflater inf = LayoutInflater.from(this);
		View view = inf.inflate(R.layout.popwin, null);
		wheel1=(WheelView)view.findViewById(R.id.wheel1);
		wheel2=(WheelView)view.findViewById(R.id.wheel2);
		wheel3=(WheelView)view.findViewById(R.id.wheel3);
		String[] year_little;
		String[] months_little;
		String[] day_little;
		if(langua){
			year_little =new String[]{ "October","123"};
			months_little =new String[] { "28", "27", "26" };
			day_little =new String[] { "2016","123" };
		}else{
			year_little =new String[]{ "2016","123"};
			months_little =new String[] { "10","123" };
			day_little =new String[] {"28", "27", "26" };
			
		}
		wheel1.setAdapter(new ArrayWheelAdapter<String>(year_little));
		wheel2.setAdapter( new ArrayWheelAdapter<String>(months_little));
		wheel3.setAdapter( new ArrayWheelAdapter<String>(day_little));
		wheel1.setCurrentItem(0);
		wheel1.setCyclic(false);
		wheel1.setVisibleItems(4);
		wheel2.setCurrentItem(0);
		wheel2.setCyclic(false);
		wheel2.setVisibleItems(4);
		wheel3.setCurrentItem(0);
		wheel3.setCyclic(false);
		wheel3.setVisibleItems(4);

		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				showToast("nian"+oldValue);
			}
		};
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				showToast("yue"+oldValue);
			}
		};
		OnWheelChangedListener wheelListener_day = new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				showToast("ri"+oldValue);
				if(oldValue==1){
					wheel1.setCurrentItem(1);
				}
			}
		};
		wheel1.addChangingListener(wheelListener_year);
		wheel2.addChangingListener(wheelListener_month);
		wheel3.addChangingListener(wheelListener_day);
		int textSize = 0;
		textSize = (int) getResources().getDimension(R.dimen.dim36);
		wheel1.TEXT_SIZE = textSize;
		wheel2.TEXT_SIZE = textSize;
		wheel3.TEXT_SIZE = textSize;
		pop=new PopupWindowView(this, w, h, view, ll_home, 2);
		pop.getwindow().setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				pop = null;
			}
		});
	}
}
