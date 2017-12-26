package com.cloud.repository.mapper;

import java.util.List;

import com.cloud.repository.vo.Comment;
import com.cloud.repository.vo.Forum;
import com.cloud.repository.vo.Like;

public interface ForumMapper {
	
	public List<Forum> selectForum(Forum forum) throws Exception;
	public List<Comment> selectComment(Comment comment) throws Exception;
	public int checkLike(Like like) throws Exception;
	public void insertLike(Like like) throws Exception;

}
