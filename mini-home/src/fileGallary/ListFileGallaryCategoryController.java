package fileGallary;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CategoryDomain;
import common.Page;
import common.PageResult;
import common.WriterSelect;
import homepage.HomePageDAO;
import homepage.HomePageDomain;


@WebServlet("/jsp/filegallary/listfilegallarycategory")
public class ListFileGallaryCategoryController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 작업을 처리하기 위한 파라미터 추출
		
		try {
			int homepageNo = 1;
			int categoryGroupNo = 1;
			int categoryNo = 1;
			
			try {
				
				homepageNo = Integer.parseInt(request.getParameter("homepage_no"));
				categoryGroupNo = Integer.parseInt(request.getParameter("category_group_no"));
				categoryNo = Integer.parseInt(request.getParameter("category_no"));
			} catch (NumberFormatException e) {	}
			
			GallaryDAO dao = new GallaryDAO();
			List<CategoryDomain> list = dao.listFileCategory(homepageNo, categoryGroupNo);
			//int count = dao.listBoardCount(homepageNo, categoryGroupNo);
			
			
			
			// 데이터를 사용할 페이지로 이동
			request.setAttribute("writer", WriterSelect.writerSel(request));
			request.setAttribute("homepage_no", homepageNo);
			request.setAttribute("category_no", categoryNo);
			request.setAttribute("category_group_no", categoryGroupNo);
			request.setAttribute("list", list);
			RequestDispatcher rd = 
					request.getRequestDispatcher(
							"/jsp/listCategory/listFileCategory.jsp"
					);
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	
}







