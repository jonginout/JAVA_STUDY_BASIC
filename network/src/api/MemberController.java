/**
 *  오픈 api
 *  url
 *  http://192.168.0.184:8080/lec04_network/api/member
 *  
 *  요청파라미터
 *  id(필수) String
 *  
 *  응답 
 *  {
 *  	"id": "admin",
 *      "pass": "12345",
 *      "name": "홍길동"
 *  }
 */
package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/api/member")
public class MemberController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		try {
			String id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			Member user = dao.detailMember(id);
			System.out.println(user.getName());
			
			out.print(new Gson().toJson(user));
		} catch (Exception e) {
			throw new ServletException (e);
		}
	}
}











