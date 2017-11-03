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

@WebServlet("/jsp/diary/diarywrite")
public class DiaryWriteProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
		
	try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String regDate = sdf.format(new Date());
		
			int homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			DiaryDomain diary = new DiaryDomain();
		
			diary.setHomepageNo(homepageNo);
			diary.setCategoryNo(categoryNo);
			
			diary.setTitle(title);
			diary.setWriter(writer);
			diary.setContent(content);
			
			DiaryDAO dao = new DiaryDAO();
			
			dao.insertDiary(diary);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diarylist");
			
			request.setAttribute("diary", diary);
			rd.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
