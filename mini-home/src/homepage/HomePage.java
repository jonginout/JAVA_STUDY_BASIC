package homepage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.AlertBack;
import friend.FriendDAO;
import login.Login;

@WebServlet("/jsp/homepage/homepage")
public class HomePage extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		int homepageNo = Integer.parseInt(request.getParameter("homepage_no")); 
		
		HomePageDAO dao = new HomePageDAO();
		FriendDAO fdao = new FriendDAO();
		
		try {
			
			
			HomePageDomain homePage = dao.detailHomePage(homepageNo);
			
			HttpSession session = request.getSession();
			Login user = (Login)session.getAttribute("user");
		
			
			if(homePage.getOpenRange().equals("PRIVATE")) {
				if(!user.getId().equals(homePage.getId())) {
					// 내홈피가 아닐경우
					AlertBack.alertAndBack(response, "나만보기 처리된 홈페이지 입니다.");
					return;
				}
			}
			if (homePage.getOpenRange().equals("FRIEND")) {
				if(
					!fdao.FriendCheck(homePage.getId() ,user.getId())
					&&
					!user.getId().equals(homePage.getId())
				  ) {
					//친구도 아니고 내 홈피도 아닐경우
					AlertBack.alertAndBack(response, "친구공개 처리된 홈페이지 입니다.");
					return;
				}
			}
			
			dao.visitsUpHomePage(homepageNo);
			
			String profileExt = null;
			if(homePage.getProfile()!=null) {
				String profile = homePage.getProfile().substring(homePage.getProfile().lastIndexOf("/")+1);	
				profileExt = profile.substring(profile.indexOf(".")+1);
			}
			String backgroundImgExt =null;
			if(homePage.getBackgroundImg()!=null) {
				String backgroundImg = homePage.getBackgroundImg().substring(homePage.getBackgroundImg().lastIndexOf("/")+1);
				backgroundImgExt = backgroundImg.substring(backgroundImg.indexOf(".")+1);
			}
			String bgmExt = null;
			if(homePage.getBgm()!=null) {
				String bgm = homePage.getBgm().substring(homePage.getBgm().lastIndexOf("/")+1);
				bgmExt = bgm.substring(bgm.indexOf(".")+1);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/homepage/homePage.jsp");
			request.setAttribute("homePage", homePage);
			request.setAttribute("profileExt", profileExt);
			request.setAttribute("backgroundImgExt", backgroundImgExt);
			request.setAttribute("bgmExt", bgmExt);
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
