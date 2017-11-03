package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageResult;


@WebServlet("/board/list3.do")
public class ListBoardController3 extends HttpServlet {
	
	@Override
	protected void service( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int pageNo = 1;
		if(request.getParameter("pageNo")!=null)
		{
			pageNo = Integer.parseInt(request.getParameter("pageNo")); //현재페이지
			if(pageNo<1) {pageNo = 1;} // 현재 페이지가 총 페이지 수 보다 적을 경우 1 페이지로 초기화
		}
		
		try {
			int pageList = 10; // 페이지 당 리스트
			int blockPage = 10;	// 블록당 페이지 수
			
			BoardDAO dao = new BoardDAO();
			Page page = new Page(pageNo);
			List<BoardDomain> list = dao.listBoard(page);
			
			
			// 페이징을 위한 파트--------------------------
			
			//전체 게시물의 수
			int count = dao.listBoardCount(); 
			// 마지막 페이지 번호 구하기
			PageResult pageResult = new PageResult(pageNo, count);
			
			
			request.setAttribute("pageResult", pageResult);
			// -------------------------------------------
			
			
			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/board/list3.jsp");

			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}











