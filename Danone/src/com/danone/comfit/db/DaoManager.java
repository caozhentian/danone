package com.danone.comfit.db;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.danone.comfit.common.config.DatabaseConfig;
import com.danone.comfit.common.config.Debug;
import com.danone.comfit.common.config.FileConfig;
import com.danone.comfit.db.dao.DaoMaster;
import com.danone.comfit.db.dao.DaoSession;

import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.query.QueryBuilder;

public class DaoManager {
	private static final String TAG = DaoManager.class.getSimpleName();
	private static final String DB_NAME = "danone.db";// 数据库名称
	private volatile static DaoManager mDaoManager;// 多线程访问
	private static DaoMaster.EncryptedDevOpenHelper mHelper;
	private static DaoMaster mDaoMaster;
	private static DaoSession mDaoSession;
	private Context context;
	private Database sqLiteDatabase;

	/**
	 * 使用单例模式获得操作数据库的对象
	 * 
	 * @return
	 */
	public static DaoManager getInstance() {
		DaoManager instance = null;
		if (mDaoManager == null) {
			synchronized (DaoManager.class) {
				if (instance == null) {
					instance = new DaoManager();
					mDaoManager = instance;
				}
			}
		}
		return mDaoManager;
	}

	/**
	 * 初始化Context对象
	 * 
	 * @param context
	 */
	public DaoManager init(Context context) {
		this.context = context;
		return mDaoManager;
	}

	/**
	 * 判断数据库是否存在，如果不存在则创建
	 * 
	 * @return
	 */
	public DaoMaster getDaoMaster() {
		if (null == mDaoMaster) {
			if(Debug.DEV_MODE){
				mHelper = new DaoMaster.EncryptedDevOpenHelper(new ContextWrapper(context){
				
		                @Override  
		                public File getDatabasePath(String name) {        
	                        File dirFile = new File(FileConfig.APP_DEV_BASE_DIR);  
	                        if (!dirFile.exists())  
	                            dirFile.mkdirs();  
	  
	                        // 数据库文件是否创建成功  
	                        boolean isFileCreateSuccess = false;  
	                        // 判断文件是否存在，不存在则创建该文件  
	                        File dbFile = new File(FileConfig.APP_DEV_BASE_DIR + FileConfig.DB_NAME);  
	                        if (!dbFile.exists()) {  
	                            try {  
	                                isFileCreateSuccess = dbFile.createNewFile();// 创建文件  
	                            } catch (IOException e) {  
	                                e.printStackTrace();  
	                            }  
	                        } else  
	                            isFileCreateSuccess = true;  
	                        // 返回数据库文件对象  
	                        if (isFileCreateSuccess)  
	                            return dbFile;  
	                        else  
	                            return super.getDatabasePath(name);  
			                      
			                }  
				}, DB_NAME);
			}
			else{
				mHelper = new DaoMaster.EncryptedDevOpenHelper(context , DB_NAME) ;
			}
			sqLiteDatabase = mHelper.getWritableDatabase(DatabaseConfig.ENCRYP_PASSWORD) ;
			mDaoMaster = new DaoMaster(sqLiteDatabase);
		}
		return mDaoMaster;
	}

	/**
	 * 完成对数据库的增删查找
	 * 
	 * @return
	 */
	public DaoSession getDaoSession() {
		if (null == mDaoSession) {
			if (null == mDaoMaster) {
				mDaoMaster = getDaoMaster();
			}
			mDaoSession = mDaoMaster.newSession();

		}
		return mDaoSession;
	}

	/**
	 * 设置debug模式开启或关闭，默认关闭
	 * 
	 * @param flag
	 */
	public void setDebug(boolean flag) {
		QueryBuilder.LOG_SQL = flag;
		QueryBuilder.LOG_VALUES = flag;
	}

	/**
	 * 关闭数据库
	 */
	public void closeDataBase() {
		
		mDaoMaster = null;
		closeHelper();
		closeDaoSession();
	}

	public void closeDaoSession() {
		if (null != mDaoSession) {

			mDaoSession.clear();
			mDaoSession = null;
		}
	}

	public void closeHelper() {
		if (mHelper != null) {
			mHelper.close();
			mHelper = null;
		}
	}
}
