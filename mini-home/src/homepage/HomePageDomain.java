package homepage;

public class HomePageDomain {
	
	private int homepageNo;
	private String id;
	private String bgm;
	private String profile;
	private String backgroundImg;
	private String introduce;
	private String title;
	private int visits;
	private String openRange;
	private boolean photoGallaryUseYn;
	private boolean guestBookUseYn;
	private boolean diaryUseYn;
	private boolean fileGallaryUseYn;
	public int getHomepageNo() {
		return homepageNo;
	}
	public void setHomepageNo(int homepageNo) {
		this.homepageNo = homepageNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBgm() {
		return bgm;
	}
	public void setBgm(String bgm) {
		this.bgm = bgm;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getBackgroundImg() {
		return backgroundImg;
	}
	public void setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public String getOpenRange() {
		return openRange;
	}
	public void setOpenRange(String openRange) {
		this.openRange = openRange;
	}
	public boolean getPhotoGallaryUseYn() {
		return photoGallaryUseYn;
	}
	public void setPhotoGallaryUseYn(char photoGallaryUseYn) {
		if (photoGallaryUseYn=='Y') {
			this.photoGallaryUseYn = true;
		}else {			
			this.photoGallaryUseYn = false;
		}
	}
	public boolean getGuestBookUseYn() {
		return guestBookUseYn;
	}
	public void setGuestBookUseYn(char guestBookUseYn) {
		if (guestBookUseYn=='Y') {
			this.guestBookUseYn = true;
		}else {			
			this.guestBookUseYn = false;
		}	}
	public boolean getDiaryUseYn() {
		return diaryUseYn;
	}
	public void setDiaryUseYn(char diaryUseYn) {
		if (diaryUseYn=='Y') {
			this.diaryUseYn = true;
		}else {			
			this.diaryUseYn = false;
		}	
	}
	public boolean getFileGallaryUseYn() {
		return fileGallaryUseYn;
	}
	public void setFileGallaryUseYn(char fileGallaryUseYn) {
		if (fileGallaryUseYn=='Y') {
			this.fileGallaryUseYn = true;
		}else {			
			this.fileGallaryUseYn = false;
		}
	}
	@Override
	public String toString() {
		return "HomePageDomain [homepageNo=" + homepageNo + ", id=" + id + ", bgm=" + bgm + ", profile=" + profile
				+ ", backgroundImg=" + backgroundImg + ", introduce=" + introduce + ", title=" + title + ", visits="
				+ visits + ", photoGallaryUseYn=" + photoGallaryUseYn + ", guestBookUseYn=" + guestBookUseYn
				+ ", diaryUseYn=" + diaryUseYn + ", fileGallaryUseYn=" + fileGallaryUseYn + "]";
	}
	

	
	
}
