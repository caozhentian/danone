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
import com.threeti.danone.android.db.dao.CryingDao;
import com.threeti.danone.android.db.dao.CryingDao.Properties;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.CryingDao;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.common.bean.Crying;
import com.threeti.danone.common.bean.DiaryResposityEvent;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.model.Diary;
import com.threeti.danone.common.util.DateUtil;
import com.threeti.danone.common.util.NumberIntersectUtil;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author ztcao
 *
 */
public class CryingRespository extends DiaryRespository {

	public static final String TAG = "CryingRespository" ;
	
	@Override
	protected boolean localDelete(Diary diary) {
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			CryingDao feedDao = daoSession.getCryingDao() ;
			if (feedDao != null) {
				try{
					feedDao.delete((Crying)diary) ;
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
			CryingDao feedDao = daoSession.getCryingDao() ;
			if (feedDao != null) {
				try{
					feedDao.update((Crying)diary);
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
			CryingDao feedDao = daoSession.getCryingDao() ;
			if (feedDao != null) {
				try{
					long result_id = feedDao.insert((Crying)diary);
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
//		Call<BaseModel<Crying>> call     = stoolApiService.sync() ;
//		try {
//			Response<BaseModel<Crying>>  response = 	call.execute();
//			if(response.isSuccessful() && response.errorBody() == null){
//				BaseModel<Stool> baseModel = response.body() ;
//				Crying feed      = baseModel.getData() ;
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
				CryingDao cryingDao = daoSession.getCryingDao() ;
				List<Crying> cryings = cryingDao.queryBuilder().where(Properties.Ddat.ge(curDate.getTime())).list() ;
				return cryings ;
		    }catch(Exception e){
		    	NLogger.e(TAG, e) ;
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
		
		List<Crying> crying     = convCrying(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			CryingDao cryingDao = daoSession.getCryingDao() ;
			if (cryingDao != null) {
				try{
					cryingDao.insertInTx(crying) ;
					isInsertSucess = true;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
				}
			}
		}
		return isInsertSucess;
	}

	
	public List<Crying> convCrying(List<? extends Diary> diaries){
		List<Crying> crying     = new   ArrayList<Crying>(diaries.size()) ;  
		for(Diary diary : diaries){
			crying.add((Crying)diary) ;
		}
		return crying ;
	}

	@Override
	protected boolean localDelete(List<? extends Diary> diaries) {
		if( diaries == null || diaries.size() == 0 ){
			return true ;
		}
		
		boolean isDeleteSucess =  false;
		
		List<Crying> crying     = convCrying(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			CryingDao cryingDao = daoSession.getCryingDao() ;
			if (cryingDao != null) {
				try{
					cryingDao.deleteInTx(crying) ;
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
		
		List<Crying> crying     = convCrying(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			CryingDao cryingDao = daoSession.getCryingDao() ;
			if (cryingDao != null) {
				try{
					cryingDao.updateInTx(crying) ;
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
				CryingDao cryingDao = daoSession.getCryingDao() ;
				QueryBuilder<Crying> queryBuilder = cryingDao.queryBuilder() ;
				queryBuilder.where(Properties.Ddat.lt(curDate))   ;
				List<Crying> crying = queryBuilder.list() ;
				return crying ;
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
				CryingDao cryingDao = daoSession.getCryingDao() ;
				QueryBuilder<Crying > queryBuilder = cryingDao.queryBuilder() ;
				queryBuilder.where(Properties.Ddat.ge(curDate.getTime()) ,
						           (Properties.Status.notEq(Diary.OPP_NORMAL)));
				List<Crying> crying = queryBuilder.list() ;
				return crying ;
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
	
	public boolean isOverride(final Crying crying){
		boolean override = false ;
		@SuppressWarnings("unchecked")
		List<Crying> cryinges          = (List<Crying>) query(crying.getDdat() , 0) ;
		@SuppressWarnings("unchecked")
		List<Crying> intersectCryinges   = (List<Crying>) NumberIntersectUtil.isIntersect(crying, cryinges) ;
		if(intersectCryinges.size() > 0){
			override = true ; 
		}
		return override ;
	}
	
	public void override(final Crying crying){

		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();
		final CryingDao cryinglDao =  daoSession.getCryingDao() ; 
		@SuppressWarnings("unchecked")
		List<Crying> cryinges          = (List<Crying>) query(crying.getDdat() , 0) ;
		//List<Crying> cryinges          = cryinglDao.loadAll() ;
		@SuppressWarnings("unchecked")
		List<Crying> intersectCryinges   = (List<Crying>) NumberIntersectUtil.isIntersect(crying, cryinges) ;
		final List<Crying> deleteLogicCryinges = new ArrayList<Crying>() ;
		final List<Crying> deleteCryinges      = new ArrayList<Crying>() ;
		
		for(Crying cry : intersectCryinges){
			if(cry.isOffline()){
				deleteCryinges.add(cry) ;
			}
			else{
				cry.setStatus(Diary.OPP_DELETE) ;
				deleteLogicCryinges.add(cry) ;
			}
		}
		
		if (daoSession != null) {
			cryinglDao.getSession().runInTx(new Runnable() {  
		            @Override  
		            public void run() { 
		            	try{
			            	if(deleteCryinges.size() != 0){
			            		cryinglDao.deleteInTx(deleteCryinges) ;
			            	}
			            	if(deleteLogicCryinges.size() != 0){
			            		cryinglDao.updateInTx(deleteLogicCryinges) ;
			            	}
			            	//override crying 接口 
			            	
			            	create(crying) ;
		            	}catch(Exception e){
		            		e.printStackTrace() ;
		            		postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_FAIL)    ;
		            		return ;
		            	}
		            }  
		    });
	    }
		
	}
	
	
}
