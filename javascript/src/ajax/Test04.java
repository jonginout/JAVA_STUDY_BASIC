package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/08_ajax/test04")
public class Test04 extends HttpServlet{

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
			String msg = request.getParameter("msg");
		
			System.out.println("서블릿");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<h1> GET : "+msg+"</h1>");
			out.close();
		
	}
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String msg = request.getParameter("msg");
		
		System.out.println("서블릿");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1> POST : "+msg+"</h1>");
		out.close();
		
	}
}
