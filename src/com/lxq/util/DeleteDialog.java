package com.lxq.util;

import java.util.List;

import com.lxq.entity.Student;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

public class DeleteDialog {
	private Context context;
	private int position;
	private Handler handler;
	private List<Student> list;
	private SQLiteDatabase db;
	
	public DeleteDialog(Context context,int position,Handler handler,List<Student> list,SQLiteDatabase db){
		this.context=context;
		this.position=position;
		this.handler=handler;
		this.list=list;
		this.db=db;
	}
	
	public Dialog getDialog(){
		AlertDialog.Builder b=new Builder(context);
		b.setTitle("提示");
		b.setMessage("您确定要删除【"+list.get(position).getName()+"】的相关数据吗？");
		b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(handler!=null){
					String[] whereArgs={list.get(position).getName(),list.get(position).getTel()};
					int param=db.delete(DBUtil.MY_DB_TABLE_1_NAME, "name= ? and tel= ?", whereArgs);
					if(param>0){
						list.remove(position);
						handler.sendEmptyMessage(1);
					}else{
						handler.sendEmptyMessage(2);
					}
				}
			}
		});
		
		b.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		return b.create();
	}
	
}
