package com.jongin.board;

import java.util.Date;

public class FileDomain {
	
	private int fileNo;
	private int no;
	private String filePath;
	private String oriName;
	private String systemName;
	private Date regDate;
	private long fileSize;
	
	
	@Override
	public String toString() {
		return "FileDomain [fileNo=" + fileNo + ", no=" + no + ", filePath=" + filePath + ", oriName=" + oriName
				+ ", systemName=" + systemName + ", fileSize=" + fileSize + "]";
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	
}









