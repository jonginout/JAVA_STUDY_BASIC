package crossdomain;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/crossdomain/test03")
public class Test03 extends HttpServlet{

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException {
			
			System.out.println("서블릿");
			response.setContentType("text/html; charset=utf-8");
			
			/// origin 문제 해결
			response.setHeader(
					"Access-Control-Allow-Origin", "http://localhost:9080"
					);
			
			
			PrintWriter out = response.getWriter();
			out.println("<h1>Ajax 응답결과.. hello~</h1>");
			out.close();
		
	}
	
}
