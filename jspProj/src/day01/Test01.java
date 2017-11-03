package day01;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

// http://localhost:8080/jspProj/day01/test01
// 가상의 경로를 주는거다.
@WebServlet("/day01/test01")
public class Test01 implements Servlet{

	@Override
	public void destroy() {
		// 서블릿 객체가 메모리에 올라갔다가
		// 메모리에서 올라간게 메모리에서 내려올때
		// 호출되는 메소드
		
		// 직접 건들일이 거진 없다.
	}

	@Override
	public ServletConfig getServletConfig() {
		// 서블릿의 환경 같은거..
		
		// 직접 건들일이 거진 없다.
		return null;
	}

	@Override
	public String getServletInfo() {
		//서블릿의 대략적인 설명
		
		// 직접 건들일이 거진 없다.
		return null;
	}

	// 여기서 부터는 자주 사용
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// 서블릿이 메모리에 올라갈때 
		// 최초 한번만 호줄최는 메소드
		
		// 두번째 요청부터는 있는것을 가져다 쓴다.
		// 이닛이 호출이 안된다.
		System.out.println("호출");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// 사용자가 요청한 정보에 대한 처리를 진행
		
		// ServletRequest은 HttpServletRequest
		// 과 비슷한 것 http프로토콜이 아닐 뿐
		
		System.out.println("처리");		
		int a = Integer.parseInt(request.getParameter("a"));	
		int b = Integer.parseInt(request.getParameter("b"));	
		System.out.println(a);
		System.out.println(b);
	}
	
}
