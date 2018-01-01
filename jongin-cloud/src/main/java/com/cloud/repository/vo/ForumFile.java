package com.cloud.repository.vo;

public class ForumFile {
	
	private int forumFileNo;
	private int forumNo;
	private String fileName;
	private String path;
	private String ext;
	public int getForumFileNo() {
		return forumFileNo;
	}
	public void setForumFileNo(int forumFileNo) {
		this.forumFileNo = forumFileNo;
	}
	public int getForumNo() {
		return forumNo;
	}
	public void setForumNo(int forumNo) {
		this.forumNo = forumNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	@Override
	public String toString() {
		return "ForumFile [forumFileNo=" + forumFileNo + ", forumNo=" + forumNo + ", fileName=" + fileName + ", path="
				+ path + ", ext=" + ext + "]";
	}
	
	
}
