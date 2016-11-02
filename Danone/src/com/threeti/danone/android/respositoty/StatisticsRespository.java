/**
 * 
 */
package com.threeti.danone.android.respositoty;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import cn.jesse.nativelogger.NLogger;

import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.TimeSpentDao;
import com.threeti.danone.android.db.dao.TimeSpentDao.Properties;
import com.threeti.danone.common.bean.Diary;
import com.threeti.danone.common.bean.TimeSpent;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author ztcao 统计时间的存储逻辑
 *
 */
public class StatisticsRespository {

	public static final String TAG = "StatisticsRespository" ;

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

	public boolean loacalInsert(TimeSpent timeSpent) {
		boolean isInsertSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			TimeSpentDao timeSpentDao = daoSession.getTimeSpentDao();
			String appId = UUID.randomUUID().toString() ;
			timeSpent.setAppId(appId) ;
			try{
				timeSpentDao.insert(timeSpent) ;
				isInsertSucess = true ;
			}catch(Exception e){
				NLogger.e(TAG ,  e);
			}
		}
		return isInsertSucess;
	}

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


	public boolean sync(Diary diary) {
		
		return false;
	}

	
	public List<? extends Diary> sync(List<? extends Diary> diary) {

		return Collections.emptyList();
	}

}
