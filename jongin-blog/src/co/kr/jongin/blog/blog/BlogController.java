package co.kr.jongin.blog.blog;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;

import com.oreilly.servlet.MultipartRequest;

import co.kr.jongin.blog.common.HanbitFileRenamePolicy;
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
	
	@RequestMapping("/blog/blog.do")
	public ModelAndView blog(int blogNo) throws Exception {
		ModelAndView mav = new ModelAndView("/jsp/blog/blog.jsp");
		BlogMapper blogDAO = new BlogMapper();
		mav.addAttribute("blog", blogDAO.blogDetail(blogNo));
		return mav;
	}
	
	@RequestMapping("/blog/myblog.do")
	public String myBlog(int memberNo) throws Exception {
		BlogMapper blogDAO = new BlogMapper();
		BlogDomain blog = blogDAO.myBlogDetail(memberNo);
		return "redirect:/blog/blog.do?blogNo="+blog.getBlogNo();
	}

	@RequestMapping("/blog/blogsetform.do")
	public ModelAndView blogSetForm(int blogNo) throws Exception {
		ModelAndView mav = new ModelAndView("/jsp/blog/blogsetform.jsp");
		BlogMapper blogDAO = new BlogMapper();
		mav.addAttribute("blog", blogDAO.blogDetail(blogNo));
		mav.addAttribute("logo", blogDAO.logoDetail(blogNo));
		return mav;
	}
	
	@RequestMapping("/blog/modifyblog.do")
	public String modify(
			BlogDomain blog, HttpServletRequest request
			) throws Exception {
		
		String dir = "/blog/logo/"+blog.getBlogNo();
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH");
		String subPath = dir+sdf.format(new Date());
		String uploadPath = "C:/jongin/upload";
		File f = new File(uploadPath + subPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		MultipartRequest mRequest = new MultipartRequest(
			request,	// request 자체를 넘긴다.
			uploadPath+subPath,	// 어디에 저장할지 위치
			1024 * 1024 * 100,	// 최대 사이즈 크기 (1024*1024 = 1메가)
			"utf-8",	// 파라미터값 인코딩을 뭐로 하겠냐
			new HanbitFileRenamePolicy()	// 기본제공 이름 중복 정책
			//생성자만 호출해도 자동으로 rename 메소드 호출
		);
	
		blog.setTitle(mRequest.getParameter("title"));
		blog.setTag(mRequest.getParameter("tag"));
		blog.setShowNum(Integer.parseInt(mRequest.getParameter("showNum")));
		
		List<LogoDomain> logoList = new ArrayList<>();
		Enumeration<String> fNames = mRequest.getFileNames();
		while (fNames.hasMoreElements()) {
			String fName = fNames.nextElement();
			System.out.println("fName : " + fName);
			File file = mRequest.getFile(fName);
			if (file != null) {
				long fileSize = file.length();
				String oriName = mRequest.getOriginalFileName(fName);
				String systemName = mRequest.getFilesystemName(fName);
				System.out.println("사이즈 : " + fileSize);
				System.out.println("원래이름 : " + oriName);
				System.out.println("시스템이름 : " + systemName);
				LogoDomain ld = new LogoDomain();
				ld.setBlogNo(blog.getBlogNo());
				ld.setFilePath(subPath);
				ld.setFileSize(fileSize);
				ld.setOriName(oriName);
				ld.setSystemName(systemName);
				logoList.add(ld);
			}
		}
		
		System.out.println(blog.toString());
		
		BlogMapper blogDAO = new BlogMapper();
		
		blogDAO.blogModify(blog);
		for (LogoDomain logo : logoList) {				
			System.out.println(logo.toString());
			blogDAO.logoInsert(logo);
		}
		
		return "redirect:/blog/blogsetform.do?blogNo="+blog.getBlogNo();
	}
	
	@RequestMapping("/blog/deletelogo.do")
	public String logoDelete(int blogNo) throws Exception {
		BlogMapper blogDAO = new BlogMapper();
		blogDAO.logoDelete(blogNo);
		return "redirect:/blog/blogsetform.do?blogNo="+blogNo;
	}
	
}

