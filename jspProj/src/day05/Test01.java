package day05;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day05/test01")
public class Test01 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String name = URLEncoder.encode(
					request.getParameter("name"), "utf-8"
				); 
		String value = URLEncoder.encode(
				request.getParameter("value"), "utf-8"
				); 
		// 쿠키 이름이나 값에 한글이 정상적으로 입력되기 위해
		// 인코딩을 해준다.
		// URLEncodder 클래스 활용
		
		// 쿠키 생성
		Cookie c = new Cookie(name, value);
		
		
		// 별도의 유효시간 설정이 되어있지 않은 경우
		// 브라우져가 닫히기 전까지만 유지됨
		//c.setMaxAge(60*60*24); // 초단위 (하루)
		c.setMaxAge(60 * 60);
		
		// 생성된 쿠키를 프로젝트 전체에서 사용하고 싶다/
		c.setPath("/");
		
		// 쿠키를 사용자에게 응답하기
		response.addCookie(c);
		
		response.sendRedirect("test01.jsp");
		
	}
	
}
