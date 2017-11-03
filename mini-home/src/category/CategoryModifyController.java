package category;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AlertBack;
import common.WriterCheck;

@WebServlet("/jsp/category/categorymodify")
public class CategoryModifyController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("@WebServlet(\"/jsp/category/categorymodify\")\r\n");
		try {
			int homepageNo = Integer.parseInt(request.getParameter("homepageNo"));
			int categoryGroupNo = Integer.parseInt(request.getParameter("categoryGroupNo"));
			int modifyNo = Integer.parseInt(request.getParameter("modifyNo"));
			int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
			/*System.out.println("modifyHOme : " + homepageNo);
			System.out.println("modifycate : " + categoryGroupNo);
			System.out.println("modifyno : " + modifyNo);
			System.out.println("cateno : " + categoryNo);
			*/
			boolean chk = WriterCheck.writerChk(request, homepageNo);
			if(!chk ) {
				AlertBack.alertAndBack(response, "작성자만 수정 가능합니다.");
				return;
			}
			request.setAttribute("categoryNo", categoryNo);
			request.setAttribute("homepageNo", homepageNo);
			request.setAttribute("categoryGroupNo", categoryGroupNo);
			request.setAttribute("modifyNo", modifyNo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/category/categorylist");
			rd.forward(request, response);
		
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
}
