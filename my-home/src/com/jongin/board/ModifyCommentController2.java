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
//
//import org.springframework.web.WebUtil;
//
//import common.WriterCheck;
//
//@WebServlet("/board/commentmodify")
//public class ModifyCommentController extends HttpServlet{
//
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// post 방식일 경우 파라미터 한글 처리
//		
//
//		
//		BoardDAO bb = new BoardDAO();
//		try {
//			
//			CommentDomain comment = (CommentDomain)WebUtil.toVO(
//					request, BoardDomain.class
//			);
//			bb.modifyComment(comment);
//			response.sendRedirect(request.getContextPath()+"/board/detail?no="+comment.getNo());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
