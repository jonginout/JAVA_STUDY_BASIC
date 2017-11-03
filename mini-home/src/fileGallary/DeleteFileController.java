package fileGallary;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/filegallary/deletefile")
public class DeleteFileController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 지울 파일 번호
		int fileNo = Integer.parseInt(request.getParameter("fileNo"));
		
		try {
			GallaryDAO dao = new GallaryDAO();
			dao.deleteFile(fileNo);
			
			request.setAttribute("homepage_no", request.getParameter("homepage_no"));
			request.setAttribute("category_no", request.getParameter("category_no"));
			request.setAttribute("category_group_no", request.getParameter("category_group_no"));
			request.setAttribute("commentNo", request.getParameter("commentNo"));
			
			RequestDispatcher rd = request.getRequestDispatcher(
					"/jsp/filegallary/modifyformfilegallary?no=" + request.getParameter("no")
					);
			
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}






