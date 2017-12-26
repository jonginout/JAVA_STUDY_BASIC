package com.cloud.repository.vo;

public class Like {
	private int likeNo;
	private int forumNo;
	private int liker;
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	public int getForumNo() {
		return forumNo;
	}
	public void setForumNo(int forumNo) {
		this.forumNo = forumNo;
	}
	public int getLiker() {
		return liker;
	}
	public void setLiker(int liker) {
		this.liker = liker;
	}
	@Override
	public String toString() {
		return "Like [likeNo=" + likeNo + ", forumNo=" + forumNo + ", liker=" + liker + "]";
	}
	
	
}
