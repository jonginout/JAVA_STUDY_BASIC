package com.cloud.repository.vo;

import java.sql.Date;

public class Member {

	private int memberNo;
	private String id;
	private String name;
	private String email;
	private String pass;
	private String adress;
	private Date reg_date;
	private Integer maxVolume;
	private String tel;
	private String profileImg;
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Integer getMaxVolume() {
		return maxVolume;
	}
	public void setMaxVolume(Integer maxVolume) {
		this.maxVolume = maxVolume;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", id=" + id + ", name=" + name + ", email=" + email + ", pass=" + pass
				+ ", adress=" + adress + ", reg_date=" + reg_date + ", maxVolume=" + maxVolume + ", tel=" + tel
				+ ", profileImg=" + profileImg + "]";
	}
	
	
	
}
