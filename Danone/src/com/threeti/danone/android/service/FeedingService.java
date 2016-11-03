/**
 * 
 */
package com.threeti.danone.android.service;

import com.threeti.danone.android.respositoty.FeedingRespository;

/**
 * @author ztcao
 *
 */
public class FeedingService extends DiaryService{

	public FeedingService() {
		diaryRespository=new FeedingRespository();
	}

}
