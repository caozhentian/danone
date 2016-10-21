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

	public boolean isOverride(Crying crying){ // io 
		boolean isOverride = ((CryingRespository)diaryRespository).isOverride(crying) ;
		return  isOverride ;
	}
	
	public void override(Crying crying){ 
		((CryingRespository)diaryRespository).override(crying);
	}
}
