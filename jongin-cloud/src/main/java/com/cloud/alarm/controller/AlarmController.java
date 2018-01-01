package com.cloud.alarm.controller;

import java.util.HashMap;
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
import com.cloud.repository.vo.Member;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

	@Autowired
	AlarmService service;
	
	@Autowired
	ForumService forumService;
	
	@RequestMapping("/countalarm.json")
	@ResponseBody
	public int countAlarm(Alarm alarm, HttpSession session) throws Exception {

		Member user = (Member)session.getAttribute("user");
		alarm.setReceiver(user.getMemberNo());
		
		return service.lengthAlarm(alarm);
	}
	
	@RequestMapping("/alarm.json")
	@ResponseBody
	public Map<String, Object> alarmList(Alarm alarm, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("result", false);
		
		Member user = (Member)session.getAttribute("user");
		if(user.getMemberNo()==alarm.getReceiver()) {
			map.put("alarms", service.alarmList(alarm));
			map.put("result", true);
		}
		
		return map;
	}
	
	@RequestMapping("/readalarm.json")
	@ResponseBody
	public void readAlarm(Alarm alarm) throws Exception {
		service.viewAlarm(alarm);
	}
	
	@RequestMapping("/deletealarm.json")
	@ResponseBody
	public void deleteAlarm(Alarm alarm) throws Exception {
		service.removeAlarm(alarm);
	}
	
	@RequestMapping("/readallalarm.json")
	@ResponseBody
	public void readAllAlarm(Alarm alarm, HttpSession session) throws Exception {
		Member user = (Member)session.getAttribute("user");
		alarm.setReceiver(user.getMemberNo());
		service.viewAllAlarm(alarm);
	}
	
	@RequestMapping("/detailalarm.json")
	@ResponseBody
	public Map<String, Object> detailalarm(int forumNo) throws Exception {
		Map<String, Object> map = new HashMap<>();

		Comment comment = new Comment();
		comment.setForumNo(forumNo);
		
		map.put("forum", forumService.forumDetail(forumNo));
		map.put("comments", forumService.commentList(comment));
		
		return map;
	}
	
}
