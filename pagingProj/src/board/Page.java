package board;

public class Page {
	private int pageNo;
	private int begin;
	private int end;
	
	
	
	public Page() {}
	public Page(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageNo() {
		return pageNo;
	}
	public int getBegin() {
		return (pageNo - 1) * 10 + 1;
	}
	public int getEnd() {
		return pageNo * 10;
	}
}
