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

	private List<Diary> diaries ;
	
	

	public DiaryQueryEvent(List<Diary> diaries) {
		super();
		this.diaries = diaries;
	}

	public List<Diary> getDiaries() {
		return diaries;
	}

	public void setDiaries(List<Diary> diaries) {
		this.diaries = diaries;
	}
	
	
}
