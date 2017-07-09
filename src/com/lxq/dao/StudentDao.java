package com.lxq.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lxq.entity.Student;
import com.lxq.util.DBUtil;

public class StudentDao {
	public static final String MY_DB_NAME = "my_db";
	public static final String MY_DB_TABLE_1_NAME = "student";
	public static final String QUERY_STUDENT_ALL = "select * from"
			+ MY_DB_TABLE_1_NAME;
	private DBUtil dbUtil;
	Context context;
	
	public StudentDao(Context context){
		dbUtil=DBUtil.getInstance(context, MY_DB_NAME);
		this.context=context;
	}
	
	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	
	public long delAllStudents(){
		long param=0;
		SQLiteDatabase db=dbUtil.getWritableDatabase();
		param=db.delete(DBUtil.MY_DB_TABLE_1_NAME, null, null);
		return param;
	}
	
	public List<Student> getAllStudent(){
		List<Student> list=new ArrayList<Student>();
		Student s=null;
		SQLiteDatabase db=dbUtil.getReadableDatabase();
		Cursor c=db.query(MY_DB_TABLE_1_NAME, null, null, null, null, null, null);
		while(c.moveToNext()){
			s=new Student();
			//取id可以 怎么年龄就出错呢！！
			s.setId(c.getInt(0));
			s.setName(c.getString(1));
			s.setAge(c.getInt(2));
			s.setSex(c.getString(3));
			s.setTel(c.getString(4));
			s.setAddress(c.getString(5));
			s.setQq(c.getString(6));
			s.setMajor(c.getString(7));
			s.setBirthday(c.getString(8));
			s.setEducational(c.getString(9));
			s.setEmergency(c.getString(10));
			s.setHobby(c.getString(11));
			s.setWantWork(c.getString(12));
			s.setDream(c.getString(13));
			s.setPhoto(c.getString(14));
			list.add(s);
		}
		db.close();
		return list;
	}
	
//	private void wrap(Student s,Cursor c){
//		s.setId(c.getInt(0));
//		s.setName(c.getString(1));
//		s.setAge(c.getInt(2));
//		s.setSex(c.getString(3));
//		s.setTel(c.getString(4));
//		s.setAddress(c.getString(5));
//		s.setQq(c.getString(6));
//		s.setMajor(c.getString(7));
//		s.setBirthday(c.getString(8));
//		s.setEducational(c.getString(9));
//		s.setEmergency(c.getString(10));
//		s.setHobby(c.getString(11));
//		s.setWantWork(c.getString(12));
//		s.setDream(c.getString(13));
//		s.setPhoto(c.getString(14));
//	}
//	
	public long addStudents(List<Student> list){
		SQLiteDatabase db=dbUtil.getWritableDatabase();
		if(list==null){
			System.out.println("true");
			return 1;
		}
		long param=0;
		for (Student s : list) {
			ContentValues cv=new ContentValues();
			cv.put("name", s.getName());
			cv.put("age", s.getAge());
			cv.put("sex", s.getSex());
			cv.put("tel", s.getTel());
			cv.put("address", s.getAddress());
			cv.put("qq", s.getQq());
			cv.put("major", s.getMajor());
			cv.put("birthday", s.getBirthday());
			cv.put("educational", s.getEducational());
			cv.put("emergency", s.getEmergency());
			cv.put("hobby", s.getHobby());
			cv.put("wantWork", s.getWantWork());
			cv.put("dream", s.getDream());
			cv.put("photo", s.getPhoto());
			param=db.insert(MY_DB_TABLE_1_NAME, null, cv);
		}
		db.close();
		return param;
	}
}
