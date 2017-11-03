package homepage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/homepage/homepagedeletefile")
public class HomePageDeleteFile extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		int homepageNo = Integer.parseInt(request.getParameter("homepage_no")); 
		String field = request.getParameter("field");
		
		HomePageDAO dao = new HomePageDAO();
		
		try {
			
			dao.fileDeleteHomePage(homepageNo, field);
			response.sendRedirect(request.getContextPath()+"/jsp/homepage/homepagesettingform?homepage_no="+homepageNo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
