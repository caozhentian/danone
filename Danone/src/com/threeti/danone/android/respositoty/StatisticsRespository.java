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
import com.threeti.danone.common.bean.TimeSpent;

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
	 * @param timeSpents
	 * @return
	 */
	public List<TimeSpent> sync(List<TimeSpent> timeSpents) {

		return Collections.emptyList();
	}

}
