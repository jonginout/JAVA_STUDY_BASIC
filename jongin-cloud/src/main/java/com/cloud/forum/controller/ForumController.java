package com.cloud.forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cloud.forum.service.ForumService;
import com.cloud.repository.vo.Comment;
import com.cloud.repository.vo.Forum;
import com.cloud.repository.vo.Like;
import com.cloud.repository.vo.Member;
import com.google.gson.Gson;

@Controller
@RequestMapping("/forum")
public class ForumController {
	
	@Autowired
	ForumService service;
	
	@RequestMapping("/forum.do")
	public void forum(Forum forum) throws Exception {}
	

	@RequestMapping("/forum.json")
	@ResponseBody
	public Map<String, Object> forumJson(Forum forum) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		if(forum.getStart()==null) {
			forum.setStart(0);
			forum.setCount(20);
		}
		
		List<Forum> forums = service.forumList(forum);
		if(forums.size()>1) {				
			for (Forum f : forums) {
				if(f.getCategory().equals("FREE")) {
					f.setCategory("자유");
				}
			}
			map.put("result", new Gson().toJson(forums));
		}else {
			map.put("result", false);		
		}
		return map;
		
	}

	@RequestMapping("/addforum.json")
	@ResponseBody
	public int addForum(Forum forum, HttpSession session) throws Exception {
		Member user = (Member) session.getAttribute("user");
		forum.setWriter(user.getMemberNo());
		forum.setWriterId(user.getId());
		service.addForum(forum);		
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
	public int addCommetn(Comment comment) throws Exception {
		service.addComment(comment);
		return comment.getCommentNo();
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
		if(service.chkWriter(forum)>0) {
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
		if(service.chkWriter(forum)>0) {
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
		if(service.chkCommentWriter(comment)>0) {
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
		if(service.chkCommentWriter(comment)>0) {
			service.editComment(comment);
			map.put("result", true);			
		}
		//체크 작성자
		
		System.out.println(comment);
		
		return map;
	}
	
	
}
