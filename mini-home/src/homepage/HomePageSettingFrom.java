package homepage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/jsp/homepage/homepagesettingform")
public class HomePageSettingFrom extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		int homepageNo = Integer.parseInt(request.getParameter("homepage_no")); 
		
		HomePageDAO dao = new HomePageDAO();
		
		
		
		try {
			
			HomePageDomain homePage = dao.detailHomePage(homepageNo);
			String profile = null;
			String profileExt = null;
			if(homePage.getProfile()!=null) {
				profile = homePage.getProfile().substring(homePage.getProfile().lastIndexOf("/")+1);	
				profileExt = profile.substring(profile.indexOf(".")+1);
			}
			String backgroundImg =null;
			String backgroundImgExt =null;
			if(homePage.getBackgroundImg()!=null) {
				backgroundImg = homePage.getBackgroundImg().substring(homePage.getBackgroundImg().lastIndexOf("/")+1);
				backgroundImgExt = backgroundImg.substring(backgroundImg.indexOf(".")+1);
			}
			String bgm = null;
			String bgmExt = null;
			if(homePage.getBgm()!=null) {
				bgm = homePage.getBgm().substring(homePage.getBgm().lastIndexOf("/")+1);
				bgmExt = bgm.substring(bgm.indexOf(".")+1);
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/homepage/homePageSettingForm.jsp");
			request.setAttribute("homePage", homePage);
			request.setAttribute("profile", profile);
			request.setAttribute("backgroundImg", backgroundImg);
			request.setAttribute("bgm", bgm);
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




