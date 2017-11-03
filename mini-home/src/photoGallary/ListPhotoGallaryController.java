package photoGallary;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Page;
import common.PageResult;
import login.Login;
import vote.VoteDomain;


@WebServlet("/jsp/photogallary/listphotogallary")
public class ListPhotoGallaryController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 작업을 처리하기 위한 파라미터 추출
		
		try {
			// 데이터를 가져오기
			String category_no = request.getParameter("category_no");
			String homepage_no = request.getParameter("homepage_no");
			String category_group_no = request.getParameter("category_group_no");
			
			int categoryNo = Integer.parseInt(category_no);
			int homepageNo = Integer.parseInt(homepage_no);
			int pageNo = 1;
			int memberNo = -1;
			
			try {
				pageNo = Integer.parseInt(request.getParameter("page_no"));				
			} catch (NumberFormatException e) {	}
				

			String servletPath = "/jsp/photogallary/listphotogallary"
					+ "?homepage_no="+request.getParameter("homepage_no")
					+ "&category_group_no="+request.getParameter("category_group_no")
					+"&category_no="+request.getParameter("category_no")
					+"&page_no=";
			
			int listPerPage = 10;
			int pagePerBlock = 10;
			
			Page page = new Page(pageNo, listPerPage);
			GallaryDAO dao = new GallaryDAO();
			
			List<BoardDomain> list = dao.listBoard(homepageNo, categoryNo, page);
			int count = dao.listBoardCount(homepageNo, categoryNo);
			
			PageResult pageResult = new PageResult(pageNo, count, pagePerBlock, servletPath);

			HttpSession session = request.getSession();
			Login user = (Login)session.getAttribute("user");
			

			memberNo = user.getMemberNo();
				
			// 데이터를 사용할 페이지로 이동
			request.setAttribute("homepage_no", homepage_no);
			request.setAttribute("category_no", category_no);
			request.setAttribute("category_group_no", category_group_no);
			request.setAttribute("pageResult", pageResult);
			request.setAttribute("list", list);
			request.setAttribute("member_no", memberNo);
			
			RequestDispatcher rd = 
					request.getRequestDispatcher(
							"/jsp/photoGallary/listPhotoGallary.jsp"
					);
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	
}







