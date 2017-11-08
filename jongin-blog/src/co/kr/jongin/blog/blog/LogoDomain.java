package co.kr.jongin.blog.blog;

public class LogoDomain {
	private int logoNo;
	private int blogNo;
	private String filePath;
	private String oriName;
	private String systemName;
	private long fileSize;
	
	
	
	@Override
	public String toString() {
		return "LogoDomain [logoNo=" + logoNo + ", blogNo=" + blogNo + ", filePath=" + filePath + ", oriName=" + oriName
				+ ", systemName=" + systemName + ", fileSize=" + fileSize + "]";
	}
	public int getLogoNo() {
		return logoNo;
	}
	public void setLogoNo(int logoNo) {
		this.logoNo = logoNo;
	}
	public int getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
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

	
	
}