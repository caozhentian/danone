/**
 * 
 */
package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.StoolRespository;

/**
 * @author ztcao
 *
 */
public class StoolService extends DiaryService{

	public StoolService() {
		diaryRespository = new StoolRespository() ;
	}
	
	
}
