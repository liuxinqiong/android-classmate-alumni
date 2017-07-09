package com.lxq.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 设计成单例设计模式
 * 
 * @author sky
 */
public class DBUtil extends SQLiteOpenHelper {
	private static DBUtil dbUtil = null;
	public static final String MY_DB_NAME = "my_db";
	public static final String MY_DB_TABLE_1_NAME = "student";
	public static final int MY_DB_VERSION = 1;
	

	private DBUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public static DBUtil getInstance(Context context, String name) {
		if (dbUtil == null) {
			return dbUtil = new DBUtil(context, name, null, MY_DB_VERSION);
		} else {
			return dbUtil;
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table "
				+ MY_DB_TABLE_1_NAME
				+ " (_id integer primary key,name varchar,age integer,sex varchar,tel varchar,address varchar,qq varchar,major varchar,birthday varchar,educational varchar,emergency varchar,hobby varchar,wantWork varchar,dream varchar,photo varchar)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//删除原来的表
		 db.execSQL("drop table"+MY_DB_TABLE_1_NAME);
		 onCreate(db);
	}
}
