package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/writeform")
public class WriteFormBoardController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서비스가 보통 하는 일
		
		// 1.데이터를 구하기 위한 파라미터
		// 2.데이터를 구하는 것
		// 3.데이터 구한 것을 사용할 페이지로 이동
		// 4.단순 페이지 이동
		
		RequestDispatcher rd = request.getRequestDispatcher(
					"/board/writeForm.jsp"
				);
		rd.forward(request, response);
		
	}
	
}
