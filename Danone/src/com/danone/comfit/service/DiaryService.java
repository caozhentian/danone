package com.danone.comfit.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.danone.comfit.common.bean.Diary;
import com.danone.comfit.common.bean.event.DiaryQueryEvent;
import com.danone.comfit.respositoty.DiaryRespository;

import de.greenrobot.event.EventBus;

/**
 * @author ztcao Diary superclass
 *
 */
public class DiaryService {

	protected DiaryRespository diaryRespository ;
	
	static ExecutorService singleThreadExecutor = ExecutorServiceFactory.getExecutorService() ;  
	
	public void save(final Diary diary){
		//other logic (开始的校验逻辑)
		
		singleThreadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				if(diary.getAppId() == null){
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
				List<? extends Diary> diary = diaryRespository.query(date, beforeDays) ;
				EventBus.getDefault().post(new DiaryQueryEvent(diary)) ;
			}
		}) ;
	}
}
