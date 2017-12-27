package com.cloud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cloud.repository.vo.Member;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Member vo = (Member)session.getAttribute("user");
		if(vo != null) {
			/*int msgCount = new MsgServiceImpl().notReadCount(vo.getId());
			vo.setMsgCount(msgCount);
			
			session.setAttribute("user", vo);*/
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/login/loginform.do");
		return false;
	}
}