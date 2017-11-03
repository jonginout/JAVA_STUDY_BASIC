package category;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/category/categorymodifyprocess")
public class CategoryModifyProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			String name = request.getParameter("name");
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			int homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
			int categoryGroupNo = Integer.parseInt(request.getParameter("categoryGroupNo"));
			
			CategoryDomain category = new CategoryDomain();
			category.setName(name);
			category.setCategoryNo(categoryNo);
			
			CategoryDAO dao = new CategoryDAO();
			dao.modifyCategory(category);
			
			
			request.setAttribute("homepageNo", homepageNo);
			request.setAttribute("categoryGroupNo", categoryGroupNo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/category/categorylist");
			rd.forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}	
}
