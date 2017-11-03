package diary;

import java.util.Date;

public class DiaryDomain {
	private int no;
	private int homepageNo;
	private int categoryNo;
	
	private String title;
	private String writer;
	private String content;
	
	private Date regDate;
	private Date updateDate;
	private String secret;
	
	private int categoryGroupNo;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getHomepageNo() {
		return homepageNo;
	}

	public void setHomepageNo(int homepageNo) {
		this.homepageNo = homepageNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getCategoryGroupNo() {
		return categoryGroupNo;
	}

	public void setCategoryGroupNo(int categoryGroupNo) {
		this.categoryGroupNo = categoryGroupNo;
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



}
