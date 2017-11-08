package co.kr.jongin.blog.blog;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
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
		return mav;
	}
	
	@RequestMapping("/blog/modifyblog.do")
	public String blogSetForm(
			BlogDomain blog, HttpServletRequest request
			) throws Exception {
		
		// request로 직접 해라 어쩔수 없당
		BlogMapper blogDAO = new BlogMapper();
		return "redirect:/blog/blog.do?blogNo="+blog.getBlogNo();
	}
	
}

//
//SimpleDateFormat sdf = new SimpleDateFormat(/* /회원명/게시판 */"/yyyy/MM/dd/HH");
//String subPath = sdf.format(new Date());
//String uploadPath = "C:/jongin/upload";
//File f = new File(uploadPath + subPath);
//if (!f.exists()) {
//	f.mkdirs();
//}
//
//MultipartRequest mRequest = new MultipartRequest(
//		request,	// request 자체를 넘긴다.
//		uploadPath+subPath,	// 어디에 저장할지 위치
//		1024 * 1024 * 100,	// 최대 사이즈 크기 (1024*1024 = 1메가)
//		"utf-8",	// 파라미터값 인코딩을 뭐로 하겠냐
//		new HanbitFileRenamePolicy()	// 기본제공 이름 중복 정책
//		//생성자만 호출해도 자동으로 rename 메소드 호출
//	);
//
//// 화면에서 넘어온 파라미터 추출하기
//String title = mRequest.getParameter("title");
//String content = mRequest.getParameter("content");
//String writer = mRequest.getParameter("writer");
//System.out.println(title);
//System.out.println(content);
//System.out.println(writer);
//
//List<FileDomain> fileList = new ArrayList<>();
//
//Enumeration<String> fNames = mRequest.getFileNames();
//while (fNames.hasMoreElements()) {
//	String fName = fNames.nextElement();
//	System.out.println("fName : " + fName);
//	File file = mRequest.getFile(fName);
//	if (file != null) {
//		long fileSize = file.length();
//		String oriName = mRequest.getOriginalFileName(fName);
//		String systemName = mRequest.getFilesystemName(fName);
//		System.out.println("사이즈 : " + fileSize);
//		System.out.println("원래이름 : " + oriName);
//		System.out.println("시스템이름 : " + systemName);
//		FileDomain fd = new FileDomain();
//		fd.setFilePath(subPath);
//		fd.setFileSize(fileSize);
//		fd.setOriName(oriName);
//		fd.setSystemName(systemName);
//		fileList.add(fd);
//	}
//}
//
//// Domain 클래스에 파라미터 담기
//BoardDomain board = new BoardDomain();
//board.setTitle(title);
//board.setContent(content);
//board.setWriter(writer);
//
//// DAO를 호출해서 작업처리 지시
//BoardMapper dao = new BoardMapper();
//int nextVal;
//try {
//	nextVal = dao.getBoardNoSequence();
//	dao.insertBoard(board, nextVal);
//	for (FileDomain file : fileList) {				
//		dao.insertFile(file, nextVal);
//	}
//} catch (Exception e) {
//	throw new ServletException(e);
//} 
//
//// 처리된 결과를 보여줄 화면 관련 처리
//// 게시물 등록이 성공하였음..
//response.sendRedirect(request.getContextPath()+"/board/detail?no="+nextVal);

