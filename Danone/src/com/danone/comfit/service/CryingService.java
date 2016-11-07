package com.danone.comfit.service;

import com.danone.comfit.common.bean.Crying;
import com.danone.comfit.respositoty.CryingRespository;

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
