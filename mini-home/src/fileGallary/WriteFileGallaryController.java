package fileGallary;

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

import common.AlertBack;
import common.HanbitFileRenamePolicy;
import common.WriterCheck;

@WebServlet("/jsp/fileGallary/writefilegallary")
public class WriteFileGallaryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String uploadPath = "c:/mini-home/upload";
			String dir = "/board/fileGallary";
			SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/hh");
			//게시판 별로 구분할 것
			String subPath = dir+sdf.format(new Date());
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
			int homepageNo = -1;
			int	categoryNo = -1;
			// 화면에서 넘어온 파라미터 추출하기
			// homepageNo는 파라미터로 받는다.
			try {
				homepageNo = Integer.parseInt(mRequest.getParameter("homepage_no"));
				categoryNo = Integer.parseInt(mRequest.getParameter("category_no"));
				
			} catch (NumberFormatException e) {	}
			
			
			
			String title = mRequest.getParameter("title");
			String writer = mRequest.getParameter("writer");
			String content = mRequest.getParameter("content");
			
			Enumeration<String> fNames = mRequest.getFileNames();
			
			// Domain 클래스에 파라미터 담기
			BoardDomain board = new BoardDomain();
			GallaryDAO dao = new GallaryDAO();
			int no = dao.detailBoardNo();
			board.setHomepageNo(homepageNo);
			board.setCategoryNo(categoryNo);
			board.setTitle(title);
			board.setContent(content);
			board.setWriter(writer);
			board.setNo(no);
			
			dao.insertBoard(board);
			
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
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		
		request.setAttribute("category_group_no", request.getParameter("category_group_no"));
		
		RequestDispatcher rd = 
				request.getRequestDispatcher(
						"/jsp/filegallary/listfilegallary"
				);
		rd.forward(request, response);
		
		// 페이지 이동
//		response.sendRedirect(request.getContextPath() + "/jsp/filegallary/listfilegallary?homepageNo="+request.getParameter("homepageNo")
//		+"&categoryNo="+request.getParameter("categoryNo"));
	}
}










