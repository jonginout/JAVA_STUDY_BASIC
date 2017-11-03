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

@WebServlet("/jsp/boardcomment/commentdelete")
public class BoardCommentDelete extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// comment_no 파라미터를 얻는다.
			int commentNo = -1;
			
			try {
				commentNo = Integer.parseInt(request.getParameter("commentNo"));
			} catch (NumberFormatException e) {}	
			
		
			
			// comment_no를 통해서 board_no를 얻는다
			BoardCommentDAO dao = new BoardCommentDAO();
			int boardNo = dao.searchBoardNo(commentNo);
			
			
			DiaryDAO dDao = new DiaryDAO();
			DiaryDomain diary = new DiaryDomain();
			diary = dDao.searchBoard(boardNo);
			int homepageNo = diary.getHomepageNo();
			
			boolean chk = WriterCheck.writerChk(request, homepageNo);
			if(!chk ) {
				AlertBack.alertAndBack(response, "작성자만 삭제 가능합니다.");
				return;
			}
		
			// comment 삭제
			dao.deleteComment(commentNo);
		
			// board_no를 전해준다.
			request.setAttribute("no", boardNo);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diarydetail");			
			rd.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
