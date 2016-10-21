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
import com.threeti.danone.android.db.dao.SpittingUpDao;
import com.threeti.danone.android.db.dao.StoolDao;
import com.threeti.danone.common.bean.BaseModel;
import com.threeti.danone.common.bean.SpittingUp;
import com.threeti.danone.common.bean.Stool;
import com.threeti.danone.common.model.Diary;

/**
 * @author ztcao
 *
 */
public class SpittingUpRespository extends DiaryRespository {

	private static Logger loger = LoggerFactory
			.getLogger(SpittingUpRespository.class);
	
	@Override
	protected boolean localDelete(Diary diary) {
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			SpittingUpDao spitting_upDao = daoSession.getSpittingUpDao() ;
			if (spitting_upDao != null) {
				try{
					spitting_upDao.delete((SpittingUp)diary) ;
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
			SpittingUpDao spitting_upDao = daoSession.getSpittingUpDao() ;
			if (spitting_upDao != null) {
				try{
					spitting_upDao.update((SpittingUp)diary);
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
			SpittingUpDao spitting_upDao = daoSession.getSpittingUpDao() ;
			if (spitting_upDao != null) {
				try{
					long result_id = spitting_upDao.insert((SpittingUp)diary);
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
//		Call<BaseModel<Spitting_up>> call     = stoolApiService.sync() ;
//		try {
//			Response<BaseModel<Spitting_up>>  response = 	call.execute();
//			if(response.isSuccessful() && response.errorBody() == null){
//				BaseModel<Stool> baseModel = response.body() ;
//				Spitting_up feed      = baseModel.getData() ;
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
	protected List<Diary> loacalQuery(Date date, int beforeDays) {
		
		return Collections.emptyList() ;
	}

	@Override
	protected List<Diary> serverQuery(Date date, int beforeDays) {
		
		return Collections.emptyList() ;
	}

//	@Override
//	public boolean sync() {
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
////	}

	@Override
	protected boolean loacalInsert(List<? extends Diary> diaries) {
		
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
					loger.debug(e.toString()) ;
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
