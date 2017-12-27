package com.cloud.forum.service;

import java.util.List;

import com.cloud.repository.vo.Comment;
import com.cloud.repository.vo.Forum;
import com.cloud.repository.vo.Like;

public interface ForumService {

	public List<Forum> forumList(Forum forum) throws Exception;
	public List<Comment> commentList(Comment comment) throws Exception;
	public int chkLike(Like like) throws Exception;
	public void addLike(Like like) throws Exception;
	public void addComment(Comment comment) throws Exception;
	public void addView(int forumNo) throws Exception;
	public int chkWriter(Forum forum) throws Exception;
	public void removeForum(Forum forum) throws Exception;
	public void editForum(Forum forum) throws Exception;
	public int chkCommentWriter(Comment comment) throws Exception;
	public void removeComment(Comment comment) throws Exception;
	public void editComment(Comment comment) throws Exception;
	public void addForum(Forum forum) throws Exception;

}
