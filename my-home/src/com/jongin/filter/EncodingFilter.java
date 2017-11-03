//필터클래스로 선언


package com.jongin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{

	private String charset;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter init 실행");
		charset = filterConfig.getInitParameter("charset");
		System.out.println(charset);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	
	}

	@Override
	public void destroy() {
		
	}

	
	
}
