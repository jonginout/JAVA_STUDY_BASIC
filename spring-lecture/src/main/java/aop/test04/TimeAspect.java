package aop.test04;

import org.aspectj.lang.ProceedingJoinPoint;

public class TimeAspect {
// 실행시간 측정 (공용클래스)
// 처음과 끝 모두 있어야 하기 때문에 around 라는 점 숙지

	// around 방식 사용시 매우 주의!!
	// 공통 기능 클래스에서 핵심 기능 클래스의 메소드를 직접 호출해야 한다!!
	// ProceedingJoinPoint : 핵심기능 클래스에 대한 정보+호출기능
	public void exeTime(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("실행 시간을 측정하자...");

		long s = System.currentTimeMillis();
		
		try {
			pjp.proceed(); // 핵심기능을 호출
		}finally {
			double time = (System.currentTimeMillis() - s) / 1000d;
			System.out.println("실행시간 : "+ time);
		}
		
	}
	
}
