package com.jongin.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login/loginprocess")
public class LoginProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String idcheck = request.getParameter("idcheck");
		
		Cookie c = new Cookie("cid", id);
		// 쿠키 삭제
		c.setMaxAge(0); //쿠키 즉시 삭제 -1은 브라우져 꺼져있음 삭제
		if ("Y".equals(idcheck)) {
			c.setMaxAge(60 * 60 * 24);
			request.setAttribute("cid", id);
		}
		response.addCookie(c);
		
		Login param = new Login();
		param.setId(id);
		param.setPass(pass);
		
		LoginMapper dao;
		dao = new LoginMapper();
		
		Login login = dao.detailMember(param);
		
		if (login!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", login);
			response.sendRedirect(request.getContextPath()+"/main/main");
		}else {
			
			// 일부로 에러를 보낸다
			request.setAttribute("error", "입력한 정보가 올바르지 않습니다.");
			RequestDispatcher rd = 
					request.getRequestDispatcher("/login/loginForm.jsp");
			rd.forward(request, response);
		}
		
	}
}
