package fileGallary;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;

@WebServlet("/jsp/filegallary/writeformfilegallary")
public class WriteFormFileGallaryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int homepageNo = Integer.parseInt(request.getParameter("homepage_no"));
		
		if(WriterCheck.writerChk(request, homepageNo)==false) {
			AlertBack.alertAndBack(response, "홈페이지 관리자만 작성 가능합니다");
			return;
		}
		
		request.setAttribute("homepage_no", request.getParameter("homepage_no"));
		request.setAttribute("category_no", request.getParameter("category_no"));
		request.setAttribute("category_group_no", request.getParameter("category_group_no"));
		
		// 데이터를 사용할 페이지로 이동
		RequestDispatcher rd = request.getRequestDispatcher(
				"/jsp/fileGallary/writeFileGallary.jsp"
		);
		rd.forward(request, response);
	}
}









