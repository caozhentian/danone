/**
 * 
 */
package com.threeti.danone.common.bean;

import java.util.List;

import com.threeti.danone.common.model.Diary;

/**
 * @author ztcao
 *
 */
public class DiaryQueryEvent {

	private List<? extends Diary> diaries ;
	
	

	public DiaryQueryEvent(List<? extends Diary> diaries) {
		super();
		this.diaries = diaries;
	}

	public List<? extends Diary> getDiaries() {
		return diaries;
	}

	public void setDiaries(List<? extends Diary> diaries) {
		this.diaries = diaries;
	}
	
	
}
