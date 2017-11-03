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

@WebServlet("/jsp/signin/idchk")
public class IdCheck extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		SigninDAO dao = new SigninDAO();
		
		try {
			if (dao.idCheck(id)) {
				request.setAttribute("error", "이미 존재하는 아이디 입니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/signin/signinForm.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("id", id);
				request.setAttribute("idChk", true);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/signin/signinForm.jsp");
				rd.forward(request, response);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}