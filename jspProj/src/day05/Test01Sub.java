package day05;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test01Sub {
	public static void main(String[] args) {
		
	//%EC%9E%90%EB%B0%94123java -> 자바123java
	// 3byte를 사용
	
	// euc-kr은 한글을 2byte로 바꾼다.
	// %C0%DA%B9%D9123java
		
	String msg = "자바123java";
	
	
	try {
		String eMsg = URLEncoder.encode(
//				msg, "euc-kr"
				msg, "utf-8"
			);
		
		System.out.println(eMsg);
		
		String dMsg = URLDecoder.decode(
//				eMsg, "euc-kr"
				msg, "utf-8"
			);
		
		System.out.println(dMsg);
		
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
	
	
	
	}
}
