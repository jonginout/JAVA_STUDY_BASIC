package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/write")
public class WriteBoardController extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// post 방식일 경우 파라미터 한글 처리
		request.setCharacterEncoding("utf-8");
		
		// 화면에서 넘어온 파라미터 추출하기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		System.out.println(title);
		System.out.println(content);
		System.out.println(writer);
		
		// Domain 클래스에 파라미터 담기
		BoardDomain board = new BoardDomain();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		// DAO를 호출해서 작업처리 지시
		BoardDAO dao = new BoardDAO();
		try {
			dao.insertBoard(board);
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		
		// 처리된 결과를 보여줄 화면 관련 처리
		// 게시물 등록이 성공하였음..
		response.sendRedirect("/jspProj/board/list");

	}
}










