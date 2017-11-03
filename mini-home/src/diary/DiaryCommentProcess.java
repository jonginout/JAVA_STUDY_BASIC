package diary;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardComment.BoardCommentDAO;
import boardComment.BoardCommentDomain;

@WebServlet("/jsp/diary/diarycommentprocess")
public class DiaryCommentProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(request.getParameter("commentNo") == null) {
			try {
				// diaryDetail.jsp 에서 url로 넘어온 board_no
				int no = -1;
				try {
					no = Integer.parseInt(request.getParameter("no"));
				} catch (NumberFormatException e) {}

				// diaryDetail.jsp 폼 태그에서 넘어온 파라미터
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				
				BoardCommentDomain comment = new BoardCommentDomain();
				comment.setNo(no);
				comment.setWriter(writer);
				comment.setContent(content);
	
				BoardCommentDAO dao = new BoardCommentDAO();
				dao.insertComment(comment);
				
				request.setAttribute("no", no);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diarydetail");
				rd.forward(request, response);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} else {
			
			try {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				String content = request.getParameter("content");
				int no = Integer.parseInt(request.getParameter("no"));
				
				BoardCommentDAO dao = new BoardCommentDAO();
				BoardCommentDomain comment = new BoardCommentDomain();
				comment.setCommentNo(commentNo);
				comment.setContent(content);
				
				
				dao.modifyComment(comment);
			
				request.setAttribute("no", no);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diarydetail");
				rd.forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
