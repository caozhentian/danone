package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.CryingRespository;
import com.threeti.danone.common.bean.Crying;

/**
 * @author ztcao
 *
 */
public class NotificationService extends DiaryService{

	public NotificationService() {
		diaryRespository=new CryingRespository();
	}

}
