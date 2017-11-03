package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/detail")
public class DetailBoardController extends HttpServlet {
	
	@Override
	protected void service( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// post 방식일 경우 파라미터 한글 처리
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no"));

		int coPage = 1;
		if(request.getParameter("co_page")!=null)
		{
			coPage = Integer.parseInt(request.getParameter("co_page")); //현재페이지
			if(coPage<1) {coPage = 1;} // 현재 페이지가 총 페이지 수 보다 적을 경우 1 페이지로 초기화
		}
		
		BoardDAO bb = new BoardDAO();
		try {
			
			int pageList = 5; // 페이지 당 리스트 수
			int blockPage = 5; // 블록당 페이지 수
			int listCount =  bb.listCommentCount(no); // 모든 게시글 수
			int pageCount = (int) Math.ceil(((double)listCount/(double)pageList)); // 총 페이지 수
			int blockCount = (int) Math.ceil(((double)pageCount/(double)blockPage)); // 총 블록 수
			if( coPage > pageCount ) { coPage = pageCount; } // 현재 페이지가 총 페이지 수 보다 많을 경우 총 페이지 수 로 초기화
			int block = (int) Math.ceil((double)coPage/(double)blockPage); // 현재 블록
			int firstPage = (block*blockPage)-(blockPage-1); // 현재 블록의 첫 페이지
			if( firstPage <= 1 ) { firstPage = 1; } // 블록의 첫 페이지가 1 이하면 1로 초기화
			int lastPage = (block*blockPage); // 현재 블록의 마지막 페이지
			if( listCount <= lastPage ) { lastPage = listCount; }
			if( lastPage > pageCount ) { lastPage = pageCount; }			
			int startPoint = ((coPage-1)*pageList)+1; // 현 페이지 에서 글을 가져오는 시작점
			int endPoint = startPoint+(pageList-1); // 현 페이지 에서 글을 시작점으로 부터 얼마나 가져올지
			// 값 설정 끝
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/detail.jsp");
			request.setAttribute("detail", bb.detailBoard(no));
			request.setAttribute("comment", bb.listComment(no, startPoint, endPoint) );
			request.setAttribute("coPage", coPage );
			request.setAttribute("listCount", listCount );
			request.setAttribute("pageCount", pageCount );
			request.setAttribute("firstPage", firstPage );
			request.setAttribute("lastPage", lastPage );


			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}











