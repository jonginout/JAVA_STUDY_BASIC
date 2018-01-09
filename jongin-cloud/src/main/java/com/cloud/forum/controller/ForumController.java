package com.cloud.forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.alarm.service.AlarmService;
import com.cloud.forum.service.ForumService;
import com.cloud.repository.vo.Alarm;
import com.cloud.repository.vo.Comment;
import com.cloud.repository.vo.Forum;
import com.cloud.repository.vo.ForumFile;
import com.cloud.repository.vo.Like;
import com.cloud.repository.vo.Member;
import com.google.gson.Gson;

@Controller
@RequestMapping("/forum")
public class ForumController {
	
	@Autowired
	ForumService service;
	
	@Autowired
	AlarmService alarmService;
	
	@RequestMapping("/forum.do")
	public void forum() throws Exception {}
	
	@RequestMapping("/info.do")
	public void info() throws Exception {}
	

	@RequestMapping("/forum.json")
	@ResponseBody
	public Map<String, Object> forumJson(Forum forum) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		System.out.println(forum);
		
		if(forum.getStart()==null) {
			forum.setStart(0);
			forum.setCount(20);
		}
		
		List<Forum> forums = service.forumList(forum);
		if(forums.size()>1) {				
			map.put("result", new Gson().toJson(forums));
		}else {
			map.put("result", false);		
		}
		return map;
		
	}
	
	@RequestMapping("/forumfile.json")
	@ResponseBody
	public Map<String, Object> forumFile(ForumFile forumFile) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("result", false);
		
		List<ForumFile> forumFiles = service.forumFileList(forumFile);
		if(forumFiles.size()>0) {
			map.put("result", true);
			map.put("forumFiles", forumFiles);
		}
		
		return map;
	}
	

	@RequestMapping("/addforum.json")
	@ResponseBody
	public int addForum(
			Forum forum, ForumFile forumFile, HttpSession session
			) throws Exception {
		
		Member user = (Member) session.getAttribute("user");
		forum.setWriter(user.getMemberNo());
		forum.setWriterId(user.getId());
		service.addForum(forum);
		
		
		if(forumFile.getPath()!=null) {
			forumFile.setForumNo(forum.getForumNo());
			service.addForumFile(forumFile);
		}
		System.out.println(forumFile);
		
		return forum.getForumNo();
	}

	@RequestMapping("/comment.json")
	@ResponseBody
	public List<Comment> comment(Comment comment) throws Exception {
		
		return service.commentList(comment);
		
	}
	

	@RequestMapping("/addlike.json")
	@ResponseBody
	public Map<String, Object> addLike(Like like) throws Exception {
		Map<String, Object> map = new HashMap<>();

		if(service.chkLike(like)>0) {
			map.put("result", false);
		}else {			
			service.addLike(like);
			map.put("result", true);
		}
		
		return map;
	}
	
	
	@RequestMapping("/addcommetn.json")
	@ResponseBody
	public int addCommetn(Comment comment, HttpSession session) throws Exception {
		service.addComment(comment);
		// 방금 인서트한 코멘트 NO
		int insertCommentNo = comment.getCommentNo();
		
		////////////////////////////////////
		Member user = (Member)session.getAttribute("user");
		int forumNo = comment.getForumNo();
		Forum forum = service.forumDetail(forumNo);
		//자신글 알람 X
		if(user.getMemberNo()!=forum.getWriter()) {			
			Alarm alarm = new Alarm();
			alarm.setReceiver(forum.getWriter());		
			alarm.setForumNo(forumNo);
			alarm.setCommentNo(insertCommentNo);
			alarm.setContent("'"+comment.getWriterId()+"'님이 ['"+forum.getTitle()+"'] 글에 댓글을 달았습니다.");
			alarmService.addAlarm(alarm);
		}
		////////////////////////////////////
		
		
		return insertCommentNo;
	}
	
	@RequestMapping("/addview.json")
	@ResponseBody
	public void addView(int forumNo) throws Exception {
		service.addView(forumNo);		
	}
	
	@RequestMapping("/deleteforum.json")
	@ResponseBody
	public Map<String, Object> deleteForum(Forum forum, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("result", false);
		Member user = (Member) session.getAttribute("user");
		forum.setWriter(user.getMemberNo());
		if(service.chkWriter(forum)>0 || user.getStatus().equals("ADMIN")) {
			service.removeForum(forum);
			map.put("result", true);			
		}
		//체크 작성자
		
		return map;
	}
	
	@RequestMapping("/editforum.json")
	@ResponseBody
	public Map<String, Object> editForum(Forum forum, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("result", false);
		Member user = (Member) session.getAttribute("user");
		forum.setWriter(user.getMemberNo());
		if(service.chkWriter(forum)>0 || user.getStatus().equals("ADMIN")) {
			service.editForum(forum);
			map.put("result", true);			
		}
		//체크 작성자
		
		return map;
	}
	
	@RequestMapping("/deletecomment.json")
	@ResponseBody
	public Map<String, Object> deleteComment(Comment comment, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("result", false);
		Member user = (Member) session.getAttribute("user");
		comment.setWriter(user.getMemberNo());
		if(service.chkCommentWriter(comment)>0 || user.getStatus().equals("ADMIN")) {
			service.removeComment(comment);
			map.put("result", true);			
		}
		//체크 작성자
		
		return map;
	}
	
	@RequestMapping("/editcomment.json")
	@ResponseBody
	public Map<String, Object> editComment(Comment comment, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("result", false);
		Member user = (Member) session.getAttribute("user");
		comment.setWriter(user.getMemberNo());
		if(service.chkCommentWriter(comment)>0 || user.getStatus().equals("ADMIN")) {
			service.editComment(comment);
			map.put("result", true);			
		}
		//체크 작성자
		
		System.out.println(comment);
		
		return map;
	}
	
	
}
