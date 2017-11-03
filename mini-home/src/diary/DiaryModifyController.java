package diary;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;

@WebServlet("/jsp/diary/diarymodify")
public class DiaryModifyController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int no = Integer.parseInt(request.getParameter("no"));
		
			DiaryDomain diary = new DiaryDomain();
			DiaryDAO dao = new DiaryDAO();
			
			diary = dao.searchBoard(no);
			int homepageNo = diary.getHomepageNo();
			
			boolean chk = WriterCheck.writerChk(request, homepageNo);
			if(!chk ) {
				AlertBack.alertAndBack(response, "작성자만 수정 가능합니다.");
				return;
			}
			request.setAttribute("diary",diary);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diaryModifyForm.jsp");
			rd.forward(request, response);
		
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
