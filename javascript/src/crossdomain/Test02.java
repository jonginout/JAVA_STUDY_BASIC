package crossdomain;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/crossdomain/test02")
public class Test02 extends HttpServlet{

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException {
			
			System.out.println("서블릿");
			response.setContentType("text/html; charset=utf-8");
			
			String u = "http://localhost:9080/apiserver/crossdomain/test02";
			URL url = new URL(u);
			
			InputStream is = url.openStream(); //
			StringBuffer sb = new StringBuffer();
			Scanner sc = new Scanner(is);
			
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine());
			};
			
			// 자기가 타 서버를 호출한다.
			
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			out.close();
		
	}
	
}
