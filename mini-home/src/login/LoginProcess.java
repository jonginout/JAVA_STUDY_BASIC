package login;

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

@WebServlet("/jsp/login/loginprocess")
public class LoginProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		//MEMBER_NO,ID,pw,name,birth
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String idcheck = request.getParameter("idcheck");
		
		Cookie c = new Cookie("cid", id);
		// 쿠키 삭제
		c.setMaxAge(0); //쿠키 즉시 삭제 -1은 브라우져 꺼져있음 삭제
		if ("Y".equals(idcheck)) {
			c.setMaxAge(60 * 60 * 24);
			request.setAttribute("cid", id);
		}
		response.addCookie(c);
		
		LoginDAO dao = new LoginDAO();
		try {
				
			Login login = dao.loginMember(id, pass);
			if (login!=null) {
				login.setAccessTime(new Date());
				HttpSession session = request.getSession();
				session.setAttribute("user", login);
				response.sendRedirect(request.getContextPath()+"/jsp/main/main");
			}else {
				
				// 일부로 에러를 보낸다
				request.setAttribute("error", "입력한 정보가 올바르지 않습니다.");
				
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login/loginForm.jsp");
				rd.forward(request, response);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
