package reflection;

import java.util.ArrayList;
import java.util.Random;

public class Test01 {
	
	public static void main(String[] args) {
		
		try {
			test01();
			System.out.println("========");
			test02();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void test02() throws Exception {
		
		Object obj = getObject();
		// 무슨 객체를 받을지 모르는 상황

		Class<?> clz = obj.getClass();
		System.out.println(clz.getName());
		
	}
	
	
	
	public static Object getObject() throws Exception {
		
		Object [] arr = {
				
				new String("a"),
				new Dog(),
				new ArrayList<String>()
		};
		Random  r = new Random();
		return arr[r.nextInt(arr.length)];
		
		// 아무 객체나 반환한다.
	}

		
		
	public static void test01() throws Exception {
/*	
		 리플렉션 : 클래스 정보가 로딩이 된다.. 
					: method area 영역에 올라가 있는 클래스 정보를
					: 조회 및 활용
		
		 method area 에는 객체정보 (클래스 정보가) 딱 하나만 올라간다
*/	
		
		
//		클래스 정보에 접근하는 3가지 방법
//		1번째 방식.
//			Class clz = Class.forName("패키지명 포함한 클래스명");
//			반환은 클래스란 클래스를 반환
	
		Class<?> clz = Class.forName("reflection.Dog");
		
//		2번째 방식.
//			Class clz = 클래스이름.class;
		
		Class<?> clz2 = Dog.class;
		
//		3번째 방식.
//			객체 활용
//			Class clz = 객체.getClass();

		Dog d = new Dog();
		Class<?> clz3 = d.getClass();
		// 모든 오브젝트는 getClass();가 있다.
		
		System.out.println(clz == clz2);
		System.out.println(clz3 == clz2);
//		clz, clz2, clz3 의 주소는 다 같다
//		메소드 area에는 클래스 정보 딱 하나만 올라가니깐
//		clz, clz2, clz3는 같은 곳을 바라본다.
	
		
		
		System.out.println(clz3.getName());
		// 클래스의 풀네임
		
		System.out.println(clz3.getSimpleName());
		// 객체 자체의 이름
		

	}
}
