package com.jongin.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jongin.login.Login;


@WebServlet("/board/detail")
public class DetailBoardController extends HttpServlet {
	
	@Override
	protected void service( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		// post 방식일 경우 파라미터 한글 처리
		
		int no = Integer.parseInt(request.getParameter("no"));

		int coPage = 1;
		if(request.getParameter("co_page")!=null)
		{
			coPage = Integer.parseInt(request.getParameter("co_page")); //현재페이지
			if(coPage<1) {coPage = 1;} // 현재 페이지가 총 페이지 수 보다 적을 경우 1 페이지로 초기화
		}
		
		BoardMapper bb = new BoardMapper();
		try {
			
			int coPageList = 5; // 페이지 당 리스트 수
			int coBlockPage = 5; // 블록당 페이지 수
			int coListCount =  bb.listCommentCount(no); // 모든 게시글 수
			int coPageCount = (int) Math.ceil(((double)coListCount/(double)coPageList)); // 총 페이지 수
			int coBlockCount = (int) Math.ceil(((double)coPageCount/(double)coBlockPage)); // 총 블록 수
			if( coPage > coPageCount ) { coPage = coPageCount; } // 현재 페이지가 총 페이지 수 보다 많을 경우 총 페이지 수 로 초기화
			int coBlock = (int) Math.ceil((double)coPage/(double)coBlockPage); // 현재 블록
			int firstCoPage = (coBlock*coBlockPage)-(coBlockPage-1); // 현재 블록의 첫 페이지
			if( firstCoPage <= 1 ) { firstCoPage = 1; } // 블록의 첫 페이지가 1 이하면 1로 초기화
			int lastCoPage = (coBlock*coBlockPage); // 현재 블록의 마지막 페이지
			if( coListCount <= lastCoPage ) { lastCoPage = coListCount; }
			if( lastCoPage > coPageCount ) { lastCoPage = coPageCount; }			
			int coStartPoint = ((coPage-1)*coPageList)+1; // 현 페이지 에서 글을 가져오는 시작점
			int coEndPoint = coStartPoint+(coPageList-1); // 현 페이지 에서 글을 시작점으로 부터 얼마나 가져올지
			// 값 설정 끝
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/detail.jsp");
			
			request.setAttribute("detail", bb.detailBoard(no));
			request.setAttribute("listFile", bb.listFile(no));
			request.setAttribute("comment", bb.listComment(no, coStartPoint, coEndPoint) );
			request.setAttribute("coPage", coPage );
			request.setAttribute("coListCount", coListCount );
			request.setAttribute("coPageCount", coPageCount );
			request.setAttribute("firstCoPage", firstCoPage );
			request.setAttribute("lastCoPage", lastCoPage );


			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}










