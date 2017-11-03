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

import boardComment.BoardCommentDAO;
import boardComment.BoardCommentDomain;
import common.Page;
import common.PageResult;


@WebServlet("/jsp/diary/diarydetail")
public class DiaryDetail extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		int pageNo = 1;
		
		try {	
			//board_no 를 받는다.
			int no=-1;
			
			try {
				if(request.getParameter("no") == null) {
					no = (int)(request.getAttribute("no"));  
				}
				else {
					no = Integer.parseInt(request.getParameter("no"));  //list 에서 넘어온 no
				}
			} catch (NumberFormatException e) {
			}
			
			// 다이어리글 상세
			DiaryDomain diary = new DiaryDomain();
			DiaryDAO dao = new DiaryDAO();
			diary = dao.detailDiary(no);
			
			if(request.getAttribute("commentContent") != null)
				{
					String commentContent = (String)request.getAttribute("commentContent");
					int commentNo = (int)request.getAttribute("commentNo");
					request.setAttribute("commentContent", commentContent);
					request.setAttribute("commentNo", commentNo);
				}
			
			// 댓글 페이징
	
				try {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				} catch (NumberFormatException e) {
				}
			
			// 페이지당 리스트를 담는다.	
			int listPerPage= 5;
			int pagePerBlock = 5;
			
			Page page = new Page(pageNo, listPerPage, no);
		
			
			// 댓글 리스트
			BoardCommentDAO cDAO = new BoardCommentDAO();
			List<BoardCommentDomain> list = new ArrayList<>();
			
			list = cDAO.commentListBoard(page);
			
			int count = cDAO.listCommentCount(no);
			PageResult pageResult = new PageResult(pageNo, count, pagePerBlock);
		
			request.setAttribute("no", no);
			request.setAttribute("list", list);
			request.setAttribute("diary", diary);
			request.setAttribute("pageResult", pageResult);   
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diaryDetail.jsp");
			rd.forward(request, response);
			
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
}

