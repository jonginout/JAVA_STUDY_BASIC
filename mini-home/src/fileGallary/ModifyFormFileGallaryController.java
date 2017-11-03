package fileGallary;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;

@WebServlet("/jsp/filegallary/modifyformfilegallary")
public class ModifyFormFileGallaryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			int no = 1;
			int homepageNo = Integer.parseInt(request.getParameter("homepage_no"));
			
			try {
				no = Integer.parseInt(request.getParameter("no"));
			} catch (NumberFormatException e) {	}
			
			if(WriterCheck.writerChk(request, homepageNo)==false) {
				AlertBack.alertAndBack(response, "작성자만 수정 가능합니다");
				return;
			}
			
			GallaryDAO dao = new GallaryDAO();
			BoardDomain board = dao.detailBoard(no);
			List<BoardFileDomain> fileList = dao.listBoardFile(no);
			int fileListSize = fileList.size();
			
			request.setAttribute("fileListSize", fileListSize);
			request.setAttribute("fileList", fileList);
			request.setAttribute("board", board);
			request.setAttribute("homepage_no", request.getParameter("homepage_no"));
			request.setAttribute("category_no", request.getParameter("category_no"));
			request.setAttribute("category_group_no", request.getParameter("category_group_no"));
			
			RequestDispatcher rd = request.getRequestDispatcher(
				"/jsp/fileGallary/modifyFileGallary.jsp"	
			);
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}






