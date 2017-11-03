package com.jongin.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jongin.login.Login;

public class AuthFilter implements Filter{

	// 로그인이 필요없는 페이지 주소를 담는다.
	private List<String> list = new ArrayList<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String nonelogin = filterConfig.getInitParameter("nonelogin");
		String [] arr = nonelogin.split(";");
		
		for (String np : arr) {
			list.add(np.trim());
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		
		String uri = hRequest.getRequestURI();
		uri = uri.substring(hRequest.getContextPath().length()); //프로젝트 빼기
		
		if (list.indexOf(uri) == -1) {
			HttpSession session = hRequest.getSession();
			Login user = (Login)session.getAttribute("user");
			if(user==null) {
				//로그인이 되어있지 않은 상태
				hResponse.sendRedirect(hRequest.getContextPath()+"/login/loginform");
			}else {				
				chain.doFilter(request, response);
			}
		}else {			
			chain.doFilter(request, response);
		}
		

		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	
}
