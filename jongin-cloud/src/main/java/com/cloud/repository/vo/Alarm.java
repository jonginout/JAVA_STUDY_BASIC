package com.cloud.repository.vo;

import java.util.Date;

public class Alarm {
	private int alarmNo;
	private int receiver;
	private int forumNo;
	private int commentNo;
	private String content;
	private String isRead;
	private Date regDate;
	private String url;
	public int getAlarmNo() {
		return alarmNo;
	}
	public void setAlarmNo(int alarmNo) {
		this.alarmNo = alarmNo;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public int getForumNo() {
		return forumNo;
	}
	public void setForumNo(int forumNo) {
		this.forumNo = forumNo;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Alarm [alarmNo=" + alarmNo + ", receiver=" + receiver + ", forumNo=" + forumNo + ", commentNo="
				+ commentNo + ", content=" + content + ", isRead=" + isRead + ", regDate=" + regDate + ", url=" + url
				+ "]";
	}
	

	
}
