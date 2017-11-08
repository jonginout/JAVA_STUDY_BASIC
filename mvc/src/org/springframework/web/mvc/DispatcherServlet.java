package org.springframework.web.mvc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Dispatcher의 역할 (FrontController)
	- 사용자의 모든 역할을 최초로 받는다. (그러기 위해서 web.xml에 등록)
	- 사용자의 요청에 해당하는 작업클래스 (Controller)를 찾아서 호출
	- 컨트롤러의 공통적인 처리작업을 여기서 다 해준다.
	  : 파라미터 처리,
	  : 화면에 사용할 데이터 공유
	  : 페이지 이동!
*/

public class DispatcherServlet extends HttpServlet{
	
	private URLHandlerMapping mapping;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String componentScan = config.getInitParameter("component-scan");
		try {
			mapping = new URLHandlerMapping(componentScan);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		//메모리에 올라올때 딱 한번만 하고 싶다.
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("프론트 컨트롤러 호출되었음...");
		
		
		//특정 URL에 대한 작업 클래스를 호출한다;
		String uri = request.getRequestURI();
		
		// /mvc
		System.out.println("uri : "+uri);
		
		// - 사용자 요청 URL에서 웹어플리케이션 경로 제거하기
		String contextPath = request.getContextPath();
		
		uri = uri.substring(contextPath.length());
		System.out.println("경로제외 URI :" + uri);
		
		try {

			CtrlAndMethod cam = mapping.getCtrlAndMethod(uri); 
			// 많은 Map 중에서 uri(key)에 맞는 value(메소드)를 가져온다  즉 ,
			// Map<String, CtrlAndMethod> 의 value값 CtrlAndMethod를 가져온다.
			
			
			//url이 잘못된게 왔을 때 처리
			if(cam==null) {
				throw new Exception("잘못된 URL 입니다.");
			}
			
			Method m = cam.getMethod();
			Object target = cam.getTarget();
			
			
			// 파라미터 처리 클래스///////////////////////////////////
			PreParameterProcess ppp = new PreParameterProcess();
			Object[] params = ppp.process(m, request, response);
			// 배열 그대로 -> 인보크 해도 된다.
			
//			ModelAndView mav = (ModelAndView)m.invoke(target, params); // Object[] params
			// cam.getMethod()로 CtrlAndMethod에 담긴 메소드를 실행하고
			// cam.getTarget()로 객체 연결을 해 invoke 한다.
			// 그거의 리턴값은 ModelAndView
			
//			String view = mav.getView();

			
			// 반환타입 처리 
			Class<?> rType = m.getReturnType();
			String rName = rType.getSimpleName(); // 반환타입명
			
			String view = ""; //리다이렉션 링크
			ModelAndView mav = null;
			
			switch (rName) {
			case "String":
				view = (String)m.invoke(target, params);
				break;
			case "void":
				m.invoke(target, params);
//				view = "/jsp"+uri.replace(".do", ".jsp");
				view = uri.replace(".do", ".jsp");
				break;
			case "ModelAndView":
				mav = (ModelAndView)m.invoke(target, params);
				view = mav.getView();
				break;
			}
			
			/////////
			
			if(view.startsWith("redirect:")) {
				view = view.substring("redirect:".length());
				if (view.charAt(0)=='/') {
					view = contextPath+view;
				}
				response.sendRedirect(view);
			}else if (view.startsWith("jsonView:")) {
				view = view.substring("jsonView:".length());
				response.setContentType("application/x-json; charset=UTF-8");
				response.getWriter().print(view);
			}else {
				if (mav != null) {
					//모델앤뷰가 존재한다면.
					
					// forward 방식일 경우에는 mav 객체에 있는 맵의 데이터를 꺼내서
					// 공유 영역에 등록시키는 작업을 진행한다.
		            for (Object oo : mav.getModel().entrySet()) {
		                  Map.Entry me = (Map.Entry)oo;
		                  request.setAttribute(
		                		  (String)me.getKey(), me.getValue()
		                	  );        
		            }
		            
	            }

				
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
