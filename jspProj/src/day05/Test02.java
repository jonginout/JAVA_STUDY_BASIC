package day05;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/day05/test02")
public class Test02 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 클라이언트와 관련된 세션 얻기
		HttpSession session = request.getSession();
		
		// 마지막으로 사용자가 접근한 시간
		// 이시간을 기준으로 유지시간을 따지는 것
		long time = session.getLastAccessedTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = new Date(time);
		
		// 세션의 아이디 확인
		String id = session.getId();
		
		// 세션의 유지시간은 기본 30분 정도니깐
		// 변경 (2초)
		session.setMaxInactiveInterval(2);
		
		out.println("<h1>세션 ID : "+ id + "</h1>");
		out.println("<h3>마지막 접근 시간 : "+ sdf.format(date) + "</h3>");
		
		out.close();
		
	}
	
}
