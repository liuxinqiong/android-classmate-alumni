package com.lxq.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
/**
 * 三个重载方法，提高可用性
 * @author sky
 *
 */
public class OutDialog {
	private Context context;
	public OutDialog(Context context){
		this.context=context;
	}
	
	public Dialog getDialog(){
		AlertDialog.Builder b=new Builder(context);
		b.setTitle("提示");
		b.setMessage("没有数据哟，该干嘛干嘛去");
		b.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				((Activity)context).finish();
			}
		});
		
		return b.create();
	}
	
	public Dialog getDialog(boolean flag){
		AlertDialog.Builder b=new Builder(context);
		b.setTitle("提示");
		b.setMessage("没有数据哟，该干嘛干嘛去");
		if(flag){
			b.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		}else{
			b.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					((Activity)context).finish();
				}
			});
		}
		return b.create();
	}
	
	public Dialog getDialog(String msg,boolean flag){
		AlertDialog.Builder b=new Builder(context);
		b.setTitle("提示");
		b.setMessage(msg);
		if(flag){
			b.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		}else{
			b.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					((Activity)context).finish();
				}
			});
		}
		
		return b.create();
	}
}
