package co.kr.jongin.blog.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login/loginform.do")
	public void loginForm() {}

	@RequestMapping("/login/logout.do")
	public String logout(
			HttpServletRequest request,
			HttpServletResponse response
			) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/login/login.do")
	public ModelAndView login(
			LoginDomain login, String idcheck, 
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception {
		
		Cookie c = new Cookie("cid", login.getId());
		c.setMaxAge(0);
		if("Y".equals(idcheck)) {
			c.setMaxAge(60 * 60 * 24);
			request.setAttribute("cid", login.getId());
		}
		response.addCookie(c);
		
		LoginMapper loginDAO = new LoginMapper();
		login = loginDAO.login(login);
		
		ModelAndView mav = new ModelAndView();
		if(login!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", login);
			mav.setView("/jsp/main/main.jsp");
		}else {
			mav.setView("/jsp/login/loginform.jsp");
			mav.addAttribute("error", "입력한 정보가 올바르지 않습니다.");
		}
		return mav;
	}
	
}