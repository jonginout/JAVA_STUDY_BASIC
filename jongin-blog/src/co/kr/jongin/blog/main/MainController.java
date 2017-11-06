package co.kr.jongin.blog.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;

import co.kr.jongin.blog.blog.BlogDomain;
import co.kr.jongin.blog.blog.BlogMapper;
import co.kr.jongin.blog.login.LoginDomain;

@Controller
public class MainController {
	
	@RequestMapping("/main/main.do")
	public ModelAndView main(
			HttpServletRequest request,
			BlogDomain blog
			) throws Exception {
		
		ModelAndView mav = new ModelAndView("/jsp/main/main.jsp");
		BlogMapper blogDAO = new BlogMapper();
		
		mav.addAttribute("blogList", blogDAO.blogList(blog));
		
		HttpSession session = request.getSession();
		LoginDomain user = (LoginDomain) session.getAttribute("user");
		if(user != null) {
			int chk = blogDAO.chkMyBlog(user.getMemberNo());
			if(chk>0) {
				mav.addAttribute("myBlog", true);
			}
		}
		return mav;
	}
	
}
