package friend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jsp/friend/friendactive")
public class FriendActive extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		String memberId = request.getParameter("member_id");
		String friendMemberId = request.getParameter("friend_member_id");
		String status = "active";
		
		FriendDomain me = new FriendDomain();
		me.setMemberId(memberId);
		me.setFriendMemberId(friendMemberId);
		me.setStatus("ACTIVE");
		FriendDomain you = new FriendDomain();
		you.setMemberId(friendMemberId);
		you.setFriendMemberId(memberId);
		you.setStatus("ACTIVE");
		
		FriendDAO dao = new FriendDAO();
		
		try {
			
			dao.modifyFriend(me);
			dao.modifyFriend(you);
			response.sendRedirect(request.getContextPath()+"/jsp/main/main");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
