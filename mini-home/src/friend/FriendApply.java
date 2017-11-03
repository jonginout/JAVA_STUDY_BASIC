package friend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;


@WebServlet("/jsp/friend/friendapply")
public class FriendApply extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		String homePageNo = request.getParameter("homepage_no");
		String memberId = request.getParameter("member_id");
		String friendMemberId = request.getParameter("friend_member_id");
		
		FriendDomain me = new FriendDomain();
		me.setMemberId(memberId);
		me.setFriendMemberId(friendMemberId);
		me.setStatus("WAIT");
		FriendDomain you = new FriendDomain();
		you.setMemberId(friendMemberId);
		you.setFriendMemberId(memberId);
		you.setStatus("SELECT");
		
		FriendDAO dao = new FriendDAO();
		
		try {
			
			;
			for (FriendDomain f : dao.listFriend(memberId)) {
				if(f.getFriendMemberId().equals(friendMemberId)) {
					//이미 친구인 경우
					String msg = "이미 친구이거나 혹은 친구수락 대기중 입니다.";
					AlertBack.alertAndBack(response, msg);
					return;
				}
			}
			
			dao.insertFriend(me);
			dao.insertFriend(you);
			response.sendRedirect(request.getContextPath()+"/jsp/homepage/homepage?homepage_no="+homePageNo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
