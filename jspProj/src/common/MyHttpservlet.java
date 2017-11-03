package common;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyHttpservlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("MyhttpServlet - service()");
		
		service((HttpServletRequest)request, (HttpServletResponse)response);
		
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("MyhttpServlet - service(http)");
		
		// http 시작라인에 들어온 메소드 방식
		// get방식일땐 get / post 방식일땐 post .. 등 
		// 방식이 누구인지 꺼낸다
		String method = request.getMethod();
		
		if("GET".equals(method)) {
			doGet(request, response);
		}else if ("POST".equals(method)) {
			doPost(request, response);			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("MyHttpServlet = doPost");
		response.sendError(405, "지원되지 않는 메서드 요청입니다.");
		//인위적으로 에러를 발생시킨다.
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("MyHttpServlet = doGet");
		response.sendError(405, "지원되지 않는 메서드 요청입니다.");
		
	}

}
