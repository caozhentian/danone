package com.threeti.danone.android.adpter;

import java.util.List;

import com.threeti.danone.R;
import com.threeti.danone.android.holder.ViewHolder;
import com.threeti.danone.common.bean.Student;

import android.content.Context;
import android.widget.TextView;

public class StudentAdpter extends CommonAdapter<Student> {

	public StudentAdpter(Context context, List<Student> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHolder helper, Student item) {
		// TODO Auto-generated method stub

		TextView studen_id_textView = helper.getView(R.id.studen_id_textView);
		TextView studen_name_textView = helper.getView(R.id.studen_name_textView);
		TextView studen_age_textView = helper.getView(R.id.studen_age_textView);
		TextView studen_score_textView = helper.getView(R.id.studen_score_textView);
		TextView studen_fancy_textView = helper.getView(R.id.studen_fancy_textView);
		TextView studen_time_textView = helper.getView(R.id.studen_time_textView);

		if (item != null) {
			studen_id_textView.setText(item.getId() + "");
			studen_name_textView.setText(item.getName());
			studen_age_textView.setText(item.getAge() + "");
			studen_score_textView.setText(item.getScore() + "");
			studen_fancy_textView.setText(item.getFancy());
			studen_time_textView.setText(item.getTime());
		}

	}

}
