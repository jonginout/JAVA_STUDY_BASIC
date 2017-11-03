package diary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/diary/diarymodifyprocess")
public class DiaryModifyProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String regDate = sdf.format(new Date());
			
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			DiaryDomain diary = new DiaryDomain();
			diary.setTitle(title);
			diary.setContent(content);
			diary.setNo(no);
			
			DiaryDAO dao = new DiaryDAO();
			
			dao.modifyDiary(diary);
			
			
			request.setAttribute("no", no);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diarydetail");
			rd.forward(request, response);	

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}	
}
