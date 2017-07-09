package com.lxq.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.lxq.entity.Student;

public class PullForXml {
	public static List<Student> getStudents(InputStream in) {
		List<Student> list = new ArrayList<Student>();
		Student s = null;

		try {
			XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance()
					.newPullParser();
			xmlPullParser.setInput(in, "utf-8");
			int eventType = xmlPullParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					String tag=xmlPullParser.getName();
					
					if(tag.equalsIgnoreCase("student")){
						s=new Student();
					}else if(s!=null){
						if(tag.equalsIgnoreCase("name")){
							String name=xmlPullParser.nextText();
							s.setName(name);
						}else if(tag.equalsIgnoreCase("age")){
							String ageStr=xmlPullParser.nextText();
							if(ageStr!=null&&!"null".equalsIgnoreCase(ageStr)){
								s.setAge(Integer.parseInt(ageStr));
							}
						}else if(tag.equalsIgnoreCase("sex")){
							String param=xmlPullParser.nextText();
							s.setSex(param);
						}else if(tag.equalsIgnoreCase("tel")){
							String param=xmlPullParser.nextText();
							s.setTel(param);
						}else if(tag.equalsIgnoreCase("address")){
							String param=xmlPullParser.nextText();
							s.setAddress(param);
						}else if(tag.equalsIgnoreCase("qq")){
							String param=xmlPullParser.nextText();
							s.setQq(param);
						}else if(tag.equalsIgnoreCase("major")){
							String param=xmlPullParser.nextText();
							s.setMajor(param);
						}else if(tag.equalsIgnoreCase("birthday")){
							String param=xmlPullParser.nextText();
							s.setBirthday(param);
						}else if(tag.equalsIgnoreCase("educational")){
							String param=xmlPullParser.nextText();
							s.setEducational(param);
						}else if(tag.equalsIgnoreCase("emergency")){
							String param=xmlPullParser.nextText();
							s.setEmergency(param);
						}else if(tag.equalsIgnoreCase("hobby")){
							String param=xmlPullParser.nextText();
							s.setHobby(param);
						}else if(tag.equalsIgnoreCase("wantWork")){
							String param=xmlPullParser.nextText();
							s.setWantWork(param);
						}else if(tag.equalsIgnoreCase("dream")){
							String param=xmlPullParser.nextText();
							s.setDream(param);
						}else if(tag.equalsIgnoreCase("photo")){
							String param=xmlPullParser.nextText();
							s.setPhoto(param);
						}
					}
					break;
					
				case XmlPullParser.END_TAG:
					if(xmlPullParser.getName().equalsIgnoreCase("student")&&s!=null&&list!=null){
						list.add(s);
						s=null;
					}
					break;
				default:  break;
				}
				//记录下一个标签编号
				eventType=xmlPullParser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
