package day05.longin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day05/login/main")
public class LoginMain extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 로그인 폼으로 이동하기
		RequestDispatcher rd = request.getRequestDispatcher("/day05/login/main.jsp");
		rd.forward(request, response);
		
	}
	
	
	
}
