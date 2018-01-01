package com.cloud.repository.vo;

import java.util.Date;

public class Comment {
	private int commentNo;
	private int forumNo;
	private String writerId;
	private int writer;
	private String content;
	private Date regDate;
	private Date updateDate;
	
	////////////////////////////
	private String profileImg;
	private String type;
	private int forumWriter;

	public int getForumWriter() {
		return forumWriter;
	}
	public void setForumWriter(int forumWriter) {
		this.forumWriter = forumWriter;
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
	////////////////////////////
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getForumNo() {
		return forumNo;
	}
	public void setForumNo(int forumNo) {
		this.forumNo = forumNo;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Comments [commentNo=" + commentNo + ", forumNo=" + forumNo + ", writerId=" + writerId + ", writer="
				+ writer + ", content=" + content + ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}
	
	
}
