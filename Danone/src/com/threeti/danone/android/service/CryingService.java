package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.CryingRespository;
import com.threeti.danone.common.bean.Crying;

/**
 * @author ztcao
 *
 */
public class CryingService extends DiaryService{

	public CryingService() {
		diaryRespository=new CryingRespository();
	}

	public boolean isOverride(Crying crying){ // lightio 
		boolean isOverride = ((CryingRespository)diaryRespository).isOverride(crying) ;
		return  isOverride ;
	}
	
	public void override(final Crying crying){ 
		singleThreadExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				((CryingRespository)diaryRespository).override(crying);	
			}
		});
		//((CryingRespository)diaryRespository).override(crying);
	}
}
