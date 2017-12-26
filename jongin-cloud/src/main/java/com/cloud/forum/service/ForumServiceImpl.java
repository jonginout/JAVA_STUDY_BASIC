package com.cloud.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.repository.mapper.ForumMapper;
import com.cloud.repository.vo.Forum;

@Service("ForumService")
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumMapper mapper;
	
	@Override
	public List<Forum> forumList(Forum forum) throws Exception {
		return mapper.selectForum(forum);
	}
	

}
