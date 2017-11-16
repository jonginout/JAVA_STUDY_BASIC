package com.jongin.search;


public class Page{
	
	private int listPerPage; //페이지당 리스트 수
	private int pageNo;
	private int begin;
	private int end;
	
	
	public int getListPerPage() {
		return listPerPage;
	}
	public void setListPerPage(int listPerPage) {
		this.listPerPage = listPerPage;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPageNo() {
		return pageNo;
	}
	public int getBegin() {
		return (pageNo - 1) * listPerPage + 1;
	}
	public int getEnd() {
		return pageNo * listPerPage;
	}
	@Override
	public String toString() {
		return "Page [listPerPage=" + listPerPage + ", pageNo=" + pageNo + ", begin=" + begin + ", end=" + end + "]";
	}
	
	
}
