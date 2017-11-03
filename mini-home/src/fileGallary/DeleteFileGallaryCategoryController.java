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

@WebServlet("/jsp/filegallary/deletefilegallarycategory")
public class DeleteFileGallaryCategoryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int homepageNo = -1;
		int category_no = -1;
		int categoryGroupNo = -1;
		
		try {
			
			try {
				homepageNo = Integer.parseInt(request.getParameter("homepage_no"));
				category_no = Integer.parseInt(request.getParameter("category_no"));
				categoryGroupNo = Integer.parseInt(request.getParameter("category_group_no"));
				
			} catch (NumberFormatException e) {	}
			
			GallaryDAO dao = new GallaryDAO();
			dao.deleteCategory(category_no);
			List<CategoryDomain> list = dao.listFileCategory(homepageNo, categoryGroupNo);
			
			request.setAttribute("homepage_no", request.getParameter("homepage_no"));
			request.setAttribute("category_no", request.getParameter("category_no"));
			request.setAttribute("category_group_no", request.getParameter("category_group_no"));
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher(
				"/jsp/listCategory/listFileCategory.jsp"	
			);
			rd.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}






