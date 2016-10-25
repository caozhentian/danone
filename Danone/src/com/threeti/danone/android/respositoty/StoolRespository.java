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
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.android.db.dao.StoolDao.Properties;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.Diary;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.util.DateUtil;
import com.threeti.danone.manager.net.RetrofitFactory;
import com.threeti.danone.manager.net.StoolApiService;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author ztcao
 *
 */
public class StoolRespository extends DiaryRespository {

	public static final String TAG = "StoolRespository" ;
	
	@Override
	protected boolean localDelete(Diary diary) {
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			StoolDao stoolDao = daoSession.getStoolDao() ;
			if (stoolDao != null) {
				try{
					stoolDao.delete((Stool)diary) ;
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
			StoolDao stoolDao = daoSession.getStoolDao() ;
			if (stoolDao != null) {
				try{
					stoolDao.update((Stool)diary);
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
			StoolDao stoolDao = daoSession.getStoolDao() ;
			if (stoolDao != null) {
				try{
					long result_id = stoolDao.insert((Stool)diary);
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
		Retrofit retrofit = RetrofitFactory.getBaseRetrofit();
		StoolApiService stoolApiService = retrofit.create(StoolApiService.class);
		try{
			Call<BaseModel<Stool>> call     = stoolApiService.sync((Stool)diary) ;
			try {
				Response<BaseModel<Stool>>  response = 	call.execute();
				if(response.isSuccessful() && response.errorBody() == null){
					BaseModel<Stool> baseModel = response.body() ;
					Stool stool      = baseModel.getData() ;
					//update diary serverId
					diary.setServerId(stool.getServerId()) ;
					isSyncSucess = true ;
				}
				
			    } catch (IOException e) {
				// TODO Auto-generated catch block
			    NLogger.e(TAG ,  e);
				e.printStackTrace();
			   }
		}
		catch(Exception e){
			NLogger.e(TAG ,  e);
			e.printStackTrace() ;
		}
		return isSyncSucess;
	}

	@Override
	protected List<? extends Diary> loacalQuery(Date date, int beforeDays) {
		Date curDate = DateUtil.getBeforeDate(date ,beforeDays) ;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				StoolDao stoolDao = daoSession.getStoolDao() ;
				List<Stool> stools = stoolDao.queryBuilder().where(Properties.Ddat.ge(curDate.getTime())).list() ;
				return stools ;
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

	protected  List<? extends Diary> sync(List<? extends Diary> diary) {

		Date date = new Date() ;
		int beforeDays = 3 ; //default 3 
		List<? extends Diary> diaries = loacalQuery(date, beforeDays) ;
		Retrofit retrofit = RetrofitFactory.getBaseRetrofit();
		StoolApiService stoolApiService = retrofit.create(StoolApiService.class);
		Call<BaseModel<List<Stool>>> call     = stoolApiService.sync(convStools(diaries)) ;
		try {
			Response<BaseModel<List<Stool>>>  response = 	call.execute();
			if(response.isSuccessful() && response.errorBody() == null){
				BaseModel<List<Stool>> baseModel = response.body() ;
				List<Stool> stools     = baseModel.getData() ;
				return stools ;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			NLogger.e(TAG ,  e);
			e.printStackTrace();
		}
		return Collections.emptyList() ;
	}

	@Override
	protected boolean loacalInsert(List<? extends Diary> diaries) {
		super.loacalInsert(diaries) ;
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

	
	public List<Stool> convStools(List<? extends Diary> diaries){
		if(diaries == null || diaries.size() == 0){
			return Collections.emptyList();
		}
		List<Stool> stools     = new   ArrayList<Stool>(diaries.size()) ;  
		for(Diary diary : diaries){
			stools.add((Stool)diary) ;
		}
		return stools ;
	}

	@Override
	protected boolean localDelete(List<? extends Diary> diaries) {
		if( diaries == null || diaries.size() == 0 ){
			return true ;
		}
		
		boolean isDeleteSucess =  false;
		
		List<Stool> stools     = convStools(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			StoolDao stoolDao = daoSession.getStoolDao() ;
			if (stoolDao != null) {
				try{
					stoolDao.deleteInTx(stools) ;
					isDeleteSucess = true;
				}catch(Exception e){
					NLogger.e(TAG ,  e);
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
		
		List<Stool> stools     = convStools(diaries) ;  
	
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			StoolDao stoolDao = daoSession.getStoolDao() ;
			if (stoolDao != null) {
				try{
					stoolDao.updateInTx(stools) ;
					isUpdateSucess = true;
				}catch(Exception e){
					NLogger.e(TAG ,  e);
				}
			}
		}
		return isUpdateSucess;
	}

	@Override
	public List<? extends Diary> getNeedSynDiary() {
		int beforedays = 3 ;
		Date curDate = DateUtil.getBeforeDate(new Date() ,beforedays) ;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				StoolDao stoolDao = daoSession.getStoolDao() ;
				QueryBuilder<Stool > queryBuilder = stoolDao.queryBuilder() ;
				queryBuilder.where(Properties.Ddat.ge(curDate.getTime()) ,
						           (Properties.Status.notEq(Diary.OPP_NORMAL)));
				List<Stool> stools = queryBuilder.list() ;
				return stools ;
		    }catch(Exception e){
		    	e.printStackTrace() ;
		    }
		}
		return Collections.emptyList() ;
	}

	@Override
	public List<? extends Diary> queryNeedDeleteDiary() {
		int beforedays = 1 ;
		Date curDate = DateUtil.getBeforeDate(new Date() ,beforedays) ;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				StoolDao stoolDao = daoSession.getStoolDao() ;
				QueryBuilder<Stool> queryBuilder = stoolDao.queryBuilder() ;
				queryBuilder.where(Properties.Ddat.lt(curDate))   ;
				List<Stool> stools = queryBuilder.list() ;
				return stools ;
		   }catch(Exception e){
		    	e.printStackTrace() ;
		    }
		}
		return Collections.emptyList() ;
	}

}
