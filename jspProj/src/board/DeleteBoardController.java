package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/delete")
public class DeleteBoardController extends HttpServlet {
	
	@Override
	protected void service( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// post 방식일 경우 파라미터 한글 처리
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDAO bb = new BoardDAO();
		try {

			bb.deleteBoard(no);
			response.sendRedirect("/jspProj/board/list");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}











