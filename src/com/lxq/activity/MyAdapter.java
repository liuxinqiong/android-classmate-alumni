package com.lxq.activity;

import java.util.List;

import com.lxq.entity.Student;
import com.lxq.util.StudentImageGloabal;
import com.lxq.util.ThreadStudentNetImageForMe;
import com.lxq.util.UrlForMe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private List<Student> list;
	private LayoutInflater inflater;
	
	public MyAdapter(List<Student> list,Context context){
		this.list=list;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.main_page_show_item, null);
		}
		Student s=list.get(pos);
		TextView main_list_txtName =(TextView) view.findViewById(R.id.main_list_txtName);
		TextView main_list_txtPhone=(TextView) view.findViewById(R.id.main_list_txtPhone);
		ImageView main_list_imageViewPhoto=(ImageView) view.findViewById(R.id.main_list_imageViewPhoto);
		main_list_txtName.setText(s.getName());
		main_list_txtPhone.setText(s.getTel());
		if(StudentImageGloabal.image.containsKey(UrlForMe.QUERY_STUDENT_IMG+list.get(pos).getPhoto())){
			main_list_imageViewPhoto.setImageBitmap(StudentImageGloabal.image.get(UrlForMe.QUERY_STUDENT_IMG+list.get(pos).getPhoto()));
		}else{
			ThreadStudentNetImageForMe tsnfm=new ThreadStudentNetImageForMe(main_list_imageViewPhoto, list.get(pos).getPhoto(), StudentImageGloabal.image);
			new Thread(tsnfm).start();
		}
		return view;
	}
	
}
