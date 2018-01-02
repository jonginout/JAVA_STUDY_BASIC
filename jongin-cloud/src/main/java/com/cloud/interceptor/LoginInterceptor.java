package com.cloud.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.cloud.login.service.LoginService;
import com.cloud.repository.vo.AutoLogin;
import com.cloud.repository.vo.Member;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	LoginService service;
	
	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");
		
		if (member != null) {
			return true;
		}else {
			
			Cookie userCookie = WebUtils.getCookie(request, "userCookie");
			
			if(userCookie != null) {
				
				String sessionId = userCookie.getValue();
				AutoLogin autoLogin = service.checkAutoLogin(sessionId);
				
				System.out.println(sessionId);
				if(autoLogin != null) {
					
					session.setAttribute("user", service.autoLoginToMember(autoLogin.getMemberNo()));
					return true;
				}
				
			}
			
			response.sendRedirect(request.getContextPath() + "/login/loginform.do");
			return false;
		}
		
		
		// preHandle의 return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
        // 따라서 true로하면 컨트롤러 uri로 가게 됨.
	}
}
