/*
 * - 필터 사용하기
 * 	Filter 인터페이스 상속
 * 
 * - 필터를 특정 서블릿 또는 특정 페이지와 연결
 *	web.xml 파일 이용해서 등록하기
 * 
*/
package day07;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter{

	@Override
	// 최초 한번만 메모리 올라갈떄 실행
	// (이 웹프로젝트가 로딩될떄)
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter init 실행");
		String appName = filterConfig.getInitParameter("app_name");
		String port = filterConfig.getInitParameter("server_port");
		System.out.println(appName+","+ port);
	}

	@Override
	// 필터 자동 호출
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("doFilter 호출됨");
		
	}


	@Override
	public void destroy() {}
	
	
}
