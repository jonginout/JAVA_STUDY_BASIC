package msg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.Login;


@WebServlet("/jsp/msg/msgdetail")
public class MsgDetail extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		int msgNo = Integer.parseInt(request.getParameter("msg_no")); 
		
		MsgDAO dao = new MsgDAO();
		
		try {
			
			MsgDomain msg = dao.detailMsg(msgNo);
			HttpSession session = request.getSession();
			Login user = (Login)session.getAttribute("user");
			if(user.getId().equals(msg.getRecId())) {				
				dao.readMsg(msgNo);
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/msg/msgDetail.jsp");
			request.setAttribute("msg", msg);
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
