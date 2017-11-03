package common;

public class PageResult {
	private int pageList = 5;
	private int blockPage = 5;

	private int pageNo;
	private int count;
	private int pagePerBlock;
	private int beginPage, endPage;
	private boolean prev, next;
	private String servletPath;
	
	public PageResult(int pageNo, int count, int pagePerBlock,String servletPath) {
		this.pageNo = pageNo;
		this.count = count;
		this.servletPath = servletPath;
		this.pagePerBlock = pagePerBlock;
		

		//마지막 페이지 번호 구하기
		int lastPage = (count % pagePerBlock ==0) ? count/pagePerBlock 
												  : count/pagePerBlock+1;
		
		System.out.println("lastPage : " + lastPage);
		// Tab 블럭 관련처리
		
		// 페이 번호에 따른 현재 탭번호
		int currTab = (pageNo - 1) / pagePerBlock + 1;
		System.out.println("currTab : " + currTab);
		// 탭의 시작
		beginPage = (currTab - 1) * pagePerBlock + 1 ;
		
		// 탭의 종료
		endPage = (currTab * pagePerBlock > lastPage) ? lastPage 
				   									  : currTab * pagePerBlock;
		
		// 이전이 있으면 true;
		prev = beginPage != 1;
		next = endPage != lastPage;
		
		print();
	}
	
	public PageResult(int pageNo, int count, int pagePerBlock) {
		this.pageNo = pageNo;
		this.count = count;
		this.pagePerBlock = pagePerBlock;
		
		//마지막 페이지 번호 구하기
		int lastPage = (count % pagePerBlock ==0) ? count/pagePerBlock 
												  : count/pagePerBlock+1;
		
		System.out.println("lastPage : " + lastPage);
		// Tab 블럭 관련처리
		
		// 페이 번호에 따른 현재 탭번호
		int currTab = (pageNo - 1) / pagePerBlock + 1;
		System.out.println("currTab : " + currTab);
		// 탭의 시작
		beginPage = (currTab - 1) * pagePerBlock + 1 ;
		
		// 탭의 종료
		endPage = (currTab * pagePerBlock > lastPage) ? lastPage 
				   									  : currTab * pagePerBlock;
		
		// 이전이 있으면 true;
		prev = beginPage != 1;
		next = endPage != lastPage;
		
		print();
	}
	
	public void print() {
		System.out.println("PageResult pageNo : " + pageNo);
		System.out.println("PageResult count : " + count);
		System.out.println("PageResult pagePerBlock : " + pagePerBlock);
		System.out.println("PageResult endPage : " + endPage);
		System.out.println("PageResult beginPage : " + beginPage);
	}
	
	public String getServletPath() {
		return servletPath;
	}
	
	public int getPageList() {
		return pageList;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getCount() {
		return count;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}
	
}
