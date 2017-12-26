package com.cloud.repository.vo;

import java.util.Date;

public class Forum {

	private int forumNo;
	private String title;
	private String content;
	private int writer;
	private String writerId;
	private Date regDate;
	private Date updateDate;
	private int view;
	private String status;
	private String category;
	private Integer start;
	private Integer count;
	
	public int getForumNo() {
		return forumNo;
	}
	public void setForumNo(int forumNo) {
		this.forumNo = forumNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
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
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Forum [forumNo=" + forumNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", writerId=" + writerId + ", regDate=" + regDate + ", updateDate=" + updateDate + ", view=" + view
				+ ", status=" + status + ", category=" + category + ", start=" + start + ", count=" + count + "]";
	}


	


	
	
}
