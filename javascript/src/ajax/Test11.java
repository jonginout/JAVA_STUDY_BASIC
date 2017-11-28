/*package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax/test11")
public class Test11 extends HttpServlet {
	@Override
	public void service(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		String id = request.getParameter("id");
		QuizMapper mapper = new QuizMapper();
		int count = mapper.selectMemberCountByPK(id);
		PrintWriter out = response.getWriter();
		out.write("{\"result\": " + count + "}");
		out.close();
	}
}
*/