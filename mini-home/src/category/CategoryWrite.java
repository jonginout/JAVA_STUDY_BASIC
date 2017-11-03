package category;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;

@WebServlet("/jsp/category/categorywrite")
public class CategoryWrite extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	
	
		try {
			int homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
			int categoryGroupNo = Integer.parseInt(request.getParameter("categoryGroupNo"));
			String name = request.getParameter("name");
			

			boolean chk = WriterCheck.writerChk(request, homepageNo);
			if(!chk ) {
				AlertBack.alertAndBack(response, "홈피주인만 추가 가능합니다.");
				return;
			}
			
			CategoryDomain category = new CategoryDomain();
			
			category.setCategoryGroupNo(categoryGroupNo);
			category.setHomepageNo(homepageNo);
			category.setName(name);
		
			CategoryDAO dao = new CategoryDAO();
			dao.insertCategory(category);
			
			request.setAttribute("homepageNo", homepageNo);
			request.setAttribute("categoryGroupNo", categoryGroupNo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/category/categorylist");
			rd.forward(request, response);
		
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
