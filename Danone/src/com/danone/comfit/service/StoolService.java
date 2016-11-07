/**
 * 
 */
package com.danone.comfit.service;

import com.danone.comfit.respositoty.StoolRespository;

/**
 * @author ztcao
 *
 */
public class StoolService extends DiaryService{

	public StoolService() {
		diaryRespository = new StoolRespository() ;
	}
	
	
}
