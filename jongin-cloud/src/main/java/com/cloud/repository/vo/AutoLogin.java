package com.cloud.repository.vo;

import java.util.Date;

public class AutoLogin {
	private int autoLoginNo;
	private int memberNo;
	private String sessionId;
	private Date limitTime;
	
	public int getAutoLoginNo() {
		return autoLoginNo;
	}
	public void setAutoLoginNo(int autoLoginNo) {
		this.autoLoginNo = autoLoginNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}
	@Override
	public String toString() {
		return "AutoLogin [autoLoginNo=" + autoLoginNo + ", memberNo=" + memberNo + ", sessionId=" + sessionId
				+ ", limitTime=" + limitTime + "]";
	}
	
	
	
}
