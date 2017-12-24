package com.cloud.repository.vo;

public class AuthSms {
	private int no;
	private String authKey; 
	private String tel;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "AuthSms [no=" + no + ", authKey=" + authKey + ", tel=" + tel + "]";
	}

	
	
}
