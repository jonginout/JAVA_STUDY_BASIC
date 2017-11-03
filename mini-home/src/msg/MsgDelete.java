package msg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/msg/msgdelete")
public class MsgDelete extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		int msgNo = Integer.parseInt(request.getParameter("msg_no")); 
		String field = request.getParameter("field");
		
		MsgDAO dao = new MsgDAO();
		
		try {
			
			dao.deleteMsg(field ,msgNo);
			response.sendRedirect(request.getContextPath()+"/jsp/msg/msgbox?field=rec_id");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
