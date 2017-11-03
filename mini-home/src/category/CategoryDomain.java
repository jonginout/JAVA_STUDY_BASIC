package category;

public class CategoryDomain {
	private int categoryNo;
	private int categoryGroupNo;
	private int homepageNo;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getCategoryGroupNo() {
		return categoryGroupNo;
	}
	public void setCategoryGroupNo(int categoryGroupNo) {
		this.categoryGroupNo = categoryGroupNo;
	}
	
	
}
