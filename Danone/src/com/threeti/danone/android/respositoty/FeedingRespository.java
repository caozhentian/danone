/**
 * 
 */
package com.threeti.danone.android.respositoty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.content.Context;

import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.FeedDao;
import com.threeti.danone.android.db.dao.FeedDao.Properties;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.Feed;
import com.threeti.danone.common.model.Diary;
import com.threeti.danone.common.util.DateUtil;
import com.threeti.danone.manager.net.RetrofitFactory;
import com.threeti.danone.manager.net.StoolApiService;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author ztcao
 *
 */
public class FeedingRespository extends DiaryRespository {

	private static Logger loger = LoggerFactory
			.getLogger(FeedingRespository.class);
	
	@Override
	protected boolean localDelete(Diary diary) {
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			FeedDao feedDao = daoSession.getFeedDao() ;
			if (feedDao != null) {
				try{
					feedDao.delete((Feed)diary) ;
					isDeleteSucess = true ;
				}catch(Exception e){
					loger.debug(e.toString()) ;
				}
			}
		}
		return isDeleteSucess;
	}

	@Override
	protected boolean localUpdate(Diary diary) {
		boolean isUpdateSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			FeedDao feedDao = daoSession.getFeedDao() ;
			if (feedDao != null) {
				try{
					feedDao.update((Feed)diary);
					isUpdateSucess = true ;
				}catch(Exception e){
					loger.debug(e.toString()) ;
				}
			}
		}
		return isUpdateSucess;
	}

	@Override
	protected boolean loacalInsert(Diary diary) {
		boolean isInsertSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			FeedDao feedDao = daoSession.getFeedDao() ;
			if (feedDao != null) {
				try{
					long result_id = feedDao.insert((Feed)diary);
					if (result_id > 0) {
						isInsertSucess = true;
				}
				}catch(Exception e){
					loger.debug(e.toString()) ;
				}
			}
		}
		return isInsertSucess;
	}

	@Override
	protected boolean sync(Diary diary) {//upload diary to server
		boolean isSyncSucess =  false;
//		Retrofit retrofit = RetrofitFactory.getBaseRetrofit();
//		StoolApiService stoolApiService = retrofit.create(StoolApiService.class);
//		Call<BaseModel<Feed>> call     = stoolApiService.sync() ;
//		try {
//			Response<BaseModel<Feed>>  response = 	call.execute();
//			if(response.isSuccessful() && response.errorBody() == null){
//				BaseModel<Feed> baseModel = response.body() ;
//				Feed feed      = baseModel.getData() ;
//				//update diary serverId
//				diary.setServerId(feed.getServerId()) ;
//				isSyncSucess = true ;
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			loger.debug(e.toString()) ;
//			e.printStackTrace();
//		}
		return isSyncSucess;
	}

	@Override
	protected List<? extends Diary> loacalQuery(Date date, int beforeDays) {
		Date curDate = DateUtil.getBeforeDate(date ,beforeDays) ;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				FeedDao feedDao = daoSession.getFeedDao() ;
				List<Feed> feed = feedDao.queryBuilder().where(Properties.Ddat.ge(curDate.getTime())).list() ;
				return feed ;
		    }catch(Exception e){
		    	e.printStackTrace() ;
		    }
		}
		return Collections.emptyList() ;
	}

	@Override
	protected List<Diary> serverQuery(Date date, int beforeDays) {
		
		return Collections.emptyList() ;
	}

	@Override
	public void sync() {
		// TODO Auto-generated method stub
//		Date date = new Date() ;
//		int beforeDays = 3 ; //default 3 
//		List<Diary> diaries = loacalQuery(date, beforeDays) ;
//		if(diaries == null || diaries.size() == 0){
//			return true ;
//		}
//		for(Diary diary : diaries){
//			sync(diary) ;
//		}
//		return false;
	}

	@Override
	protected boolean loacalInsert(List<? extends Diary> diaries) {
		
		if( diaries == null || diaries.size() == 0 ){
			return true ;
		}
		
		boolean isInsertSucess =  false;
		
		List<Feed> feeds     = convFeed(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			FeedDao feedDao = daoSession.getFeedDao() ;
			if (feedDao != null) {
				try{
					feedDao.insertInTx(feeds) ;
					isInsertSucess = true;
				}catch(Exception e){
					loger.debug(e.toString()) ;
				}
			}
		}
		return isInsertSucess;
	}

	
	public List<Feed> convFeed(List<? extends Diary> diaries){
		List<Feed> feeds     = new   ArrayList<Feed>(diaries.size()) ;  
		for(Diary diary : diaries){
			feeds.add((Feed)diary) ;
		}
		return feeds ;
	}

	@Override
	protected boolean localDelete(List<? extends Diary> diaries) {
		if( diaries == null || diaries.size() == 0 ){
			return true ;
		}
		
		boolean isDeleteSucess =  false;
		
		List<Feed> feeds     = convFeed(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			FeedDao feedDao = daoSession.getFeedDao() ;
			if (feedDao != null) {
				try{
					feedDao.deleteInTx(feeds) ;
					isDeleteSucess = true;
				}catch(Exception e){
					loger.debug(e.toString()) ;
				}
			}
		}
		return isDeleteSucess;
	}

	@Override
	protected boolean localUpdate(List<? extends Diary> diaries) {
		if( diaries == null || diaries.size() == 0 ){
			return true ;
		}
		
		boolean isUpdateSucess =  false;
		
		List<Feed> feeds     = convFeed(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			FeedDao feedDao = daoSession.getFeedDao() ;
			if (feedDao != null) {
				try{
					feedDao.updateInTx(feeds) ;
					isUpdateSucess = true;
				}catch(Exception e){
					loger.debug(e.toString()) ;
				}
			}
		}
		return isUpdateSucess;
	}

	@Override
	protected List<? extends Diary> queryNeedDeleteDiary() {
		int beforedays = 1 ;
		Date curDate = DateUtil.getBeforeDate(new Date() ,beforedays) ;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				FeedDao feedDao = daoSession.getFeedDao() ;
				QueryBuilder<Feed> queryBuilder = feedDao.queryBuilder() ;
				queryBuilder.where(Properties.Ddat.lt(curDate))   ;
				List<Feed> feeds = queryBuilder.list() ;
				return feeds ;
		   }catch(Exception e){
		    	e.printStackTrace() ;
		    }
		}
		return Collections.emptyList() ;
	}

	@Override
	protected List<? extends Diary> getNeedSynDiary() {
		int beforedays = 3 ;
		Date curDate = DateUtil.getBeforeDate(new Date() ,beforedays) ;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				FeedDao feedDao = daoSession.getFeedDao() ;
				QueryBuilder<Feed > queryBuilder = feedDao.queryBuilder() ;
				queryBuilder.where(Properties.Ddat.ge(curDate.getTime()) ,
						           (Properties.Status.notEq(Diary.OPP_NORMAL)));
				List<Feed> feeds = queryBuilder.list() ;
				return feeds ;
		    }catch(Exception e){
		    	e.printStackTrace() ;
		    }
		}
		return Collections.emptyList() ;
	}

	@Override
	protected List<? extends Diary> sync(List<? extends Diary> diary) {
		// TODO Auto-generated method stub
		return null;
	}
}
