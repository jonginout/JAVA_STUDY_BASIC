package diary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;

@WebServlet("/jsp/diary/diarydelete")
public class DiaryDelete extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			int no = -1;
			try {
				no = Integer.parseInt(request.getParameter("no"));
			} catch (NumberFormatException e) {
			}
			
			
			DiaryDAO dao = new DiaryDAO();
			DiaryDomain diary = new DiaryDomain();
			
			diary = dao.searchBoard(no);
			int homepageNo = diary.getHomepageNo();
			int categoryNo = diary.getCategoryNo();
			
			boolean chk = WriterCheck.writerChk(request, homepageNo);
			if(!chk ) {
				AlertBack.alertAndBack(response, "작성자만 삭제 가능합니다.");
				return;
			}
			
			diary.setNo(no);
			diary.setCategoryNo(categoryNo);
			diary.setHomepageNo(homepageNo);
			
			dao.deleteDiary(no);
			
			request.setAttribute("diary", diary);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diarylist");
			rd.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}	
}
