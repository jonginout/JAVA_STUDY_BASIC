package aop.test04;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

// 로그를 출력하는 !공통 기능! 클래스

// 핵심기능이 수행될때 어느 순간 끼어 넣어야 한다.
public class LogAspect {
	
	public void showLog(JoinPoint point) {
		// JoinPoint를 이용해서 호출되는 핵심클래스, 메소드 정보를 꺼낼 수 있다.
		
		Object target = point.getTarget(); // 연결된 핵심기능 클래스
		Class<?> clz = target.getClass();
		
		Signature sig =  point.getSignature(); // 연결된 메소드 정보 꺼낼 수 있다.
		
		
		SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss"
				);
		System.out.println("===================================");
		System.out.println("클래스명 : "+ clz.getName());
		System.out.println("메소드명 : "+  sig.getName());
		System.out.println("호출시간 : "+ sdf.format(new Date()));
		System.out.println("===================================");
	}
	
}
