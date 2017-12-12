package com.cloud.repository.vo;

import java.util.List;

public class Tree {
	private String title;
	private Boolean isFolder;
	private Boolean isLazy;
	private String key;
	private List<Tree> children;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getIsFolder() {
		return isFolder;
	}
	public void setIsFolder(Boolean isFolder) {
		this.isFolder = isFolder;
	}
	public Boolean getIsLazy() {
		return isLazy;
	}
	public void setIsLazy(Boolean isLazy) {
		this.isLazy = isLazy;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Tree [title=" + title + ", isFolder=" + isFolder + ", isLazy=" + isLazy + ", key=" + key + ", children="
				+ children + "]";
	}

	
	
	
	
}
