package fileGallary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;

@WebServlet("/jsp/filegallary/writecomment")
public class WriteComentController extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// post 방식일 경우 파라미터 한글 처리
		request.setCharacterEncoding("utf-8");
		
			
		// Domain 클래스에 파라미터 담기
		CommentDomain comment = new CommentDomain();
		
		int no = Integer.parseInt(request.getParameter("no"));
		comment.setContent(request.getParameter("content"));
		comment.setWriter(request.getParameter("writer"));
		comment.setNo(no);
		
		// DAO를 호출해서 작업처리 지시
		GallaryDAO dao = new GallaryDAO();
		
		try {
			//댓글 수정시 input창으로 전환을 위한 파라미터
			if(request.getParameter("modifyComment") != null) {
				comment.setCommentNo(Integer.parseInt(request.getParameter("commentNo")));
				comment.setContent(request.getParameter("modifyComment"));
				dao.modifyComment(comment);
			} else {
				dao.insertComment(comment);
			}

			response.sendRedirect(request.getContextPath() + "/jsp/filegallary/detailfilegallary"
					+ "?homepage_no="+request.getParameter("homepage_no")
					+"&category_group_no="+request.getParameter("category_group_no")
					+"&category_no="+request.getParameter("category_no")
					+ "&no="+request.getParameter("no")
					+ "&member_no="+request.getParameter("member_no"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}










