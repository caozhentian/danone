/**
 * 
 */
package com.threeti.danone.android.respositoty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.content.Context;

import cn.jesse.nativelogger.NLogger;

import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.NotificationDao;
import com.threeti.danone.android.db.dao.NotificationDao.Properties;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.NotificationDao;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.common.bean.Notification;
import com.threeti.danone.common.bean.Diary;
import com.threeti.danone.common.bean.DiaryResposityEvent;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.util.DateUtil;
import com.threeti.danone.common.util.NumberIntersectUtil;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author ztcao
 *
 */
public class NotificationRespository extends DiaryRespository {

	public static final String TAG = "NotificationRespository" ;
	
	@Override
	protected boolean localDelete(Diary diary) {
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			NotificationDao feedDao = daoSession.getNotificationDao() ;
			if (feedDao != null) {
				try{
					feedDao.delete((Notification)diary) ;
					isDeleteSucess = true ;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
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
			NotificationDao feedDao = daoSession.getNotificationDao() ;
			if (feedDao != null) {
				try{
					feedDao.update((Notification)diary);
					isUpdateSucess = true ;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
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
			NotificationDao feedDao = daoSession.getNotificationDao() ;
			if (feedDao != null) {
				try{
					long result_id = feedDao.insert((Notification)diary);
					if (result_id > 0) {
						isInsertSucess = true;
				}
				}catch(Exception e){
					NLogger.e(TAG, e) ;
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
//		Call<BaseModel<Notification>> call     = stoolApiService.sync() ;
//		try {
//			Response<BaseModel<Notification>>  response = 	call.execute();
//			if(response.isSuccessful() && response.errorBody() == null){
//				BaseModel<Stool> baseModel = response.body() ;
//				Notification feed      = baseModel.getData() ;
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
//		
//		Date curDate = DateUtil.getBeforeDate(date ,beforeDays) ;
//		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
//		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();
//
//		if (daoSession != null) {
//		    try{
//				NotificationDao notificationDao = daoSession.getNotificationDao() ;
//				List<Notification> cryings = notificationDao.queryBuilder().where(Properties.Ddat.ge(curDate.getTime())).list() ;
//				return cryings ;
//		    }catch(Exception e){
//		    	NLogger.e(TAG, e) ;
//		    }
//		}
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
		
		List<Notification> crying     = convCrying(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			NotificationDao notificationDao = daoSession.getNotificationDao() ;
			if (notificationDao != null) {
				try{
					notificationDao.insertInTx(crying) ;
					isInsertSucess = true;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
				}
			}
		}
		return isInsertSucess;
	}

	
	public List<Notification> convCrying(List<? extends Diary> diaries){
		List<Notification> crying     = new   ArrayList<Notification>(diaries.size()) ;  
		for(Diary diary : diaries){
			crying.add((Notification)diary) ;
		}
		return crying ;
	}

	@Override
	protected boolean localDelete(List<? extends Diary> diaries) {
		if( diaries == null || diaries.size() == 0 ){
			return true ;
		}
		
		boolean isDeleteSucess =  false;
		
		List<Notification> crying     = convCrying(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			NotificationDao notificationDao = daoSession.getNotificationDao() ;
			if (notificationDao != null) {
				try{
					notificationDao.deleteInTx(crying) ;
					isDeleteSucess = true;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
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
		
		List<Notification> crying     = convCrying(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			NotificationDao notificationDao = daoSession.getNotificationDao() ;
			if (notificationDao != null) {
				try{
					notificationDao.updateInTx(crying) ;
					isUpdateSucess = true;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
				}
			}
		}
		return isUpdateSucess;
	}

	@Override
	protected List<? extends Diary> queryNeedDeleteDiary() {
//		int beforedays = 1 ;
//		Date curDate = DateUtil.getBeforeDate(new Date() ,beforedays) ;
//		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
//		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();
//
//		if (daoSession != null) {
//		    try{
//				NotificationDao notificationDao = daoSession.getNotificationDao() ;
//				QueryBuilder<Notification> queryBuilder = notificationDao.queryBuilder() ;
//				queryBuilder.where(Properties.Ddat.lt(curDate))   ;
//				List<Notification> crying = queryBuilder.list() ;
//				return crying ;
//		   }catch(Exception e){
//		    	e.printStackTrace() ;
//		    }
//		}
		return Collections.emptyList() ;
	}

	@Override
	protected List<? extends Diary> getNeedSynDiary() {
//		int beforedays = 3 ;
//		Date curDate = DateUtil.getBeforeDate(new Date() ,beforedays) ;
//		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
//		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();
//
//		if (daoSession != null) {
//		    try{
//				NotificationDao notificationDao = daoSession.getNotificationDao() ;
//				QueryBuilder<Notification > queryBuilder = notificationDao.queryBuilder() ;
//				queryBuilder.where(Properties.Ddat.ge(curDate.getTime()) ,
//						           (Properties.Status.notEq(Diary.OPP_NORMAL)));
//				List<Notification> crying = queryBuilder.list() ;
//				return crying ;
//		    }catch(Exception e){
//		    	e.printStackTrace() ;
//		    }
//		}
		return Collections.emptyList() ;
	}

	@Override
	protected List<? extends Diary> sync(List<? extends Diary> diary) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
