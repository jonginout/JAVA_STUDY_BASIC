package boardComment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;
import diary.DiaryDAO;
import diary.DiaryDomain;

@WebServlet("/jsp/boardcomment/commentmodify")
public class BoardCommentModifyController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			int commentNo = Integer.parseInt(request.getParameter("commentNo"));
			
			// commentNo 를 통해서 board_no 와 content 내용을 꺼낸다.
			BoardCommentDAO dao = new BoardCommentDAO();
			BoardCommentDomain comment = new BoardCommentDomain();
			
			comment = dao.searchCommentContent(commentNo);
			int boardNo = comment.getNo();
			String commentContent = comment.getContent();
			
			DiaryDAO dDao = new DiaryDAO();
			DiaryDomain diary = new DiaryDomain();
			diary = dDao.searchBoard(boardNo);
			int homepageNo = diary.getHomepageNo();
			
			boolean chk = WriterCheck.writerChk(request, homepageNo);
			if(!chk ) {
				AlertBack.alertAndBack(response, "작성자만 수정 가능합니다.");
				return;
			}
			request.setAttribute("no",boardNo);
			request.setAttribute("commentContent", commentContent);
			request.setAttribute("commentNo", commentNo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diarydetail");
			rd.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		
	}	
}
