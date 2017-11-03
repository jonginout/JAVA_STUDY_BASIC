package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/commentmodify")
public class ModifyCommentController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post 방식일 경우 파라미터 한글 처리
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int commentNo = Integer.parseInt(request.getParameter("comment_no"));
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		CommentDomain comment = new CommentDomain();
		comment.setNo(no);
		comment.setCommentNo(commentNo);
		comment.setContent(content);
		comment.setWriter(writer);
		
		BoardDAO bb = new BoardDAO();
		try {
			
			bb.modifyComment(comment);
			response.sendRedirect("/jspProj/board/detail?no="+no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
