package com.danone.comfit.service;

import com.danone.comfit.common.bean.Crying;
import com.danone.comfit.respositoty.CryingRespository;

/**
 * @author ztcao
 *
 */
public class NotificationService extends DiaryService{

	public NotificationService() {
		diaryRespository=new CryingRespository();
	}

}
