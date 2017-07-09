package com.lxq.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
/**
 * �������ط�������߿�����
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
		b.setTitle("��ʾ");
		b.setMessage("û������Ӵ���ø������ȥ");
		b.setPositiveButton("ȷ��", new OnClickListener() {
			
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
		b.setTitle("��ʾ");
		b.setMessage("û������Ӵ���ø������ȥ");
		if(flag){
			b.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		}else{
			b.setPositiveButton("ȷ��", new OnClickListener() {
				
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
		b.setTitle("��ʾ");
		b.setMessage(msg);
		if(flag){
			b.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		}else{
			b.setPositiveButton("ȷ��", new OnClickListener() {
				
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
