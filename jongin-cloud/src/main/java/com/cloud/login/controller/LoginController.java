package com.cloud.login.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.login.service.LoginService;
import com.cloud.repository.vo.Member;
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService service;
	
	@RequestMapping("/loginform.do")
	public void loginForm() throws Exception {}
	
	
	@RequestMapping("/naverLogin.do")
	public void naverLogin() throws Exception {}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, Object> login(Member member, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		System.out.println(member);
		member.setMaxVolume(209715200);
		session.setAttribute("user", member);
		map.put("result", true);
		
		return map;
	}

	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		Member user = (Member)session.getAttribute("user");
		if(user != null) {
			session.invalidate();
		}
		return "redirect:/login/loginform.do";
	}
	
}
