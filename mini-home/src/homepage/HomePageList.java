package homepage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/homepage/myhomepagelist")
public class HomePageList extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		String id = request.getParameter("id");
		
		HomePageDAO dao = new HomePageDAO();
		
		try {
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/homepage/myHomePageList.jsp");
			request.setAttribute("homePageList", dao.listHomePage(id));
			request.setAttribute("id", id);
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
