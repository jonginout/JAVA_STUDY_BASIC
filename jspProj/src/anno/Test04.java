package anno;

import java.lang.reflect.Method;

public class Test04 {
	public static void main(String[] args) {
		// Apply 클래스의 클래스 정보 얻기
		Class<?> clz = Apply.class;
		MyRuntime myRun = clz.getAnnotation(MyRuntime.class);
		MyClass myCls = clz.getAnnotation(MyClass.class);
		System.out.println("myRun : " + myRun);
		// 런타임이라 정보 존재, 리플렉션을 통해 검색이 가능하다
		
		System.out.println("myCls : " + myCls);
		// 클래스 안에는 어노테이션이 추가 되지만 JVM에서는 안된다.
		
		
		System.out.println("===========================");
		
		
		// 모든 메서드들을 얻기 : 변수명 mArr (Apply 클래스의 메소드들)
		Method[] mArr = clz.getDeclaredMethods();
		
		int i = 0;
		for (Method m : mArr) {
			System.out.println("메서드명 : " + m.getName());
			// Policy 가 CLASS 로 설정되어서 실행시에 참조되지 않는다.(null 반환)
			
			
			MyClass2 anno2 = m.getAnnotation(MyClass2.class);
			// info 메서드일 경우에는 값이 존재
			// call 메서드일 경우 존재하지 않는다.
			
			if (anno2 != null) {
				System.out.println("anno2.value : " + anno2.value());
			}else {
				System.out.println("anno2 : " + anno2);
			}
			
			
			MyRuntime anno1 = m.getAnnotation(MyRuntime.class);
			
			
			if (anno1 != null) {
				System.out.println("anno1.id : " + anno1.id());
				System.out.println("anno1.msg : " + anno1.msg());
			}else {
				System.out.println("anno1 : " + anno1);
			}
			System.out.println(i++);
			System.out.println();
		}
	}
}

















