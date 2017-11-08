package org.springframework.web;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

//알아서 set 해준다
public class WebUtil {
	
	// clz로 들어온 클래스 정보를 읽고 request의 파라미터에서 정보를 추출한다음
	// 파라미터 정보가 설정된 객체를 반환
	public static Object toVO( Object request ,Class<?> clz ) 
					throws Exception {
		
		// 메서드를 실행하기 위한 객체 생성 // domain
		Object obj = clz.newInstance();
		
		// clz 클래스 객체의 모든 메서드를 추출
		Method[] mArr = clz.getDeclaredMethods();
		for (Method m : mArr) {
			
			// 메서드의 이름 확인하기 
			// getNo, setNo, getTitle, setTitle, getWriter. ... 
			String mName = m.getName();
			
			// 메서드의 이름이 set로 시작하는 메서드인지 확인
			// setNo, setTitle, setWriter...
			if (mName.startsWith("set") == false) continue;
			
			// set 이름 제거하기, No, Title, Writer ... 
			mName = mName.substring("set".length());
			
			// 첫글자를 소문자로 변경하기 : no, title, writer ...
			mName = Character.toLowerCase(mName.charAt(0))
				  + mName.substring(1);
			
			// 변경된 이름에 해당하는 파라미터 추출하기
			String reqName = request.getClass().getSimpleName();
			System.out.println("mName : "+mName);
			System.out.println("reqName : "+reqName);
			
			String pValue = null;
			switch (reqName) {
			case "MultipartRequest" :
				pValue = ((MultipartRequest)request).getParameter(mName);	
				break;
			default :
				pValue = ((HttpServletRequest)request).getParameter(mName);	
			}
			
			// 이름에 해당하는 파라미터가 없는 경우
			if (pValue == null) continue;
			
			// 메서드와 해당하는 파라미터값이 존재하는 경우 메서드의 매개변수 타입이 정수인 경우
			// 문자열을 정수로 변경한 다음 값을 대입
			String pTypeName = m.getParameterTypes()[0].getName();
			switch (pTypeName) {
			case "int":
				m.invoke(obj, Integer.parseInt(pValue));
				break;
			default: 	
				m.invoke(obj, pValue);
			}
		}
		
		return obj;
	}
}







