package kr.co.mlec.interceptor;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mlec.form.MemberVO;

@Controller
@RequestMapping("/interceptor")
public class Login {
	
	@RequestMapping("/loginForm.do")
	public void loginForm() {}
	
	@RequestMapping("/login.do")
	public String login(HttpSession session) {
		MemberVO member = new MemberVO();
		member.setId("admin");
		member.setName("관리자");
		session.setAttribute("user", member);
		
		return "redirect:myPage.do";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm.do";
	}
}


