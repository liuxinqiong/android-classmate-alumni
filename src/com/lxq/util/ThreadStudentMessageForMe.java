package com.lxq.util;

import android.os.Handler;

public class ThreadStudentMessageForMe implements Runnable{
	private Handler handler;
	public ThreadStudentMessageForMe(Handler handler){
		this.handler=handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		StudentGlobal.list=PullForXml.getStudents(GetInputStream.getInputStream(UrlForMe.QUERY_STUDENT_ALL));
		//说明此时对象已经的到 是没有问题的
//		System.out.println(StudentGlobal.list.size());
//		System.out.println(StudentGlobal.list.get(0).getAge());
//		System.out.println(StudentGlobal.list.get(0).getName());
//		System.out.println(StudentGlobal.list.get(0).getDream());
//		System.out.println(StudentGlobal.list.get(0).getPhoto());
//		System.out.println(StudentGlobal.list.get(0).getBirthday());
		if(StudentGlobal.list!=null){
			handler.sendEmptyMessage(3);
		}else{
			handler.sendEmptyMessage(4);
		}
	}
}	
