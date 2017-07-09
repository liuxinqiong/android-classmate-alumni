package com.lxq.activity;

import com.lxq.entity.Student;
import com.lxq.util.DBUtil;
import com.lxq.util.DeleteDialog;
import com.lxq.util.StudentGlobal;
import com.lxq.util.StudentImageGloabal;
import com.lxq.util.ThreadStudentNetImageForMe;
import com.lxq.util.UrlForMe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowMessageActivity extends Activity implements OnClickListener{
	public static final int SHOW_MESSAGE_CODE = 2000;
    
    private Button show_btnUserSms;
    private Button show_btnUserCall;
    private ImageView show_ivMore;
    private ImageView show_ivUserPhoto;
    private LinearLayout show_layMore;
    private LinearLayout user_show_message_lay;
    
	private TextView show_txtMore;
	private TextView show_txtUserAdd;
	private TextView show_txtUserAge;
	private TextView show_txtUserBir;
	private TextView show_txtUserDre;
	private TextView show_txtUserEducational;
	private TextView show_txtUserEmergency;
	private TextView show_txtUserHobby;
	private TextView show_txtUserMajor;
	private TextView show_txtUserName;
	private TextView show_txtUserPho;
	private TextView show_txtUserQq;
	private TextView show_txtUserSex;
	private TextView show_txtUsersWantWork;
	
	Handler handler;
	boolean isShowingFlag=false;
	Intent intent;
	String tel;
	int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_message);
		show_btnUserSms=(Button)findViewById(R.id.show_btnUserSms);
		show_btnUserCall=(Button)findViewById(R.id.show_btnUserCall);
		show_ivMore=(ImageView)findViewById(R.id.show_ivMore);
		show_ivUserPhoto=(ImageView)findViewById(R.id.show_ivUserPhoto);
		show_layMore=(LinearLayout)findViewById(R.id.show_layMore);
		show_txtMore=(TextView)findViewById(R.id.show_txtMore);	
		show_txtUserAdd=(TextView)findViewById(R.id.show_txtUserAdd);
		show_txtUserAge=(TextView)findViewById(R.id.show_txtUserAge);
		show_txtUserBir=(TextView)findViewById(R.id.show_txtUserBir);
		show_txtUserDre=(TextView)findViewById(R.id.show_txtUserDre);
		show_txtUserEducational=(TextView)findViewById(R.id.show_txtUserEducational);
		show_txtUserEmergency=(TextView)findViewById(R.id.show_txtUserEmergency);
		show_txtUserHobby=(TextView)findViewById(R.id.show_txtUserHobby);
		show_txtUserMajor=(TextView)findViewById(R.id.show_txtUserMajor);
		show_txtUserName=(TextView)findViewById(R.id.show_txtUserName);
		show_txtUserPho=(TextView)findViewById(R.id.show_txtUserPho);
		show_txtUserQq=(TextView)findViewById(R.id.show_txtUserQq);
		show_txtUserSex=(TextView)findViewById(R.id.show_txtUserSex);
		show_txtUsersWantWork=(TextView)findViewById(R.id.show_txtUsersWantWork);
		user_show_message_lay=(LinearLayout)findViewById(R.id.user_show_message_lay);
		show_btnUserSms.setOnClickListener(this);
		show_btnUserCall.setOnClickListener(this);
		user_show_message_lay.setOnClickListener(this);
		
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what){
				case 1: 
					Intent intent =new Intent(ShowMessageActivity.this,MainActivity.class);
					setResult(SHOW_MESSAGE_CODE, intent);
					finish();
					break;
				default: break;
				}
			}
		};
		
		intent=getIntent();
		if(intent!=null){		
			Bundle bundle=intent.getExtras();
			position=bundle.getInt("pos");
			Student s=(Student) bundle.getSerializable("student");
			show_txtUserName.setText(s.getName());
			//这中错误真是火了，整形一定要转成string
			show_txtUserAge.setText(s.getAge()+"");
			show_txtUserSex.setText(s.getSex());
			show_txtUserBir.setText(s.getBirthday());
			show_txtUserAdd.setText(s.getAddress());
			show_txtUserQq.setText(s.getQq());
			show_txtUserPho.setText(s.getTel());
			show_txtUserDre.setText(s.getDream());
			show_txtUserEmergency.setText(s.getEmergency());
			show_txtUserMajor.setText(s.getMajor());
			show_txtUserEducational.setText(s.getEducational());
			show_txtUserHobby.setText(s.getHobby());
			show_txtUsersWantWork.setText(s.getWantWork());		
			tel=s.getTel();
			
			if(StudentImageGloabal.image.containsKey(UrlForMe.QUERY_STUDENT_IMG+s.getPhoto())){
				show_ivUserPhoto.setImageBitmap(StudentImageGloabal.image.get(UrlForMe.QUERY_STUDENT_IMG+s.getPhoto()));
			}else{
				ThreadStudentNetImageForMe tsnfm=new ThreadStudentNetImageForMe(show_ivUserPhoto, s.getPhoto(), StudentImageGloabal.image);
				new Thread(tsnfm).start();
			}
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==show_btnUserSms){
			Uri smsToUri=Uri.parse("smsto:"+tel);
			Intent intent=new Intent(Intent.ACTION_SENDTO,smsToUri);
			intent.putExtra("sms_body", "经常联系.");
			startActivity(intent);
		}else if(v==show_btnUserCall){
			Intent intent=new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+tel));
			startActivity(intent);
		}else if(v==user_show_message_lay){
			if(isShowingFlag){
				show_layMore.setVisibility(View.VISIBLE);
				show_ivMore.setImageResource(R.drawable.up);
				show_txtMore.setText(R.string.user_pack);
				isShowingFlag=false;
			}else{
				show_layMore.setVisibility(View.GONE);
				show_ivMore.setImageResource(R.drawable.down);
				show_txtMore.setText(R.string.user_more);
				isShowingFlag=true;
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(Menu.NONE, Menu.FIRST, Menu.FIRST, "删除该用户信息");
		menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=openOrCreateDatabase(DBUtil.MY_DB_NAME, MODE_PRIVATE, null);
				Dialog d=new DeleteDialog(ShowMessageActivity.this, position, handler, StudentGlobal.list, db).getDialog();
				d.show();
				return true;
			}
		});
		return true;
	}
}
