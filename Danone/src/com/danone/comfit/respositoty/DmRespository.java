package com.danone.comfit.respositoty;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import cn.jesse.nativelogger.NLogger;

import com.danone.comfit.application.DanoneApplication;
import com.danone.comfit.common.bean.Crying;
import com.danone.comfit.common.bean.Diary;
import com.danone.comfit.common.bean.Dm;
import com.danone.comfit.common.util.DateUtil;
import com.danone.comfit.db.DaoManager;
import com.danone.comfit.db.dao.CryingDao;
import com.danone.comfit.db.dao.DaoSession;
import com.danone.comfit.db.dao.DmDao;
import com.danone.comfit.db.dao.CryingDao.Properties;

public class DmRespository  {
	
	public static final String TAG = "DmRespository" ;
	protected boolean loacalInsert(Dm dm) {
		boolean isInsertSucess =  false;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			DmDao dmDao = daoSession.getDmDao();
			if (dmDao != null) {
				try{
					long result_id = dmDao.insert(dm);
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
	
	protected List<Dm> loacalQuery() {
		List<Dm> dm=null;
		Context context        =  DanoneApplication.getInstance().getApplicationContext() ;
		DaoSession daoSession  =  DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
		    try{
				DmDao dmDao = daoSession.getDmDao() ;
				dm = dmDao.loadAll();
				return dm ;
		    }catch(Exception e){
		    	NLogger.e(TAG, e) ;
		    }
		}
		return dm ;
	}

}
