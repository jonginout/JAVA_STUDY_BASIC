package day03;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day03/test02")
public class test02 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Test02 서블릿 호출됨");
		
		request.setAttribute("msg", "테스트");
		request.setAttribute("time", new Date());
		
		// sendRedirect는 프로젝트명을 생략하지 않는다.
		response.sendRedirect("/jspProj/day03/test02.jsp");
		
	}
	
}
