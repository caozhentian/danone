/**
 * 
 */
package com.threeti.danone.android.respositoty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.content.Context;

import cn.jesse.nativelogger.NLogger;

import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.MvnDao;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.Diary;
import com.threeti.danone.common.bean.Mvn;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.manager.net.RetrofitFactory;
import com.threeti.danone.manager.net.StoolApiService;

/**
 * @author ztcao
 *
 */
public class MvnRespository extends DiaryRespository {

	public static final String TAG = "MvnRespository" ;
	
	@Override
	protected boolean localDelete(Diary diary) {
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			MvnDao mvnDao = daoSession.getMvnDao() ;
			if (mvnDao != null) {
				try{
					mvnDao.delete((Mvn)diary) ;
					isDeleteSucess = true ;
				}catch(Exception e){
					NLogger.e(TAG ,  e);
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
			MvnDao mvnDao = daoSession.getMvnDao() ;
			if (mvnDao != null) {
				try{
					mvnDao.update((Mvn)diary);
					isUpdateSucess = true ;
				}catch(Exception e){
					NLogger.e(TAG ,  e);
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
			MvnDao mvnDao = daoSession.getMvnDao() ;
			if (mvnDao != null) {
				try{
					long result_id = mvnDao.insert((Mvn)diary);
					if (result_id > 0) {
						isInsertSucess = true;
				}
				}catch(Exception e){
					NLogger.e(TAG ,  e);
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
//		Call<BaseModel<Mvn>> call     = stoolApiService.sync() ;
//		try {
//			Response<BaseModel<Mvn>>  response = 	call.execute();
//			if(response.isSuccessful() && response.errorBody() == null){
//				BaseModel<Stool> baseModel = response.body() ;
//				Mvn feed      = baseModel.getData() ;
//				//update diary serverId
//				diary.setServerId(feed.getServerId()) ;
//				isSyncSucess = true ;
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			NLogger.e(TAG ,  e);
//			e.printStackTrace();
//		}
		return isSyncSucess;
	}

	@Override
	protected List<Diary> loacalQuery(Date date, int beforeDays) {
		
		return Collections.emptyList() ;
	}

	@Override
	protected List<Diary> serverQuery(Date date, int beforeDays) {
		
		return Collections.emptyList() ;
	}

//	@Override
//	public void sync() {
//		// TODO Auto-generated method stub
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
//	}

	@Override
	protected boolean loacalInsert(List<? extends Diary>  diaries) {
		
		if( diaries == null || diaries.size() == 0 ){
			return true ;
		}
		
		boolean isInsertSucess =  false;
		
		List<Stool> stools     = convStools(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			StoolDao stoolDao = daoSession.getStoolDao() ;
			if (stoolDao != null) {
				try{
					stoolDao.insertInTx(stools) ;
					isInsertSucess = true;
				}catch(Exception e){
					NLogger.e(TAG ,  e);
				}
			}
		}
		return isInsertSucess;
	}

	
	public List<Stool> convStools(List<? extends Diary>  diaries){
		List<Stool> stools     = new   ArrayList<Stool>(diaries.size()) ;  
		for(Diary diary : diaries){
			stools.add((Stool)diary) ;
		}
		return stools ;
	}

	@Override
	protected boolean localDelete(List<? extends Diary> diaries) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean localUpdate(List<? extends Diary> diaries) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<? extends Diary> queryNeedDeleteDiary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<? extends Diary> getNeedSynDiary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<? extends Diary> sync(List<? extends Diary> diary) {
		// TODO Auto-generated method stub
		return null;
	}
}
