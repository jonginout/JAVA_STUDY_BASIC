package com.jongin.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/writeform")
public class WriteFormBoardController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서비스가 보통 하는 일
		
		// 1.데이터를 구하기 위한 파라미터
		// 2.데이터를 구하는 것
		// 3.데이터 구한 것을 사용할 페이지로 이동
		// 4.단순 페이지 이동
		
		
		// 로그인 체크 여부
//		HttpSession session = request.getSession();
//		Login login = (Login)session.getAttribute("user");
//		if (login == null) {
//			response.setContentType("text/html; charset=UTF-8");
//			
//			PrintWriter out = response.getWriter();
//			StringBuffer html = new StringBuffer();
//			html.append("<script>");
//			html.append("alert('로그인이 필요한 작업 입니다.');");
//			html.append("location.href='"+request.getContextPath()+"/login/loginform';");
//			html.append("</script>");
//			out.write(html.toString());
//			out.close();
//			
//			
//		}else {
//			
			RequestDispatcher rd = request.getRequestDispatcher(
					"/board/writeForm.jsp"
					);
			rd.forward(request, response);
//		}
		
//		String url = "/board/writeForm.jsp";
//		LoginCheck  lc = new LoginCheck();
//		
//		lc.sessionCheck(request, response, url);
		
//		RequestDispatcher rd = request.getRequestDispatcher(url);
//		rd.forward(request, response);
	}
	
}
