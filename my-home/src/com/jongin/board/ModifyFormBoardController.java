package com.jongin.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/modifyform")
public class ModifyFormBoardController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post 방식일 경우 파라미터 한글 처리
		
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardMapper dao = new BoardMapper();
		try {
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/modifyForm.jsp");
			request.setAttribute("detail", dao.detailBoard(no));
			request.setAttribute("listFile", dao.listFile(no));
			request.setAttribute("listFileSize", dao.listFile(no).size());

			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
