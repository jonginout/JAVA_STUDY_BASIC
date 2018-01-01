package com.cloud.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.repository.mapper.ForumMapper;
import com.cloud.repository.vo.Comment;
import com.cloud.repository.vo.Forum;
import com.cloud.repository.vo.ForumFile;
import com.cloud.repository.vo.Like;
import com.sun.org.apache.regexp.internal.recompile;

@Service("ForumService")
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumMapper mapper;
	

	@Override
	public Forum forumDetail(int forumNo) throws Exception {
		return mapper.selectOneForum(forumNo);
	}

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

	@Override
	public void addComment(Comment comment) throws Exception {
		mapper.insertComment(comment);
		mapper.updateDateForum(comment.getForumNo());
	}

	@Override
	public void addView(int forumNo) throws Exception {
		mapper.insertView(forumNo);
	}

	@Override
	public int chkWriter(Forum forum) throws Exception {
		return mapper.checkWriter(forum);
	}

	@Override
	public void removeForum(Forum forum) throws Exception {
		mapper.deleteForum(forum);
	}

	@Override
	public void editForum(Forum forum) throws Exception {
		mapper.updateForum(forum);
	}

	@Override
	public int chkCommentWriter(Comment comment) throws Exception {
		return mapper.checkCommentWriter(comment);
	}

	@Override
	public void removeComment(Comment comment) throws Exception {
		mapper.deleteComment(comment);
	}

	@Override
	public void editComment(Comment comment) throws Exception {
		mapper.updateComment(comment);
	}

	@Override
	public void addForum(Forum forum) throws Exception {
		mapper.insertForum(forum);
	}

	@Override
	public void addForumFile(ForumFile forumFile) throws Exception {
		mapper.insertForumFile(forumFile);
	}

	@Override
	public List<ForumFile> forumFileList(ForumFile forumFile) throws Exception {
		return mapper.selectForumFile(forumFile);
	}


}
