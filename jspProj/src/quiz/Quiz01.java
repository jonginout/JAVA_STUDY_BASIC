package quiz;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/quiz/quiz01")
public class Quiz01 implements Servlet{

	private Map<String, String> tMap;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub	
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		tMap = new HashMap<>();
		tMap.put("music", "음악");
		tMap.put("movie", "영화");
	}

	//count 1부터 10 사이의 숫자를 입력
	//type music, movie 두개의 값중에서 하나가 입력됨
	
	// 서블릿이 해야 할 일
	// music일 경우 "음악" 이라는 단어를 count에서 보내온 숫자만큼 출력
	// movie 일 경우 "영화" ~~
	// 뮤직도 무비도 아닐경우 잘못된 타입이라고 출력
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int count = 1;
		if(request.getParameter("count")!=null) {			
			count = Integer.parseInt(request.getParameter("count"));	
		}
		String type = request.getParameter("type");
		
		String view = tMap.get(type);
		if(view == null) {
			System.out.println("잘못된 타입");
			return;
		}
		
		for (int i = 0; i < count; i++) {
			System.out.println(view);
		}
		
	}

}
