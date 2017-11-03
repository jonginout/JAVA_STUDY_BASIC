package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jsp/login/logout")
public class LogOut extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 로그인 : 세션에 user를 등록
		// 로그아웃 : 세션을 삭제처리
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 메인으로 이동
		response.sendRedirect(request.getContextPath()+"/jsp/main/main");
	}
	
	
	
}
