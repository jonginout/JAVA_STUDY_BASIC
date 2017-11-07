package co.kr.jongin.blog.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.RequestMapping;

import co.kr.jongin.blog.login.LoginDomain;

@Controller
public class BlogController {

	@RequestMapping("/blog/bloginsert.do")
	public String insert(
			HttpServletRequest request
			) throws Exception {
		BlogMapper blogDAO = new BlogMapper();
		BlogDomain blog = new BlogDomain();
		
		HttpSession session = request.getSession();
		LoginDomain user = (LoginDomain) session.getAttribute("user");
		
		blog.setMemberNo(user.getMemberNo());
		blog.setTitle(user.getId()+"님의 블로그");
		blogDAO.blogInsert(blog);
		
		return "redirect:/main/main.do";
	}
	
}

