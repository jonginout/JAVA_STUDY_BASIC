package day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day02/test06")
public class test06 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		//post 파라미터의 한글 인코딩
		
		String msg = request.getParameter("msg");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		//출력이니깐 write
		
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("	<body>");
		sb.append("		<h1>");
		sb.append(			msg);
		sb.append("		</h1>");
		sb.append("	</body>");
		sb.append("</html>");
		
		out.write(sb.toString());
		out.close();
		
	}

}
