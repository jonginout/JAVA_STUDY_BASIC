package msg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.AlertBack;
import login.Login;

@WebServlet("/jsp/msg/msgsend")
public class MsgSend extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Login user = (Login)session.getAttribute("user");
		
		String sendId = user.getId();
		String recId = request.getParameter("recId");
		String content = request.getParameter("content");
		
		System.out.println(sendId+recId+content);
		
		MsgDomain msg = new MsgDomain();
		msg.setSendId(sendId);
		msg.setRecId(recId);
		msg.setContent(content);
		
		MsgDAO dao = new MsgDAO();
		
		try {
			
			dao.insertMsg(msg);
			AlertBack.alertAndBack(response, "쪽지 전송완료!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}