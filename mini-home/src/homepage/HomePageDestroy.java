package homepage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/homepage/homepagedestroy")
public class HomePageDestroy extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		int homepageNo = Integer.parseInt(request.getParameter("homepage_no")); 
		
		HomePageDAO dao = new HomePageDAO();
		
		try {
			
			dao.destroyHomePage(homepageNo);
			response.sendRedirect(request.getContextPath()+"/jsp/main/main");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
