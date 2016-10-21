package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.CryingRespository;
import com.threeti.danone.android.respositoty.SpittingUpRespository;

/**
 * @author ztcao
 *
 */
public class SpittingUpService extends DiaryService{

	public SpittingUpService() {
		diaryRespository=new SpittingUpRespository();
	}

	
}
