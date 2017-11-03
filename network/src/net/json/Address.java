package net.json;


public class Address {
	private String postNo;
	private String basic;
	private String detail;
	
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "Address [postNo=" + postNo + ", basic=" + basic + ", detail=" + detail + "]";
	}
	
	
//	List<String> 
	
}
