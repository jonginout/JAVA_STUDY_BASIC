package aop.test04_1;

import org.aspectj.lang.ProceedingJoinPoint;

public class TimeAspect {

	public void exeTime(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("실행 시간을 측정하자...");

		long s = System.currentTimeMillis();
		
		try {
			pjp.proceed(); 
		}finally {
			double time = (System.currentTimeMillis() - s) / 1000d;
			System.out.println("실행시간 : "+ time);
		}
		
	}
	
}
