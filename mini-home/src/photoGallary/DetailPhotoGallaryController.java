package photoGallary;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.AlertBack;
import common.Page;
import common.PageResult;
import common.WriterCheck;
import common.WriterSelect;
import fileGallary.CommentDomain;
import login.Login;
import vote.VoteDAO;
import vote.VoteDomain;


@WebServlet("/jsp/photogallary/detailphotogallary")
public class DetailPhotoGallaryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletPath = "/jsp/photogallary/detailphotogallary"
				+ "?homepage_no="+request.getParameter("homepage_no")
				+ "&category_group_no="+request.getParameter("category_group_no")
				+ "&category_no="+request.getParameter("category_no")
				+ "&no="+request.getParameter("no")
				+ "&commentPageNo=";
		
//		if(WriterCheck.writerChk(request, request.getParameter("writer"))==false) {
//			AlertBack.alertAndBack(response, "작성자만 수정 가능합니다");
//			return;
//		}
		
		int no = Integer.parseInt(request.getParameter("no"));
		int commentPageNo = 1;
		int memberNo = 1;
		try {
			try {
				commentPageNo = Integer.parseInt(request.getParameter("commentPageNo"));
			} catch (NumberFormatException e) {	}
			try {
				memberNo = Integer.parseInt(request.getParameter("member_no"));
			} catch (NumberFormatException e) {	}

			
			GallaryDAO dao = new GallaryDAO();
			BoardDomain board = dao.detailBoard(no);
			
			if(request.getParameter("commentNo") != null) {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				CommentDomain comment = dao.detailComment(commentNo);
				if(!WriterSelect.writerSel(request).equals(comment.getWriter()))
				{
					System.out.println(WriterSelect.writerSel(request) + ":" + comment.getWriter());
					AlertBack.alertAndBack(response, "작성자만 수정할 수 있습니다.");
					return;
				}
			}
			
			//파일 목록
			List<BoardFileDomain> fileList = dao.listBoardFile(no);
			//댓글 페이징
			
			int listPerPage = 10;
			int pagePerBlock = 10;
			
			Page page = new Page(commentPageNo, listPerPage);
			int commentCount = dao.listCommentCount(no);
			PageResult commentPageResult = new PageResult(commentPageNo, commentCount, pagePerBlock, servletPath);
			//댓글 목록
			List<CommentDomain> commentList = dao.listComment(no, page);

			// 좋아요 싫어요 --------------------------------------
			VoteDAO vDao = new VoteDAO();
			VoteDomain vote = new VoteDomain();

			vote.setNo(no);
			vote.setMemberNo(memberNo);
			int likeCheck = vDao.checkLikeVote(vote);
			int hateCheck = vDao.checkHateVote(vote);

			
			try {
				likeCheck = Integer.parseInt(request.getParameter("likeCheck"));
				hateCheck = Integer.parseInt(request.getParameter("hateCheck"));
			} catch (NumberFormatException e) {	}
						
			// ------------------------------------------------
			request.setAttribute("writer", WriterSelect.writerSel(request));

			request.setAttribute("homepage_no", request.getParameter("homepage_no"));
			request.setAttribute("category_no", request.getParameter("category_no"));
			request.setAttribute("category_group_no", request.getParameter("category_group_no"));
			request.setAttribute("commentPageResult", commentPageResult);
			request.setAttribute("commentNo", request.getParameter("commentNo"));
			request.setAttribute("commentPageNo", request.getParameter("commentPageNo"));			
			request.setAttribute("member_no", memberNo);
			request.setAttribute("commentList", commentList);
			request.setAttribute("fileList", fileList);
			request.setAttribute("board", board);
			request.setAttribute("likeCount", vDao.countLikeVote(vote));
			request.setAttribute("hateCount", vDao.countHateVote(vote));
			request.setAttribute("likeCheck", likeCheck);
			request.setAttribute("hateCheck", hateCheck);
			
			System.out.println(likeCheck);
			
			
			RequestDispatcher rd = request.getRequestDispatcher(
				"/jsp/photoGallary/detailPhotoGallary.jsp"	
			);
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}






