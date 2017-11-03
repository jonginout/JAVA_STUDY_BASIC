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

import common.CategoryDomain;
import common.HanbitFileRenamePolicy;

@WebServlet("/jsp/filegallary/modifyfilegallarycategory")
public class ModifyFileGallaryCategoryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// 화면에서 넘어온 파라미터 추출하기
			// homepageNo는 파라미터로 받는다.
			
			int homepageNo = -1;
			int categoryNo = -1; // 1. 사진갤러리, 2. 방명록, 3. 다이어리, 4. 파일갤러리
			int categoryGroupNo = -1;
			try {
				homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
				categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			} catch (NumberFormatException e) {	}
			
			// Domain 클래스에 파라미터 담기
			CategoryDomain category = new CategoryDomain();
			GallaryDAO dao = new GallaryDAO();

			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		request.setAttribute("homepageNo", request.getParameter("homepageNo"));
		request.setAttribute("categoryNo", request.getParameter("categoryNo"));
		
		// 페이지 이동
		//response.sendRedirect(request.getContextPath() + "/jsp/filegallary/detailfilegallary?no=" + request.getParameter("no"));
		RequestDispatcher rd = request.getRequestDispatcher(
				"/jsp/filegallary/detailfilegallary?no=" + request.getParameter("no")
			);
		
		rd.forward(request, response);
	}
}










