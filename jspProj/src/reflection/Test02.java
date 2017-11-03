package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;


/*
	Class 메서드 얻어오기
*/


public class Test02 {
	public static void main(String[] args) {
		
		try {
//			test01();
//			test02();
//			test03();
			test04();
			// variable arguments : 가변인자
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void test04() throws Exception {
		// ArrayList 클래스의 메서드 중에서
		// 반환타입이 void인 메서드의
		// 이름을 화면에 출력
		
		Class<?> clz = ArrayList.class;
		Method [] mArr = clz.getDeclaredMethods();
		
		for (Method method : mArr) {
			
			Class<?> rClz = method.getReturnType();
			String ArrayListreturnType = rClz.getName();
			if (ArrayListreturnType.equals("void")) {
				System.out.println(method.getName()+"()");
			}
		}
		
	}
	
	
	private static void test03() throws Exception {
		// 원하는 메서드 하나만! 가져오기
		
		Class<?> clz = Dog.class;
		Method m = clz.getDeclaredMethod("setName", String.class);
		
		Method m2 = clz.getDeclaredMethod("getName");
		
		Method m3 = clz.getDeclaredMethod("setAge", int.class);
		
		System.out.println(m.getName());
		Class<?> rClz1 = m.getReturnType();
		System.out.println(rClz1.getName()+"\n");

		System.out.println(m2.getName());
		Class<?> rClz2 = m2.getReturnType();
		System.out.println(rClz2.getName()+"\n");
		
		System.out.println(m3.getName());
		Class<?> rClz3 = m3.getReturnType();
		System.out.println(rClz3.getName()+"\n");
		
	}
		
	private static void test02() throws Exception {

		System.out.println(sum(1, 2));
		System.out.println(sum(new int[] {1,2,3}));
		System.out.println(sum(123,213,4,25,43,34,5,4));
		
	}
	
							// 가변인자 전달
//	필요에 따라 매개변수(인수)를 가변적으로 조정할수있는 기술.
	private static int sum(int...intArr) {
		int result = 0;
		for (int i : intArr) {
			result += i;
		}
		return result;
	}
	
	
	public static void test01() throws Exception {
		
		Class<?> clz = Dog.class;
		
		System.out.println("--------------------------------");
		System.out.println("Dog 클래스의 모든 메서드 출력");
		System.out.println("--------------------------------");
		Method [] mArr = clz.getDeclaredMethods();
		// 메소드 정보를 담고 있는 Metho 클래스
		// 메소드에 대한 정보르 가지고있는 클래스 끝에 "S" 중요
		
		for (Method m : mArr) {
			
			System.out.println("메소드의 이름 : "+m.getName());
			Class<?> rClz = m.getReturnType();
			// 타입에 대한 클래스 정보를 
			// 클래스 클래스에 담아서 확인한다.
			System.out.println("반환타입의 이름 : "+rClz.getName());
			
			// 파라미터 정보 확인하기
			Parameter[] pArr = m.getParameters();
			// 파라미터 정보를 담고 있는 파라미터 클래스
			for (Parameter p : pArr) {
				Type t = p.getParameterizedType();
				// 파라미터의 타입을 알아내어
				System.out.println(t.getTypeName() + " " + p.getName());
				// 타입의 이름을 알아내고                                               // 파라미터의 이름을 알아낸다
			}
			
			System.out.println("=================");
			
		}
		
	}
	
}
