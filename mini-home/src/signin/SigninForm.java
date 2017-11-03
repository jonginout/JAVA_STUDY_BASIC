package signin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/signin/signinform")
public class SigninForm extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 로그인 폼으로 이동하기
		RequestDispatcher rd = request.getRequestDispatcher(
			"/jsp/signin/signinForm.jsp"
		);
		rd.forward(request, response);
		
	}
	
	
	
}
