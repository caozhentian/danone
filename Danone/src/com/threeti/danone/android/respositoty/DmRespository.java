package com.threeti.danone.android.respositoty;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import cn.jesse.nativelogger.NLogger;

import com.threeti.danone.android.application.DanoneApplication;
import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.CryingDao;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.DmDao;
import com.threeti.danone.android.db.dao.CryingDao.Properties;
import com.threeti.danone.common.bean.Crying;
import com.threeti.danone.common.bean.Diary;
import com.threeti.danone.common.bean.Dm;
import com.threeti.danone.common.util.DateUtil;

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
