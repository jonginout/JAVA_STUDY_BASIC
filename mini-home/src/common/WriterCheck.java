package common;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import homepage.HomePageDAO;
import homepage.HomePageDomain;
import login.Login;


//작성자와 세션아이디와 같는지 체크
// true = 작성자와 세션아이디가 같다
// false = 작성자와 세션아이디가 다르다
public class WriterCheck {

	public static boolean writerChk(
			HttpServletRequest request, int homepage_no
			) throws IOException {
		
		boolean chk = true;
		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("user");
		
		HomePageDomain home = null;
		
		try {
			home = new HomePageDAO().detailHomePage(homepage_no);
		} catch (Exception e) {	}
				
		if(!login.getId().equals(home.getId())) {
			chk = false;
		}
		
		System.out.println(login.getId()+":"+ home.getId());
		return chk;
		
	}
	
}
