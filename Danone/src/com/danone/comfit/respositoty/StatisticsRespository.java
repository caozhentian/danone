/**
 * 
 */
package com.danone.comfit.respositoty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.content.Context;
import cn.jesse.nativelogger.NLogger;

import com.danone.comfit.application.DanoneApplication;
import com.danone.comfit.common.bean.BaseModel;
import com.danone.comfit.common.bean.TimeSpent;
import com.danone.comfit.common.config.Debug;
import com.danone.comfit.db.DaoManager;
import com.danone.comfit.db.dao.DaoSession;
import com.danone.comfit.db.dao.TimeSpentDao;
import com.danone.comfit.db.dao.TimeSpentDao.Properties;
import com.danone.comfit.net.RetrofitFactory;
import com.danone.comfit.net.StatisticsApiService;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author ztcao 统计时间的存储逻辑
 *
 */
public class StatisticsRespository {

	public static final String TAG = "StatisticsRespository" ;

	/**删除一条记录
	 * @param timeSpent
	 * @return
	 */
	public boolean localDelete(TimeSpent timeSpent) {
		
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			TimeSpentDao timeSpentDao = daoSession.getTimeSpentDao() ;
			if (timeSpentDao != null) {
				try{
					timeSpentDao.delete(timeSpent) ;
					isDeleteSucess = true ;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
				}
			}
		}
		return isDeleteSucess;
	}

	/**以事务方式删除一组记录
	 * @param timeSpents
	 * @return
	 */
	public boolean localDelete(List<TimeSpent> timeSpents) {
		boolean isDeleteSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			TimeSpentDao timeSpentDao = daoSession.getTimeSpentDao() ;
			if (timeSpentDao != null) {
				try{
					timeSpentDao.deleteInTx(timeSpents);
					isDeleteSucess = true ;
				}catch(Exception e){
					NLogger.e(TAG, e) ;
				}
			}
		}
		return isDeleteSucess;
	}

	/**更新一条记录
	 * @param timeSpent
	 * @return
	 */
	public boolean localUpdate(TimeSpent timeSpent) {
		boolean isUpdateSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			TimeSpentDao timeSpentDao = daoSession.getTimeSpentDao() ;
			if (timeSpentDao != null) {
				try{
					timeSpentDao.update(timeSpent);
					isUpdateSucess = true ;
				}catch(Exception e){
					NLogger.e(TAG ,  e);
				}
			}
		}
		return isUpdateSucess;
	}

	/**插入一条记录
	 * @param timeSpent
	 * @return
	 */
	public boolean loacalInsert(TimeSpent timeSpent) {
		boolean isInsertSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			TimeSpentDao timeSpentDao = daoSession.getTimeSpentDao();
			String appId = UUID.randomUUID().toString() ;
			timeSpent.setAppId(appId) ;
			//diaryRegId 还没有实现 
			//timeSpent.setDiaryRegId(diaryRegId)
			try{
				timeSpentDao.insert(timeSpent) ;
				isInsertSucess = true ;
			}catch(Exception e){
				NLogger.e(TAG ,  e);
			}
		}
		return isInsertSucess;
	}

	/**根据给定的timeSpent的ddat和type,本地数据库是否存在记录
	 * @param timeSpent
	 * @return
	 */
	public TimeSpent queryTimeSpent(TimeSpent timeSpent){

		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				TimeSpentDao timeSpentDao = daoSession.getTimeSpentDao() ;
				QueryBuilder<TimeSpent> queryBuilder = timeSpentDao.queryBuilder() ;
				queryBuilder.where(Properties.Ddat.ge(timeSpent.getDdat()) ,
						           (Properties.Type.eq(timeSpent.getType())));
				List<TimeSpent> timeSpents = queryBuilder.list();
				if(timeSpents != null && timeSpents.size() > 0){
					return timeSpents.get(0) ;
				}
		    }catch(Exception e){
		    	NLogger.e(TAG ,  e);
		    }
		}
		return null ;
	}


	/** 查询所有的记录
	 * @return
	 */
	public List<TimeSpent> getAllTimeSpent(){
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				TimeSpentDao timeSpentDao = daoSession.getTimeSpentDao() ;
				return timeSpentDao.loadAll() ;
		    }catch(Exception e){
		    	NLogger.e(TAG ,e) ;
		    }
		}
		return Collections.emptyList() ;
	}
	
	/**同步一组timeSpents 到云端
	 * @return
	 */
	public void sync() {
		List<TimeSpent> timeSpents = getAllTimeSpent() ;
		if(timeSpents.size() == 0){
			return ;
		}
		Retrofit retrofit = RetrofitFactory.getBaseRetrofit();
		StatisticsApiService statisticsApiService = retrofit.create(StatisticsApiService.class);
		Call<BaseModel<List<TimeSpent>>> call     = statisticsApiService.sync(timeSpents) ;
		try {
			Response<BaseModel<List<TimeSpent>>>  response = 	call.execute();
			if(response.isSuccessful() && response.errorBody() == null){
				BaseModel<List<TimeSpent>> baseModel = response.body() ;
				List<TimeSpent> timeSpentsNet     = baseModel.getData() ;
				if(timeSpentsNet != null && timeSpentsNet.size() > 0){
					List<TimeSpent> successTimeSpents = new ArrayList<TimeSpent>(timeSpentsNet.size()) ;
					for(TimeSpent timeSpent :successTimeSpents){
						if(timeSpent.isSyncSuccess()){
							successTimeSpents.add(timeSpent) ;
						}
					}
					//批量删除成功的记录
					localDelete(successTimeSpents) ;
				}
			}	
		} catch (IOException e) {
			NLogger.e(TAG ,  e);
			if(Debug.DEV_MODE){
				e.printStackTrace();
			}
		}
		
	}

}
