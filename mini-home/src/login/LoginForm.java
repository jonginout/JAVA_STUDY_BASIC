package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/login/loginform")
public class LoginForm extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("cid".equals(c.getName())) {
					request.setAttribute("cid", c.getValue());
					break;
				}
			}
		}		

		// 로그인 폼으로 이동하기
		RequestDispatcher rd = request.getRequestDispatcher(
			"/jsp/login/loginForm.jsp"
		);
		rd.forward(request, response);
		
	}
	
	
	
}
