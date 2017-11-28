package jquery.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jquery/ajax/test02")
public class Test02 extends HttpServlet{
	
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException {
			
			System.out.println("서블릿");
			response.setContentType("text/html; charset=utf-8");
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PrintWriter out = response.getWriter();
			out.println("<h1>Ajax 응답결과.. hello~</h1>");
			out.close();
		
	}
	
//	@Override
//	protected void service( //get 메소드만 처리
//			HttpServletRequest request, HttpServletResponse response
//			)throws ServletException, IOException {
//		
//		response.setContentType("application/json; charset=utf-8");
//		System.out.println("{ \"result\" : \"test\"}");
//		PrintWriter out = response.getWriter();
//		out.close();
//		
//	}
//
//	@Override
//	protected void doGet( //get 메소드만 처리
//			HttpServletRequest request, HttpServletResponse response
//			)throws ServletException, IOException {
//			response.setContentType("text/html; charset=utf-8");
//			
//			String msg = request.getParameter("msg");
//		
//			System.out.println("서블릿");
//			PrintWriter out = response.getWriter();
//			out.println("<h1> get : "+msg+"</h1>");
//			out.close();
//		
//	}
//	
//	@Override
//	protected void doPost( //get 메소드만 처리
//			HttpServletRequest request, HttpServletResponse response
//			)throws ServletException, IOException {
//		response.setContentType("text/html; charset=utf-8");
//		
//		String msg = request.getParameter("msg");
//		
//		System.out.println("서블릿");
//		PrintWriter out = response.getWriter();
//		out.println("<h1> post : "+msg+"</h1>");
//		out.close();
//		
//	}
	
}
