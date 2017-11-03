package reflection;

import java.lang.reflect.Method;

/*

	- 리플렉션을 이용한 객체 생성
	- 리플렉션을 이용한 메소드 실행

*/

public class Test03 {
	public static void main(String[] args) {
		
		try {
//			test01();			
			test02();			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void test02() throws Exception{
		/*
		 Dog d = new Dog();
		 d.setName("쫑");
		 String name = d.getName();
		 System.out.print(name);
		 */
		
		Class<?> clz = Dog.class;
		Object obj = clz.newInstance();
		// 인스턴스 객체 마져 Object에 담는다.
		
		//setName 메소드를 가져온다
		Method m = clz.getDeclaredMethod("setName", String.class);
		Method m2 = clz.getDeclaredMethod("getName");
		m.invoke(obj, "똥꼬삥꼬");
		String name = (String)m2.invoke(obj);
		System.out.println(name);
		
		
		Class<?> clz2 = Dog.class;
		Object obj2 = clz2.newInstance();
		
		Method m3 = clz2.getDeclaredMethod("fuckYou3", int.class , String.class, Object.class);
		Object result = m3.invoke(obj2, 1, "뼝신", new String[] {"바부","장애인"});
		System.out.println(result.toString());
	}

		
	private static void test01() throws Exception{
		// 객체 생성하기
		Class<?> clz = Dog.class;
		
		Dog d = (Dog)clz.newInstance();
		// 인스턴스 객체 , 생성
		// Dog d = new Dog();
		d.setName("리플렉션을 이용한 객체");
		System.out.println(d.getName());
		
	}
}
