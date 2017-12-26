package com.cloud.forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.forum.service.ForumService;
import com.cloud.repository.vo.Comment;
import com.cloud.repository.vo.Forum;
import com.cloud.repository.vo.Like;
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
	
	
}
