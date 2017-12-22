package com.cloud.repository.vo;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class Member {

	private int memberNo;
	private String id;
	private String name;
	private String email;
	private String pass;
	private String address;
	private Date regDate;
	private Integer maxVolume;
	private String profileImg;
	private String type;
	
	/////////////////////////////////
	
	private MultipartFile[] images;
	public MultipartFile[] getImages() {
		return images;
	}
	public void setImages(MultipartFile[] images) {
		this.images = images;
	}
	/////////////////////////////////
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getMaxVolume() {
		return maxVolume;
	}
	public void setMaxVolume(Integer maxVolume) {
		this.maxVolume = maxVolume;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", id=" + id + ", name=" + name + ", email=" + email + ", pass=" + pass
				+ ", address=" + address + ", regDate=" + regDate + ", maxVolume=" + maxVolume + ", profileImg="
				+ profileImg + ", type=" + type + ", images=" + Arrays.toString(images) + "]";
	}

}
