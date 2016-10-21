package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.CryingRespository;

/**
 * @author ztcao
 *
 */
public class CryingService extends DiaryService{

	public CryingService() {
		diaryRespository=new CryingRespository();
	}

	
}
