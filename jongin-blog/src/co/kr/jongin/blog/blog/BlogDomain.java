package co.kr.jongin.blog.blog;

public class BlogDomain{

    private int blogNo;
    private int memberNo;
    private String title;
    private String tag;
    private int logoNo;
    private int showNum;
    private int visit;
    private String id;
    
    
	@Override
	public String toString() {
		return "BlogDomain [blogNo=" + blogNo + ", memberNo=" + memberNo + ", title=" + title + ", tag=" + tag
				+ ", logoNo=" + logoNo + ", showNum=" + showNum + ", visit=" + visit + ", id=" + id + ", column="
				+ column + ", word=" + word + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String column;
	private String word;
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
    
    
	public int getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getLogoNo() {
		return logoNo;
	}
	public void setLogoNo(int logoNo) {
		this.logoNo = logoNo;
	}
	public int getShowNum() {
		return showNum;
	}
	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	
}
