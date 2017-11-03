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

@WebServlet("/jsp/category/categorydelete")
public class CategoryDelete extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
			int categoryGroupNo = Integer.parseInt(request.getParameter("categoryGroupNo"));
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			
			//System.out.println("categoryGroupNo!!!!!!: "+categoryGroupNo);
			
			boolean chk = WriterCheck.writerChk(request, homepageNo);
			if(!chk ) {
				AlertBack.alertAndBack(response, "작성자만 삭제 가능합니다.");
				return;
			}
			
			CategoryDAO dao = new CategoryDAO();
			dao.deleteCategory(categoryNo);
			
			request.setAttribute("homepageNo", homepageNo);
			request.setAttribute("categoryGroupNo", categoryGroupNo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/category/categorylist");
			rd.forward(request, response);
		
		} catch (Exception e) {}
		
	
	}	
}
