package com.cloud.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.repository.mapper.ForumMapper;
import com.cloud.repository.vo.Comment;
import com.cloud.repository.vo.Forum;
import com.cloud.repository.vo.Like;

@Service("ForumService")
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumMapper mapper;
	
	@Override
	public List<Forum> forumList(Forum forum) throws Exception {
		return mapper.selectForum(forum);
	}

	@Override
	public List<Comment> commentList(Comment comment) throws Exception {
		return mapper.selectComment(comment);
	}

	@Override
	public int chkLike(Like like) throws Exception {
		return mapper.checkLike(like);
	}

	@Override
	public void addLike(Like like) throws Exception {
		mapper.insertLike(like);
	}
	

}
