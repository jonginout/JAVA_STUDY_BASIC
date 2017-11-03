package day05.longin;

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

@WebServlet("/day05/login/loginprocess")
public class LoginProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		 /*
			-로그인 처리
			1.사용자가 화면에서 입력한 파라미터 추출
			2.DB에 사용자가 입력한 정보가 있는지 체크
			3.DB에 사용자 정보가 존재한다면 세션에 사용자 정보를 등록
			4.메인 페이지로 이동
			
			//DB에 사용자 정보가 존재하지 않는다면 로그인 폼 페이지로
			  다시이동
			
		 */
		
		// id : admin, pass : 1111 이면 성공
		
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
				
			Login login = dao.detailMember(id, pass);
			if (login!=null) {
				login.setAccessTime(new Date());
				HttpSession session = request.getSession();
				session.setAttribute("user", login);
				response.sendRedirect(request.getContextPath()+"/day05/login/main");
			}else {
				
				// 일부로 에러를 보낸다
				request.setAttribute("error", "입력한 정보가 올바르지 않습니다.");
				
				RequestDispatcher rd = request.getRequestDispatcher("/day05/login/loginForm.jsp");
				rd.forward(request, response);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
//			HttpSession session = request.getSession();
//			session.setAttribute("user", login);
			
			// 메인페이지 이동하기
			// 세션이기 때문에 모든 페이지에서 사용가능하고
			// 그렇기 때문에 센드 리다이렉트 가능

			// ${pageContext.request.contextPath}
			// pageContext.getRequest.getContextPath()
			// pageContext.getRequest 는 HttpServletRequest request 와 같다
			
			//response.sendRedirect(request.getContextPath()+"/day05/login/main");
			

			// 로그인 폼으로 이동하기
//			RequestDispatcher rd = request.getRequestDispatcher("/day05/login/loginForm.jsp");
//			rd.forward(request, response);

	}
	
	
	
}
