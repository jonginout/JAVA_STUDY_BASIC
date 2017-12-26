package com.cloud.repository.mapper;

import java.util.List;

import com.cloud.repository.vo.Forum;

public interface ForumMapper {
	
	public List<Forum> selectForum(Forum forum) throws Exception;

}
