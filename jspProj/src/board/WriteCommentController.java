package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/writecomment")
public class WriteCommentController extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// post 방식일 경우 파라미터 한글 처리
		
		
		request.setCharacterEncoding("utf-8");
		
		// 화면에서 넘어온 파라미터 추출하기
		int no = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		System.out.println(no);
		System.out.println(content);
		System.out.println(writer);
		
		// Domain 클래스에 파라미터 담기
		CommentDomain comment = new CommentDomain();
		comment.setNo(no);
		comment.setContent(content);
		comment.setWriter(writer);
		
		// DAO를 호출해서 작업처리 지시
		BoardDAO dao = new BoardDAO();
		try {
			dao.insertComment(comment);
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		
		// 처리된 결과를 보여줄 화면 관련 처리
		// 게시물 등록이 성공하였음..
		response.sendRedirect("/jspProj/board/detail?no="+no);

	}
}










