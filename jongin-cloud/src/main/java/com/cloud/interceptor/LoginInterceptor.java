package com.cloud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cloud.repository.vo.Member;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");
		if (member != null) {
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/login/loginform.do");
		return false;
		
	}
}
