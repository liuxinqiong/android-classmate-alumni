package com.lxq.util;

import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ThreadStudentNetImageForMe extends Handler implements Runnable{
	private ImageView iv=null;
	private String imageName=null;
	private Bitmap bp=null;
	
	private Map<String,Bitmap> images=null;
	
	public ThreadStudentNetImageForMe(ImageView iv,String imageName,Map<String,Bitmap> images){
		this.iv=iv;
		this.imageName=imageName;
		this.images=images;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		bp=BitmapFactory.decodeStream(GetInputStream.getInputStream(UrlForMe.QUERY_STUDENT_IMG+imageName));
		if(bp!=null){
			this.sendEmptyMessage(1);
		}
	}
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		if(msg.what==1){
			iv.setImageBitmap(bp);
			images.put("UrlForMe.QUERY_STUDENT_IMG"+imageName, bp);
		}
	}

}
