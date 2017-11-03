package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import login.Login;

public class AuthFilter implements Filter{

	// 로그인이 필요없는 페이지 주소를 담는다.
	private List<String> list = new ArrayList<>();
	private List<String> dirList = new ArrayList<>(); // 디렉토리용 필터
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String nonelogin = filterConfig.getInitParameter("nonelogin");
		String [] arr = nonelogin.split(";");
		
		for (String np : arr) {
			list.add(np.trim());
		}
		
		String nonedirlogin = filterConfig.getInitParameter("nonedirlogin");
		String [] dirArr = nonedirlogin.split(";");
		
		for (String dnp : dirArr) {
			dirList.add(dnp.trim());
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		
		String uri = hRequest.getRequestURI();
		
		
		uri = uri.substring(hRequest.getContextPath().length()); //프로젝트 빼기
		
		
		boolean isRedirect = false; // 포함되는게 없을땐 트루
		
		
		System.out.println("=================================");
		System.out.println("true : 리다이렉트 / false : 통과");
		System.out.println("현재경로 : " + uri);
		System.out.println("=================================");

		if (list.indexOf(uri) == -1) { //로그인 안해도 되는게 아닌데
			HttpSession session = hRequest.getSession();
			Login user = (Login)session.getAttribute("user");
			if(user==null) {
				// 로그인이 되어있지 않은 상태면
				// 리다이렉션
				isRedirect = true;
			}
			System.out.println("로그인안해도 되는 리스트에 '없다' [리다이렉트]");
		}else {
			System.out.println("로그인 안해도 되는 리스트에 '있다' [통과]");
		}
		System.out.println("isRedirect1 : "+isRedirect);
		System.out.println();
		
		for (String dnp : dirList) {
			System.out.println("비로그인폴더 리스트 : "+dnp);
			System.out.println("현재 경로 : "+uri);
			if(uri.startsWith(dnp)) { 
				isRedirect = false;
				System.out.println("로그인 안해도 되는 폴더리스트에 '있다'. [통과]");
			}else {
				System.out.println("로그인 안해도 되는 폴더리스트에 '없다'. [유지]");				
			}
		}
		
		System.out.println("isRedirect2 : "+isRedirect);
		System.out.println();
		System.out.println();
		System.out.println("true면 리다이렉트 false면 통과");
		if(isRedirect) {
			System.out.println("리다이렉트 한다~");
		}else {			
			System.out.println("통과 한다~");
		}
		System.out.println("=================");
		
		
		if(isRedirect) {
			hResponse.sendRedirect(hRequest.getContextPath()+"/jsp/login/loginform");
		}else {
			chain.doFilter(request, response);
		} 

		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
