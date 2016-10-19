package com.threeti.danone.android.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.threeti.danone.android.respositoty.DiaryRespository;
import com.threeti.danone.common.bean.DiaryQueryEvent;
import com.threeti.danone.common.model.Diary;

import de.greenrobot.event.EventBus;

/**
 * @author ztcao Diary superclass
 *
 */
public class DiaryService {

	protected DiaryRespository diaryRespository ;
	
	ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();  
	
	public void save(final Diary diary){
		//other logic (开始的校验逻辑)
		
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				if(diary.isOffline()){
					diaryRespository.create(diary) ;
				}
				else{
					diaryRespository.modify(diary)  ;
				}
				
			}
		});
		
	}
	
	public void delete(final Diary diray){
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				diaryRespository.delete(diray) ;
			}
		});
		
	}
	
	public void query(final Date date , final int beforeDays){
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				List<Diary> diary = diaryRespository.query(date, beforeDays) ;
				EventBus.getDefault().post(new DiaryQueryEvent(diary)) ;
			}
		}) ;
	}
}
