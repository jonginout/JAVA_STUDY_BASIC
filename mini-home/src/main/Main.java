package main;

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
import common.Search;
import friend.FriendDAO;
import friend.FriendDomain;
import homepage.HomePageDAO;
import homepage.HomePageDomain;
import login.Login;

@WebServlet("/jsp/main/main")
public class Main extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		int pageNo = 1;
		if(request.getParameter("pageNo")!=null)
		{
			pageNo = Integer.parseInt(request.getParameter("pageNo")); //현재페이지
			if(pageNo<1) {pageNo = 1;} // 현재 페이지가 총 페이지 수 보다 적을 경우 1 페이지로 초기화
		}

		int friendPageNo = 1;
		if(request.getParameter("friendPageNo")!=null)
		{
			friendPageNo = Integer.parseInt(request.getParameter("friendPageNo")); //현재페이지
			if(friendPageNo<1) {friendPageNo = 1;} // 현재 페이지가 총 페이지 수 보다 적을 경우 1 페이지로 초기화
		}
		
		
		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("user");
		String memberId = "#";
		if(login!=null) {			
			memberId = login.getId();
		}
		String word = "";
		if(request.getParameter("word")!=null) {
			word = request.getParameter("word");
		}
		String field = "title";
		if(request.getParameter("field")!=null) {
			field = request.getParameter("field");
			if(field.equals("")) {
				field = "title";
			}
		}

		Search search = new Search();
		search.setField(field);
		search.setWord(word);
		
		HomePageDAO hdao = new HomePageDAO();
		FriendDAO fdao = new FriendDAO();

		try {
			
			int listPerPage = 5; // 페이지 당 리스트 수 (1페이지에 나오는 게시글 수)
			int pagePerBlock = 5; // 블록 당 페이지 수  (1블록에 나오는 페이지 수)
			
			Page page = new Page(pageNo, listPerPage);
			List<HomePageDomain> list = hdao.searchListHomePage(page, search);
			int count = hdao.listHomePageCount(search); 
			PageResult pageResult = new PageResult(pageNo, count, pagePerBlock);
			
			
			Page pageFriend = new Page(friendPageNo, listPerPage);
			List<FriendDomain> friendList = fdao.listFriend(pageFriend, memberId);
			int friendCount = fdao.listFriendCount(memberId); 
			PageResult friendPageResult = new PageResult(friendPageNo, friendCount, pagePerBlock);
			
	
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/main/main.jsp");
			
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("friendPageNo", friendPageNo);
			
			request.setAttribute("pageResult", pageResult);
			request.setAttribute("homePageList", list);

			request.setAttribute("friendPageResult", friendPageResult);
			request.setAttribute("friendList", friendList);
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

