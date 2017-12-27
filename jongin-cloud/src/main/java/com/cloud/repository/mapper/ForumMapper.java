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
	public void insertComment(Comment comment) throws Exception;
	public void insertView(int forumNo) throws Exception;
	public int checkWriter(Forum forum) throws Exception;
	public void deleteForum(Forum forum) throws Exception;
	public void updateForum(Forum forum) throws Exception;
	public int checkCommentWriter(Comment comment) throws Exception;
	public void deleteComment(Comment comment) throws Exception;
	public void updateComment(Comment comment) throws Exception;
	public void insertForum(Forum forum) throws Exception;

}
