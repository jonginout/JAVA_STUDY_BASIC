package com.cloud.forum.service;

import java.util.List;

import com.cloud.repository.vo.Forum;

public interface ForumService {

	public List<Forum> forumList(Forum forum) throws Exception;

}
