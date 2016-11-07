package com.danone.comfit.adpter;

import java.util.List;

import android.content.Context;
import android.widget.TextView;

import com.danone.comfit.R;
import com.danone.comfit.common.bean.Feed;
import com.danone.comfit.holder.ViewHolder;

public class FeedAdpter extends CommonAdapter<Feed> {

	public FeedAdpter(Context context, List<Feed> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHolder helper, Feed item) {
		// TODO Auto-generated method stub

		TextView studen_id_textView = helper.getView(R.id.studen_id_textView);
		TextView studen_name_textView = helper.getView(R.id.studen_name_textView);
		TextView studen_age_textView = helper.getView(R.id.studen_age_textView);
		TextView studen_score_textView = helper.getView(R.id.studen_score_textView);
		TextView studen_fancy_textView = helper.getView(R.id.studen_fancy_textView);
		TextView studen_time_textView = helper.getView(R.id.studen_time_textView);

		if (item != null) {
			studen_id_textView.setText(item.getAppId() + "");
			studen_name_textView.setText(item.getFeedLeft()+"");
			studen_score_textView.setText(item.getFeedPrep() + "");
			studen_age_textView.setText(item.getDdat() + "");
		}

	}

}
