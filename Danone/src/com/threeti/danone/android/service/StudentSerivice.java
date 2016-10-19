package com.threeti.danone.android.service;

import java.util.List;

import com.threeti.danone.android.db.DaoManager;
import com.threeti.danone.android.db.dao.DaoSession;
import com.threeti.danone.android.db.dao.StudentDao;
import com.threeti.danone.common.bean.Student;

import android.content.Context;

public class StudentSerivice {

	public boolean addStudent(Student student, Context context) {
		boolean isInsertSucess = false;
		DaoSession daoSession = DaoManager.getInstance().init(context).getDaoSession();

		if (daoSession != null) {
			StudentDao studentDao = daoSession.getStudentDao();
			if (studentDao != null) {
				long result_id = studentDao.insert(student);
				if (result_id > 0) {
					isInsertSucess = true;
				}
			}
		}
		return isInsertSucess;
	}

	public List<Student> findAllStudents(Context context) {

		List<Student> students = null;
		DaoSession daoSession = DaoManager.getInstance().init(context).getDaoSession();
		if (daoSession != null) {
			StudentDao studentDao = daoSession.getStudentDao();
			if (studentDao != null) {
				students = studentDao.loadAll();
			}
		}
		return students;

	}
	public void close(){
		DaoManager.getInstance().closeDataBase();
	}
}
