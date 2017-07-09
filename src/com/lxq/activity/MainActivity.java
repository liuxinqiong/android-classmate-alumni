package com.lxq.activity;

import java.util.ArrayList;
import java.util.List;

import com.lxq.dao.StudentDao;
import com.lxq.entity.Student;
import com.lxq.util.DeleteDialog;
import com.lxq.util.OutDialog;
import com.lxq.util.StudentGlobal;
import com.lxq.util.ThreadStudentMessageForMe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	public static final int MAIN_ACTIVITY_CODE = 1000;
	private Button main_btnShowAll;
	private Button main_btnShowBy;
	private TextView main_txtTitle;
	private ListView main_listStudent;
	private StudentDao dao = null;
	private Handler handler;
	MyAdapter adapter;
	RelativeLayout main_body_center;
	ListView listSearchView;
	EditText txtSearchInfo;
	List<Student> listTemp;
	View allView;
	View searchView;//点击查询按钮后的view

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		main_btnShowAll = (Button) findViewById(R.id.main_btnShowAll);
		main_btnShowBy = (Button) findViewById(R.id.main_btnShowBy);
		main_txtTitle = (TextView) findViewById(R.id.main_txtTitle);
		main_body_center=(RelativeLayout)findViewById(R.id.main_body_center);
		main_btnShowAll.setOnClickListener(this);
		main_btnShowBy.setOnClickListener(this);
		LayoutInflater inflater = LayoutInflater.from(this);
		allView=inflater.inflate(R.layout.activity_all, null);
		main_listStudent = (ListView) allView.findViewById(R.id.main_listStudent);
		
		initHandler();
		dao = new StudentDao(MainActivity.this);
		List<Student> list = dao.getAllStudent();
		if (!list.isEmpty()) {
			StudentGlobal.list = list;
		} else {
			// 网络访问线程
			ThreadStudentMessageForMe tsmfm = new ThreadStudentMessageForMe(
					handler);
			new Thread(tsmfm).start();
		}
		adapter = new MyAdapter(StudentGlobal.list, this);
		main_listStudent.setAdapter(adapter);
		// 单击操作
		main_listStudent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						ShowMessageActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("student", StudentGlobal.list.get(pos));
				bundle.putInt("pos", pos);
				intent.putExtras(bundle);
				startActivityForResult(intent, MAIN_ACTIVITY_CODE);
			}
		});

		// 长按操作
		main_listStudent
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						Dialog d = new DeleteDialog(MainActivity.this,
								position, handler, StudentGlobal.list, dao.getDbUtil().getWritableDatabase())
								.getDialog();
						d.show();
						return true;
					}
				});
		main_body_center.addView(allView);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == main_btnShowAll) {
			main_txtTitle.setText(R.string.main_title);
			main_body_center.removeAllViews();
			main_body_center.addView(allView);
		} else if (v == main_btnShowBy) {
			main_txtTitle.setText(R.string.user_search);
			main_body_center.removeAllViews();
			LayoutInflater inflater = LayoutInflater.from(this);
			searchView = inflater.inflate(R.layout.activity_search, null);
			listSearchView = (ListView) searchView.findViewById(R.id.listSearchView);
			txtSearchInfo = (EditText) searchView.findViewById(R.id.txtSearchInfo);
			Button btnSearch = (Button) searchView.findViewById(R.id.btnSearch);
			btnSearch.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (StudentGlobal.list != null) {
						listTemp = new ArrayList<Student>();
						String param = txtSearchInfo.getText().toString()
								.trim();

						for (Student s : StudentGlobal.list) {
							if (s.getName() != null
									&& s.getName().contains(param)) {
								if (!listTemp.contains(s)) {
									listTemp.add(s);
								}
							}

							if (s.getAddress() != null
									&& s.getAddress().contains(param)) {
								if (!listTemp.contains(s)) {
									listTemp.add(s);
								}
							}

							if (s.getBirthday() != null
									&& s.getBirthday().contains(param)) {
								if (!listTemp.contains(s)) {
									listTemp.add(s);
								}
							}

							if ((s.getAge() + "").contains(param)) {
								if (!listTemp.contains(s)) {
									listTemp.add(s);
								}
							}
							
							if(s.getSex()!=null&&s.getSex().contains(param)){
								if (!listTemp.contains(s)) {
									listTemp.add(s);
								}
							}

							if (s.getTel() != null
									&& s.getTel().contains(param)) {
								if (!listTemp.contains(s)) {
									listTemp.add(s);
								}
							}
						}
						if (listTemp.isEmpty()) {
							new OutDialog(MainActivity.this).getDialog(true)
									.show();
						}

						MyAdapter adapterSearch = new MyAdapter(listTemp,
								MainActivity.this);
						listSearchView.setAdapter(adapterSearch);
						listSearchView
								.setOnItemClickListener(new OnItemClickListener() {

									@Override
									public void onItemClick(
											AdapterView<?> parent, View view,
											int pos, long id) {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												MainActivity.this,
												ShowMessageActivity.class);
										Bundle bundle = new Bundle();
										bundle.putSerializable("student",
												listTemp.get(pos));
										bundle.putInt("pos", pos);
										intent.putExtras(bundle);
										startActivityForResult(intent,
												MAIN_ACTIVITY_CODE);
										startActivity(intent);
									}
								});
						// 长按操作
						listSearchView
								.setOnItemLongClickListener(new OnItemLongClickListener() {

									@Override
									public boolean onItemLongClick(AdapterView<?> parent,
											View view, int position, long id) {
										Dialog d = new DeleteDialog(MainActivity.this,
												position, handler, listTemp, dao.getDbUtil().getWritableDatabase())
												.getDialog();
										d.show();
										return true;
									}
								});
					
					}
				}	
			});
			main_body_center.addView(searchView);
		}
	}

	@SuppressLint("HandlerLeak")
	private void initHandler() {
		// TODO Auto-generated method stub
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				case 2:
					new OutDialog(MainActivity.this).getDialog("删除失败", true)
							.show();
					break;
				case 3:
					// 数据写入手机数据库
					long param = dao.addStudents(StudentGlobal.list);
					if (param == StudentGlobal.list.size()) {

						adapter = new MyAdapter(StudentGlobal.list,
								MainActivity.this);
						main_listStudent.setAdapter(adapter);
						// 单击操作
						main_listStudent
								.setOnItemClickListener(new OnItemClickListener() {

									@Override
									public void onItemClick(
											AdapterView<?> parent, View view,
											int pos, long id) {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												MainActivity.this,
												ShowMessageActivity.class);
										Bundle bundle = new Bundle();
										bundle.putSerializable("student",
												StudentGlobal.list.get(pos));
										bundle.putInt("pos", pos);
										intent.putExtras(bundle);
										startActivityForResult(intent,
												MAIN_ACTIVITY_CODE);
									}
								});

						// 长按操作
						main_listStudent
								.setOnItemLongClickListener(new OnItemLongClickListener() {

									@Override
									public boolean onItemLongClick(
											AdapterView<?> parent, View view,
											int position, long id) {
										Dialog d = new DeleteDialog(
												MainActivity.this, position,
												handler, StudentGlobal.list, dao.getDbUtil().getWritableDatabase())
												.getDialog();
										d.show();
										return true;
									}
								});
					} else {
						handler.sendEmptyMessage(4);
					}
					break;
				case 4:
					new OutDialog(MainActivity.this).getDialog().show();
					break;
				default:
					break;
				}
			}
		};
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == MAIN_ACTIVITY_CODE) {
			if (resultCode == ShowMessageActivity.SHOW_MESSAGE_CODE) {
				handler.sendEmptyMessage(1);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				long param=dao.delAllStudents();
				if(param>0){
					//一定要先清空
					StudentGlobal.list.clear();
					ThreadStudentMessageForMe tsmfm = new ThreadStudentMessageForMe(
							handler);
					new Thread(tsmfm).start();
					dao.addStudents(StudentGlobal.list);
				}
				return true;
			}
		});
		
		return true;
	}

}
