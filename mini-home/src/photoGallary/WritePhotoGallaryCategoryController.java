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

import common.CategoryDomain;
import common.HanbitFileRenamePolicy;

@WebServlet("/jsp/photogallary/writephotogallarycategory")
public class WritePhotoGallaryCategoryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
	
		int homepageNo = -1;			
		int categoryGroupNo = -1;
		int no;
		String name = request.getParameter("name");

		try {
			homepageNo = Integer.parseInt(request.getParameter("homepage_no"));			
			categoryGroupNo = Integer.parseInt(request.getParameter("category_group_no"));
		} catch (NumberFormatException e) {	}
		// Domain 클래스에 파라미터 담기
		CategoryDomain category = new CategoryDomain();
		GallaryDAO dao = new GallaryDAO();
		
		try {
			no = dao.detailCategoryNo();
			category.setCategoryGroupNo(categoryGroupNo);
			category.setCategoryNo(no);
			category.setHomepageNo(homepageNo);
			category.setName(name);
			
		} catch (Exception e) {	e.printStackTrace();}
		
		if(request.getParameter("modifyCategory") != null) {
			category.setCategoryNo(Integer.parseInt(request.getParameter("category_no")));
			category.setName(request.getParameter("modifyCategory"));
			try {
				dao.modifyCategory(category);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	else {
			try {
				dao.insertCategory(category);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		request.setAttribute("homepage_no", homepageNo);
		request.setAttribute("category_group_no", categoryGroupNo);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher(
						"/jsp/photogallary/listphotogallarycategory"
				);
		rd.forward(request, response);

	}
}










