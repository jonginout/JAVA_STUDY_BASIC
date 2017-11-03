package category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Page;
import common.PageResult;

@WebServlet("/jsp/category/categorylist")
public class CategoryList extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int homepageNo=-1;
		int categoryGroupNo=3;  
		int add=0;
		int modifyNo = -1;
		int categoryNo = -1;
		
		int pageNo = 1;
		int listPerPage = 10;
		int pagePerBlock = 5;
		
		CategoryDAO dao = new CategoryDAO();
		
		try {
			if(request.getParameter("categoryNo") != null & request.getParameter("modifyNo") == null) {
				categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
				categoryGroupNo = dao.searchCategoryGroupNo(categoryNo);
			}
			
			if(request.getParameter("pageNo") != null)
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			//추가해서 넘어왔을때
			if(request.getParameter("add") != null) {
				add = Integer.parseInt(request.getParameter("add"));
				System.out.println(add);
			}
			
			if(request.getAttribute("modifyNo") != null) {
				modifyNo = (int)request.getAttribute("modifyNo");
			}
			
			if(request.getParameter("homepageNo") != null & request.getParameter("categoryGroupNo") != null)
			{
				homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
				categoryGroupNo = Integer.parseInt(request.getParameter("categoryGroupNo"));
			}
			if(request.getAttribute("homepageNo") != null & request.getAttribute("categoryGroupNo") != null)
			{	
				homepageNo = (int)request.getAttribute("homepageNo");
				categoryGroupNo = (int)request.getAttribute("categoryGroupNo");
			}
			
			List<CategoryDomain> list = new ArrayList<>();
			
			//list = dao.selectCategory(homepageNo, categoryGroupNo);
			
			Page page = new Page(pageNo, listPerPage, categoryGroupNo, homepageNo);
			list = dao.selectCategoryPageList(page);
			
			CategoryDomain category = new CategoryDomain();
			category.setCategoryGroupNo(categoryGroupNo);
			category.setHomepageNo(homepageNo);
			
			int count = dao.selectCategoryListCount(category);
			PageResult pageResult = new PageResult(pageNo, count, listPerPage);
/*		
			System.out.println("modifyNO : " + modifyNo);
			System.out.println("add : " + add);
			System.out.println("homepageNo : " + homepageNo);
			System.out.println("categoryGroupNo : " + categoryGroupNo);
			System.out.println("list : " + list);
			
*/			request.setAttribute("modifyNo", modifyNo);
			request.setAttribute("add", add);
			request.setAttribute("homepageNo", homepageNo);
			request.setAttribute("categoryGroupNo", categoryGroupNo);
			request.setAttribute("list", list);
			request.setAttribute("pageResult", pageResult);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/category/categoryList.jsp");
			
			
			rd.forward(request, response);
		
		} catch (Exception e) {
			throw new ServletException();
		}
	}
}
