package common;

public class Page {

	private int pageNo;
	private int listPerPage; //페이지당 리스트 수
	private int begin;
	private int end;
	private int no;  //board_no
	
	private int homepageNo;
	private int categoryNo;
	
	public Page() {	}
	
	public Page(int pageNo, int listPerPage) {
		this.pageNo = pageNo;
		this.listPerPage = listPerPage;
	}
	
	public Page(int pageNo, int listPerPage, int boardNo) {
		this.pageNo = pageNo;
		this.listPerPage = listPerPage;
		this.no = boardNo;
	}
	
	public Page(int pageNo, int listPerPage, int categoryNo, int homepageNo) {
	/*	System.out.println("pageNo : " + pageNo);
		System.out.println("listPerPage : " + listPerPage);
		System.out.println("categoryNo : " + categoryNo);
		System.out.println("homepageNo : " + homepageNo);*/
		this.pageNo = pageNo;
		this.listPerPage = listPerPage;
		this.homepageNo = homepageNo;
		this.categoryNo = categoryNo;
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


	
	public int getNo() {
		return no;
	}

	public int getPageNo() {
		return pageNo;
	}
	
	//현재 페이지에서 가져올 글 시작 번호
	public int getBegin() {
		System.out.println("begin : " + (pageNo - 1) *listPerPage + 1);
		return (pageNo - 1) *listPerPage + 1;
	}
	
	public int getEnd() {
		System.out.println("end : " + pageNo * listPerPage);
		return pageNo * listPerPage;
	}
}
