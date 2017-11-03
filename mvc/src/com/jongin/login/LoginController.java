package com.jongin.login;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;

@Controller
public class LoginController {
	
	
	@RequestMapping(value="/login/loginform.do")
	public ModelAndView loginForm(HttpServletRequest request) 
					throws Exception {
		
		ModelAndView mav = new ModelAndView("/login/loginForm.jsp");
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("cid".equals(c.getName())) {
					mav.addAttribute("cid", c.getValue());
					break;
				}
			}
		}
		return mav;
	}
	
	
	@RequestMapping(value="/login/loginprocess.do")
	public ModelAndView loginProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		// 로그인 처리
		/*
		 * - 사용자가 화면에서 입력한 파라미터 정보를 추출
		 * 
		 * - 데이터베이스에 사용자가 입력한 정보가 있는지 체크
		 * 
		 * - 데이터베이스에 사용자 정보가 존재한다면 : 세션에 사용자 정보를 등록 : 메인 페이지로 이동
		 * 
		 * - 데이터베이스에 사용자 정보가 존재하지 않으면 : 로그인 폼 페이지로 이동
		 * 
		 */

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String idcheck = request.getParameter("idcheck");
		
		ModelAndView mav = new ModelAndView();
		
		Cookie c = new Cookie("cid", id);
		// 쿠키 삭제
		c.setMaxAge(0);
		if ("Y".equals(idcheck)) {
			c.setMaxAge(60 * 60 * 24);
			mav.addAttribute("cid", id);
		}
		response.addCookie(c);

		// id : admin, pass : 1111 이면 성공
		LoginDAO dao = new LoginDAO();
		Login login = new Login();
		login.setId(id);
		login.setPass(pass);

		Login user = dao.detailMember(login);
		if (user != null) {
			user.setAccessTime(new Date());
			HttpSession session = request.getSession();
			session.setAttribute("user", login);

			// ${pageContext.request.contextPath}
			// pageContext.getRequest().getContextPath()

			// 메인페이지 이동하기
			mav.setView("redirect:/main/main.do");
			return mav;
			
		} else {
			request.setAttribute("error", "입력한 정보가 올바르지 않습니다.");

			// 로그인 폼으로 이동하기
			mav.setView("/login/loginForm.jsp");
			return mav;
		}

	}

	
	@RequestMapping(value="/login/logout.do")
	public ModelAndView logout(	HttpServletRequest request)
					throws Exception {
		
		// 로그인 : 세션에 user를 등록
		// 로그아웃 : 세션을 삭제처리
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 메인페이지로 이동하기
		return new ModelAndView("redirect:/main/main.do");
	}
}

