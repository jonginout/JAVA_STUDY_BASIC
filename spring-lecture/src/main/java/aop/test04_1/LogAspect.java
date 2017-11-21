package aop.test04_1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;


public class LogAspect {
	
	public void showLog(JoinPoint point) {
		
		Object target = point.getTarget(); 
		Class<?> clz = target.getClass();
		
		Signature sig =  point.getSignature();
		
		
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
