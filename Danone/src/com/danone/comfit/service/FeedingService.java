/**
 * 
 */
package com.danone.comfit.service;

import com.danone.comfit.respositoty.FeedingRespository;

/**
 * @author ztcao
 *
 */
public class FeedingService extends DiaryService{

	public FeedingService() {
		diaryRespository=new FeedingRespository();
	}

}
