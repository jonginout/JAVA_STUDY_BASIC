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
import category.CategoryDAO;
import common.Page;
import common.PageResult;

@WebServlet("/jsp/diary/diarylist")
public class DiaryList extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<DiaryDomain> diaryList = new ArrayList<>();
		
		DiaryDomain diary = new DiaryDomain();
		DiaryDAO dao = new DiaryDAO();
	
		int pageNo = 1;
		int listPerPage = 10;
		int pagePerBlock = 5;
		
		if(request.getAttribute("diary") != null ) {
			//System.out.println("request.getAttribute(\"diary\") != null");
			try {
				if(request.getParameter("pageNo") != null)
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
				//파라미터 값 hompepage_no, category_no 를 가져온다.	
				diary = (DiaryDomain)request.getAttribute("diary");
				int homepageNo = diary.getHomepageNo();
				int categoryNo = diary.getCategoryNo();
				int no = diary.getNo();
				CategoryDAO cDAO = new CategoryDAO();
				int categoryGroupNo = cDAO.searchCategoryGroupNo(categoryNo);
				// homepageNo, categoryNo 를 도메인에 담아서 dao.selectDiary 실행
				// 결과값 list에 담는다
				diary.setHomepageNo(homepageNo);
				diary.setCategoryNo(categoryNo);
				
				
				// 페이징 pageNo, listPerPage, categoryNo, homepageNo
				Page page = new Page(pageNo, listPerPage, categoryNo, homepageNo);
				
				diaryList = dao.selectDiaryPageList(page);
			
//				diaryList = dao.selectDiary(diary);
				
				//pageResult
				int count = dao.selectDiaryListCount(diary);
				System.out.println("count" + count);
				PageResult pageResult = new PageResult(pageNo, count, listPerPage);
				request.setAttribute("categoryGroupNo", categoryGroupNo);
				request.setAttribute("homepageNo", homepageNo);
				request.setAttribute("categoryNo", categoryNo);
				request.setAttribute("pageResult", pageResult);
				request.setAttribute("diaryList", diaryList);
				
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diaryList.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} else if(request.getParameter("no") != null){
			System.out.println("request.getParameter(\"no\") != null");
			if(request.getParameter("pageNo") != null)
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			int no = Integer.parseInt(request.getParameter("no"));
			try {
				diary = dao.searchBoard(no);
				int homepageNo = diary.getHomepageNo();
				int categoryNo = diary.getCategoryNo();
				CategoryDAO cDAO = new CategoryDAO();
				int categoryGroupNo = cDAO.searchCategoryGroupNo(categoryNo);
				// homepageNo, categoryNo 를 도메인에 담아서 dao.selectDiary 실행
				// 결과값 list에 담는다
				diary.setHomepageNo(homepageNo);
				diary.setCategoryNo(categoryNo);
				
				// 페이징 pageNo, listPerPage, categoryNo, homepageNo
				Page page = new Page(pageNo, listPerPage, categoryNo, homepageNo);
				
				diaryList = dao.selectDiaryPageList(page);
			
				//diaryList = dao.selectDiary(diary);
				//pageResult
				int count = dao.selectDiaryListCount(diary);
				System.out.println("count" + count);
				PageResult pageResult = new PageResult(pageNo, count, listPerPage);
				request.setAttribute("categoryGroupNo", categoryGroupNo);
				request.setAttribute("homepageNo", homepageNo);
				request.setAttribute("categoryNo", categoryNo);
				request.setAttribute("pageResult", pageResult);
				request.setAttribute("diaryList", diaryList);
				
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diaryList.jsp");
				rd.forward(request, response);
				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} else { 
			try {
				System.out.println("else문 실행");
				if(request.getParameter("pageNo") != null)
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
				int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
				int homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
				
				CategoryDAO cDAO = new CategoryDAO();
				int categoryGroupNo = cDAO.searchCategoryGroupNo(categoryNo);
				diary.setCategoryNo(categoryNo);
				diary.setHomepageNo(homepageNo);
				
				// 페이징 pageNo, listPerPage, categoryNo, homepageNo
				Page page = new Page(pageNo, listPerPage, categoryNo, homepageNo);
				
				diaryList = dao.selectDiaryPageList(page);
				int count = dao.selectDiaryListCount(diary);
				
				System.out.println("count" + count);
				PageResult pageResult = new PageResult(pageNo, count, listPerPage);
				request.setAttribute("categoryGroupNo", categoryGroupNo);
				request.setAttribute("homepageNo", homepageNo);
				request.setAttribute("categoryNo", categoryNo);
				request.setAttribute("pageResult", pageResult);
				request.setAttribute("diaryList", diaryList);
				
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/diary/diaryList.jsp");
			
				rd.forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
