package com.cloud.login.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.login.service.LoginService;
import com.cloud.repository.vo.Member;
import com.google.gson.Gson;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService service;

	@RequestMapping("/loginform.do")
	public void loginForm() throws Exception {
	}

	@RequestMapping("/naverLogin.do")
	public void naverLogin() throws Exception {
	}

	@RequestMapping("/signupform.do")
	public void signupForm() throws Exception {
	}

	@RequestMapping("/login.json")
	@ResponseBody
	public Map<String, Object> login(Member member, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("result", false);
		
		String loginType = member.getType();
		
		if(loginType!=null) {
			
			member.setPass(loginType);
			
			if(service.checkId(member.getId())<1) {
				
	//			member.setAddress("");
				service.signup(member);
				
				System.out.println("외부 로그인 최초 회원가입!");
			}
		
		}
		
		Member user = service.login(member);
		if(user!=null) {
			user.setPass(null);
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getId());
			map.put("result", true);
		}else {
			map.put("result", false);
		}
		
		System.out.println(user);
		return map;
	}
	
	@RequestMapping("/signup.json")
	@ResponseBody
	public void signup(Member member, HttpServletRequest request) throws Exception {
		
		String postCode = request.getParameter("postCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");

		String adress = "["+postCode+"]"+address1+" "+address2;
		String email = email1+"@"+email2;
		member.setAddress(adress);
		member.setEmail(email);
		member.setType("NORMAL");
		
		// root는 공용폴더
		// path는 실제 경로

		String uploadDir = "profile";
		System.out.println(uploadDir);

		File f = new File(uploadDir);
		if (!f.exists()) {
			f.mkdirs();
		}
		// 폴더 만들기

		for (MultipartFile file : member.getImages()) {
			if (file.isEmpty())
				continue;

			String oriFileName = file.getOriginalFilename();
			// 원본이름으로 null을 구분

			System.out.println("원본 파일명 : " + oriFileName);

			if (oriFileName != null && !oriFileName.equals("")) {
				// 확장자 처리
				String ext = "";
				int index = oriFileName.lastIndexOf(".");
				if (index != -1) {
					// 파일명에서 확장자명(.포함)을 추출
					ext = oriFileName.substring(index);
				}
				
				// 고유한 파일명 만들기
				String saveFileName = "mlec-" + UUID.randomUUID().toString() + ext;
				System.out.println("저장할 파일명 : " + saveFileName);

				// 임시저장된 파일을 원하는 경로에 저장
				file.transferTo(new File(uploadDir + "/" + saveFileName));
				// transferTo 를 해야 내가 원하는 곳으로 저장된다.
				
				member.setProfileImg(uploadDir + "/" + saveFileName);
			}
		}

		System.out.println(member);
		service.signup(member);
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		Member user = (Member) session.getAttribute("user");
		if (user != null) {
			session.invalidate();
		}
		return "redirect:/login/loginform.do";
	}

	// 아이디 체크
	@RequestMapping("/idchk.do")
	@ResponseBody
	public Map<String, Boolean> idChk(String id) throws Exception {
		Map<String, Boolean> map = new HashMap<>();
		boolean result = false;
		if (service.checkId(id) > 0) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}

}
