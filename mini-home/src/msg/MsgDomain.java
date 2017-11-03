package msg;

import java.util.Date;

public class MsgDomain {
	private int msgNo;
	private String sendId;
	private String recId;
	private String content;
	private Date sendDate;
	private boolean isRead;
	public int getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(char isRead) {
		if (isRead=='Y') {
			this.isRead = true;
		}else {			
			this.isRead = false;
		}
	}
	

}
