package msg;

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
import login.Login;

@WebServlet("/jsp/msg/msgbox")
public class MsgList extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		int pageNo = 1;
		if(request.getParameter("pageNo")!=null)
		{
			pageNo = Integer.parseInt(request.getParameter("pageNo")); //현재페이지
			if(pageNo<1) {pageNo = 1;} // 현재 페이지가 총 페이지 수 보다 적을 경우 1 페이지로 초기화
		}
		
		HttpSession session = request.getSession();
		Login user = (Login)session.getAttribute("user");
		String userId = user.getId();
		
		String field = "rec_id";
		if (request.getParameter("field")!=null) {
			field = request.getParameter("field");
		}
		
		Search search = new Search();
		search.setField(field);
		search.setWord(userId);
		
		MsgDAO dao = new MsgDAO();
		
		try {
			
			int listPerPage = 10; // 페이지 당 리스트 수 (1페이지에 나오는 게시글 수)
			int pagePerBlock = 10; // 블록 당 페이지 수  (1블록에 나오는 페이지 수)
			
			Page page = new Page(pageNo, listPerPage);
			List<MsgDomain> list = dao.listMsg(search,page);
			int count = dao.listMsgCount(search); 
			PageResult MsgpageResult = new PageResult(pageNo, count, pagePerBlock);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/msg/msgBox.jsp");
			request.setAttribute("msgList", list);
			request.setAttribute("count", count);
			request.setAttribute("MsgpageResult", MsgpageResult);
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
