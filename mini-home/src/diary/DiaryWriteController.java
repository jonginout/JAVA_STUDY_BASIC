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

@WebServlet("/jsp/diary/diarywritecontroller")
public class DiaryWriteController extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		int homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
		
		boolean chk = WriterCheck.writerChk(request, homepageNo);
		if(!chk ) {
			AlertBack.alertAndBack(response, "작성자만 작성 가능합니다.");
			return;
		}
		request.setAttribute("categoryNo", categoryNo);
		request.setAttribute("homepageNo", homepageNo);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diaryWriteForm.jsp");
		
		rd.forward(request, response);
	}

	
}
