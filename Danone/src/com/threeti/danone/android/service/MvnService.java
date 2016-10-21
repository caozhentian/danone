package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.CryingRespository;
import com.threeti.danone.android.respositoty.MvnRespository;

/**
 * @author ztcao
 *
 */
public class MvnService extends DiaryService{

	public MvnService() {
		diaryRespository=new MvnRespository();
	}

	
}
