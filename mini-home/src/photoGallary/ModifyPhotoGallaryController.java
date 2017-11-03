package photoGallary;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.HanbitFileRenamePolicy;
import vote.VoteDAO;
import vote.VoteDomain;

@WebServlet("/jsp/photogallary/modifyphotogallary")
public class ModifyPhotoGallaryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String uploadPath = "c:/mini-home/upload";
			String dir = "/board/PhotoGallary";
			SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/hh");
			//게시판 별로 구분할 것
			String subPath = sdf.format(new Date());
			File f = new File(uploadPath+subPath);
			
			if(!f.exists()) {
				f.mkdirs();
			}
			
			MultipartRequest mRequest = new MultipartRequest(
					request, 
					uploadPath+subPath, 
					1024 * 1024 * 100, 
					"utf-8", 
					new HanbitFileRenamePolicy()
			);
			
			// 화면에서 넘어온 파라미터 추출하기
			// homepageNo는 파라미터로 받는다.
			
			int homepageNo = -1;
			int categoryNo = -1; // 1. 사진갤러리, 2. 방명록, 3. 다이어리, 4. 파일갤러리
			int no = -1;
			int commentPageNo = 1;
			int memberNo= -1;

			
			try {
				homepageNo = Integer.parseInt(request.getParameter("homepage_no"));
				categoryNo = Integer.parseInt(request.getParameter("category_no"));
				no = Integer.parseInt(request.getParameter("no"));
				memberNo = Integer.parseInt(request.getParameter("member_no"));
			} catch (NumberFormatException e) {	}
			Enumeration<String> fNames = mRequest.getFileNames();
			
			// Domain 클래스에 파라미터 담기
			BoardDomain board = new BoardDomain();
			GallaryDAO dao = new GallaryDAO();

			board.setHomepageNo(homepageNo);
			board.setCategoryNo(categoryNo);
			board.setTitle(mRequest.getParameter("title"));
			board.setContent(mRequest.getParameter("content"));
			board.setWriter(mRequest.getParameter("writer"));
			board.setNo(no);
			
			dao.modifyBoard(board);

			
			VoteDAO vDao = new VoteDAO();
			VoteDomain vote = new VoteDomain();

			vote.setNo(no);
			vote.setMemberNo(memberNo);
			int likeCheck = vDao.checkLikeVote(vote);
			int hateCheck = vDao.checkHateVote(vote);
				
			while(fNames.hasMoreElements()) {
				String fName = fNames.nextElement();
				File file = mRequest.getFile(fName);
				
				if(file != null) {
					
					BoardFileDomain boardFile = new BoardFileDomain();
					boardFile.setNo(no);
					
					boardFile.setFilePath(subPath);
					boardFile.setOriName(mRequest.getOriginalFileName(fName));
					boardFile.setSystemName(mRequest.getFilesystemName(fName));
					boardFile.setFileSize(file.length());
					
					dao.insertFile(boardFile, boardFile.getNo());
				}
			}			
			
			request.setAttribute("homepage_no", request.getParameter("homepage_no"));
			request.setAttribute("category_no", request.getParameter("category_no"));
			request.setAttribute("category_group_no", request.getParameter("category_group_no"));
			request.setAttribute("commentNo", request.getParameter("commentNo"));
			request.setAttribute("commentPageNo", commentPageNo);			
			request.setAttribute("member_no", memberNo);
			request.setAttribute("likeCheck", likeCheck);
			request.setAttribute("hateCheck", hateCheck);
			
			// 페이지 이동
			//response.sendRedirect(request.getContextPath() + "/jsp/photogallary/detailPhotoGallary?no=" + request.getParameter("no"));
			RequestDispatcher rd = request.getRequestDispatcher(
					"/jsp/photogallary/detailphotogallary?"
				);
			
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		
		
		
	}
}










