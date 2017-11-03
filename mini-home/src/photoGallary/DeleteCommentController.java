package photoGallary;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;
import common.WriterSelect;
import fileGallary.CommentDomain;

@WebServlet("/jsp/photogallary/deletecomment")
public class DeleteCommentController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int homepageNo = Integer.parseInt(request.getParameter("homepage_no"));
		try {
			GallaryDAO dao = new GallaryDAO();
			
			if(request.getParameter("commentNo") != null) {
				commentNo = Integer.parseInt(request.getParameter("commentNo"));
				CommentDomain comment = dao.detailComment(commentNo);
				if(!WriterSelect.writerSel(request).equals(comment.getWriter()))
				{
					System.out.println(WriterSelect.writerSel(request) + ":" + comment.getWriter());
					AlertBack.alertAndBack(response, "작성자만 수정할 수 있습니다.");
					return;
				}
			}
			dao.deleteComment(commentNo);
			
			
			response.sendRedirect(
					request.getContextPath() + "/jsp/photogallary/detailphotogallary"
							+ "?homepage_no="+request.getParameter("homepage_no")
							+"&category_group_no="+request.getParameter("category_group_no")
							+"&category_no="+request.getParameter("category_no")
							+ "&no="+request.getParameter("no")
			);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}






