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

}
