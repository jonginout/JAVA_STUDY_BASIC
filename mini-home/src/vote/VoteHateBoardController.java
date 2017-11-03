package vote;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import common.Page;
import common.PageResult;
import fileGallary.BoardDomain;
import fileGallary.BoardFileDomain;
import fileGallary.CommentDomain;
import fileGallary.GallaryDAO;
import login.Login;

@WebServlet("/jsp/vote/votehateboard")
public class VoteHateBoardController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String servletPath = "/jsp/filegallary/detailfilegallary"
				+ "?homepage_no="+request.getParameter("homepage_no")
				+ "&category_group_no="+request.getParameter("category_group_no")
				+ "&category_no="+request.getParameter("category_no")
				+ "&no="+request.getParameter("no")
				+ "&commentPageNo=";
		
		String [] subPath = {"", "/jsp/photoGallary/detailPhotoGallary.jsp", "", "", "/jsp/fileGallary/detailFileGallary.jsp"};
		
		int memberNo = -1;
		int no = -1;
		int commentPageNo = -1;
		int categoryGroupNo = -1;
		try {
			no = Integer.parseInt(request.getParameter("no"));
			memberNo = Integer.parseInt(request.getParameter("member_no"));
			categoryGroupNo = Integer.parseInt(request.getParameter("category_group_no"));

			try {
				commentPageNo = Integer.parseInt(request.getParameter("commentPageNo"));				
			} catch (NumberFormatException e) { }
			
			GallaryDAO dao = new GallaryDAO();
			BoardDomain board = dao.detailBoard(no);
			int listPerPage = 10;
			int pagePerBlock = 10;
			
			Page page = new Page(commentPageNo, listPerPage);
			
			List<BoardFileDomain> fileList = dao.listBoardFile(no);
			List<CommentDomain> commentList = dao.listComment(no, page);
			int commentCount = dao.listCommentCount(no);
			
			PageResult commentPageResult = new PageResult(commentPageNo, commentCount, pagePerBlock, servletPath);

			VoteDAO vDao = new VoteDAO();
			VoteDomain vote = new VoteDomain();
			
			vote.setNo(no);
			vote.setMemberNo(memberNo);
			vDao.insertHateVote(vote);
			
			int likeCheck = vDao.checkLikeVote(vote);
			int hateCheck = vDao.checkHateVote(vote);
			
			
			request.setAttribute("homepage_no", request.getParameter("homepage_no"));
			request.setAttribute("category_no", request.getParameter("category_no"));
			request.setAttribute("category_group_no", categoryGroupNo);
			request.setAttribute("commentPageResult", commentPageResult);
			request.setAttribute("commentNo", request.getParameter("commentNo"));
			request.setAttribute("commentPageNo", request.getParameter("commentPageNo"));			
			request.setAttribute("member_no", memberNo);
			request.setAttribute("board", board);
			request.setAttribute("commentList", commentList);
			request.setAttribute("fileList", fileList);
			request.setAttribute("likeCheck", likeCheck);
			request.setAttribute("hateCheck", hateCheck);
			request.setAttribute("likeCount", vDao.countLikeVote(vote));
			request.setAttribute("hateCount", vDao.countHateVote(vote));
			
			
			RequestDispatcher rd = request.getRequestDispatcher(
					subPath[categoryGroupNo]	
			);
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}
	
	
}
