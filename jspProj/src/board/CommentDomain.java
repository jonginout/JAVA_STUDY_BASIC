package board;

import java.util.Date;

public class CommentDomain {
	private int no;
	private int commentNo;
	private String writer;
	private String content;
	private Date regDate;
	
	@Override
	public String toString() {
		return "CommentDomain [no=" + no + ", commentNo=" + commentNo + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	
}









