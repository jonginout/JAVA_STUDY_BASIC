package org.springframework.web.mvc;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.WebUtil;

/*
	메소드 객체의 매개변수
	정보를 꺼내서 사용자 요청
	파라미터에서 해당하는 값을
	추출 설정한 다음 Dispatcher에게 넘겨준다.
 */

public class PreParameterProcess {
	
	public Object[] process(
			Method m, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
	
		// 파라미터 정보 확인하기
		Parameter[] pArr = m.getParameters();

		Object [] rArr = new Object[pArr.length];
		// 파라미터 개수 만큼 배열크기를 만든다.
		
		int index=0;
		for (Parameter p : pArr) {
			

			Class<?> tClz = p.getType();
			// 클래스를 넘겨줘야 하니깐 
			String tName = tClz.getSimpleName(); // 타입이름
			String pName = p.getName(); // 파라미터 명
			
			
			// @RequestParam(name="pageNo", defaultValue="1") int no
			// 이 파라미터에 RequestParam이 존재한다면
			RequestParam rp = p.getAnnotation(RequestParam.class);
			String defaultValue = null;
			if (rp != null) {
				// 어노테이션이 있으면
				pName = rp.name(); // 파라미터명이 바뀌고
				defaultValue = rp.defaultValue(); // 디폴트값도 바뀐다.
			}
			String pValue = request.getParameter(pName); 
			// pName에 대한 파라미터 얻고
			

			
			if (pValue == null) { // 파라미터 값이 없으면
				pValue = defaultValue;
				// 기본값 처리
			}
			
			switch (tName) {
			case "int":
				rArr[index++] = 
					Integer.parseInt(pValue);
				break;
			case "String":
				rArr[index++] = pValue;
				break;
			case "HttpServletRequest":
				rArr[index++] = request;
				break;
			case "HttpServletResponse":
				rArr[index++] = response;
				break;
			case "HttpSession":
				rArr[index++] = request.getSession();
				break;
			default:
				rArr[index++] = WebUtil.toVO(request, tClz);
			}
			
		}
		
		return rArr;
		
	}
	
}
