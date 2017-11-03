package com.jongin.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jongin.common.WriterCheck;


@WebServlet("/board/deletecomment")
public class DeleteCommentController extends HttpServlet {
	
	@Override
	protected void service( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// post 방식일 경우 파라미터 한글 처리
		
		
		int no = Integer.parseInt(request.getParameter("no"));
		int commentNo = Integer.parseInt(request.getParameter("comment_no"));
		
		BoardMapper bb = new BoardMapper();
		try {

			//---------------------------------------------------
			WriterCheck wc = new WriterCheck();
			if(!wc.writerChk(request, bb.detailComment(commentNo).getWriter())) {
				response.sendRedirect(request.getContextPath()+"/board/detail?no="+no);
				return;
			}
			//---------------------------------------------------
			
			bb.deleteComment(no, commentNo);
			response.sendRedirect(request.getContextPath()+"/board/detail?no="+no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}











