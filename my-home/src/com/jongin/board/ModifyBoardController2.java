package com.jongin.board;
//package board;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import common.WriterCheck;
//import longin.Login;
//
//@WebServlet("/board/modify")
//public class ModifyBoardController2 extends HttpServlet{
//
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		// post 방식일 경우 파라미터 한글 처리
//		
//		
//		int no = Integer.parseInt(request.getParameter("no"));
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		String writer = request.getParameter("writer");
//		
//		//---------------------------------------------------
//		WriterCheck wc = new WriterCheck();
//		if(!wc.writerChk(request, writer)) {
//			response.sendRedirect(request.getContextPath()+"/board/detail?no="+no);
//			return;
//		}
//		//---------------------------------------------------
//		
//		BoardDomain board = new BoardDomain();
//		board.setNo(no);
//		board.setTitle(title);
//		board.setContent(content);
//		board.setWriter(writer);
//		
//		BoardDAO bb = new BoardDAO();
//		try {
//			
//			bb.modifyBoard(board);
//			response.sendRedirect(request.getContextPath()+"/board/detail?no="+no);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
