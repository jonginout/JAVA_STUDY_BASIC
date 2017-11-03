package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/modify")
public class ModifyBoardController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post 방식일 경우 파라미터 한글 처리
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		BoardDomain board = new BoardDomain();
		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		BoardDAO bb = new BoardDAO();
		try {
			
			bb.modifyBoard(board);
			response.sendRedirect("/jspProj/board/list");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
