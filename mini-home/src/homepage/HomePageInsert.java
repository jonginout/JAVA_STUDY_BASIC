package homepage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homepage.HomePageDAO;

@WebServlet("/jsp/homepage/homepageinsert")
public class HomePageInsert extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
		String id = request.getParameter("id");
		
		HomePageDAO dao = new HomePageDAO();
		
		try {
			
			dao.insertHomePage(id);
			response.sendRedirect(request.getContextPath()+"/jsp/homepage/myhomepagelist?id="+id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}