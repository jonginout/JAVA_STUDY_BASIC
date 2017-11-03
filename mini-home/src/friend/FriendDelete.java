package friend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/friend/frienddelete")
public class FriendDelete extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		String memberId = request.getParameter("member_id");
		String friendMemberId = request.getParameter("friend_member_id");
		
		FriendDAO dao = new FriendDAO();
		
		try {
			
			dao.deleteFriend(memberId, friendMemberId);
			dao.deleteFriend(friendMemberId, memberId);
			response.sendRedirect(request.getContextPath()+"/jsp/main/main");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
