package com.lxq.entity;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private String name;
	private int age;
	private String sex;
	private String tel;
	private String address;
	private String qq;
	private String major;
	private String birthday;
	private String educational;
	private String emergency;
	private String wantWork;
	private String hobby;
	private String dream;
	private String photo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getWantWork() {
		return wantWork;
	}

	public void setWantWork(String wantWork) {
		this.wantWork = wantWork;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getDream() {
		return dream;
	}

	public void setDream(String dream) {
		this.dream = dream;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex
				+ ", tel=" + tel + ", address=" + address + ", qq=" + qq
				+ ", major=" +major+ ", birthday=" + birthday + ", aducational="
				+ educational + ", emergency=" + emergency + ", wantWork="
 + wantWork + ", hobby=" + hobby + ", dream="
				+ dream + ", photo=" + photo + "]";
	}

}
