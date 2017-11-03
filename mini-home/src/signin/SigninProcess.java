package signin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import homepage.HomePageDAO;

@WebServlet("/jsp/signin/signinprocess")
public class SigninProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String birth = year+month+day;
		
		
		SigninDAO dao = new SigninDAO();
		HomePageDAO hdao = new HomePageDAO();
		Signin signin = new Signin();
		signin.setId(id);
		signin.setPw(pass);
		signin.setName(name);
		signin.setBirth(birth);
		
		try {
			
			dao.insertMember(signin);
			hdao.insertHomePage(id);
			response.sendRedirect(request.getContextPath()+"/jsp/login/loginform");


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}