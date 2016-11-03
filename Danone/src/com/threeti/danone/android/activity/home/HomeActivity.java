/**
 * 
 */
package com.threeti.danone.android.activity.home;

import android.os.Bundle;

import com.threeti.danone.R;
import com.threeti.danone.android.activity.BaseActivity;

/**
 * @author ztcao Home Summary screen
 *
 */
public class HomeActivity extends BaseActivity {

//	private BottomNavigationBar bottom_navigation_bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.home_layout);
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
//		bottom_navigation_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
//        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_SHIFTING);
//        bottom_navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
//        //设置默认颜色
////        bottom_navigation_bar
////                .setInActiveColor(R.color.colorInActive)//设置未选中的Item的颜色，包括图片和文字
////                .setActiveColor(R.color.colorPrimary)
////                .setBarBackgroundColor(R.color.colorBarBg);//设置整个控件的背景色
//        //设置徽章
//       // badge=new BadgeItem()
////                .setBorderWidth(2)//Badge的Border(边界)宽度
////                .setBorderColor("#FF0000")//Badge的Border颜色
////                .setBackgroundColor("#9ACD32")//Badge背景颜色
////                .setGravity(Gravity.RIGHT| Gravity.TOP)//位置，默认右上角
////               .setText("2")//显示的文本
////                .setTextColor("#F0F8FF")//文本颜色
////                .setAnimationDuration(2000)
////                .setHideOnSelect(true)//当选中状态时消失，非选中状态显示
//        ;
//        //添加选项
//        bottom_navigation_bar.addItem(new BottomNavigationItem(R.drawable.ic_launcher, "步行"))
//                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "骑行"))
//                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "公交"))
//                .initialise();//初始化BottomNavigationButton,所有设置需在调用该方法前完成
//        bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position) {//未选中 -> 选中
//            }
//
//            @Override
//            public void onTabUnselected(int position) {//选中 -> 未选中
//            }
//
//            @Override
//            public void onTabReselected(int position) {//选中 -> 选中
//            }
//        });

    }
}
