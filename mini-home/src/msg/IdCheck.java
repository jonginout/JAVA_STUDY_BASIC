package msg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import signin.SigninDAO;

@WebServlet("/jsp/msg/idchk")
public class IdCheck extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String recId = request.getParameter("recId");
		String url = request.getParameter("url");
		
		
		SigninDAO dao = new SigninDAO();
		
		JsonObject jsonObject = new JsonObject();				 

		try {
			if (dao.idCheck(recId)) {
				
				jsonObject = new JsonObject();				 
				PrintWriter out = null;
				jsonObject.addProperty("success", "쪽지 보내기가 가능한 아이디 입니다.");
				response.setContentType("application/x-json; charset=UTF-8"); //HttpServletResponse response
				out = response.getWriter();
				out.print(jsonObject);
				out.close();
				
			}else {
				
				jsonObject = new JsonObject();				 
				PrintWriter out = null;
				jsonObject.addProperty("error", "존재하지 않는 아이디 입니다.");
				response.setContentType("application/x-json; charset=UTF-8"); //HttpServletResponse response
				out = response.getWriter();
				out.print(jsonObject);
				out.close();
				  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}

