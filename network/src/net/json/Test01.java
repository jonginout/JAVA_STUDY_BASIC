package net.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Test01 {
	public static void main(String[] args) {
		test01(); // json 직접
		test02(); // gson 사용
		test03(); // list 담기
		test04(); // 객체속 객체 담기
		
		
		/// JSON 문자열 -> 객체
		test05();
	}
	public static void test05() {
		
		String msg = "{\"name\":\"린델로프\",\"age\":21,\"hobbys\":[\"야구보기\",\"축구하기\",\"똥싸기\"],\"addr\":{\"postNo\":\"1232\",\"basic\":\"서울시 강남구\",\"detail\":\"한빛 213-2\"}}\r\n";
		Member m = new Gson().fromJson(msg, Member.class);
		
		System.out.println(m.getAge());
		System.out.println(m.toString());
		
	}
	public static void test04() {
		
		Member m = new Member();
		m.setName("린델로프");
		m.setAge(21);
		
		List<String> hobbys = new ArrayList<>();
		hobbys.add("야구보기");
		hobbys.add("축구하기");
		hobbys.add("똥싸기");
		m.setHobbys(hobbys);
		
		Address addr = new Address();
		addr.setPostNo("1232");
		addr.setBasic("서울시 강남구");
		addr.setDetail("한빛 213-2");
		m.setAddr(addr);
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(m));
		
	}
	public static void test03() {
		
		Member m = new Member();
		m.setName("린델로프");
		m.setAge(21);

		List<String> hobbys = new ArrayList<>();
		hobbys.add("야구보기");
		hobbys.add("축구하기");
		hobbys.add("똥싸기");
		m.setHobbys(hobbys);
		Gson gson = new Gson();
		System.out.println(gson.toJson(m));
		
	}
	
	public static void test02() {
		
		Member m = new Member();
		m.setName("린델로프");
		m.setAge(21);
		
		// 클래스의 데이터를 json 형태로 표시
		Gson gson = new Gson();
		String result = gson.toJson(m);
		System.out.println(result);
		
	}
	

	public static void test01() {

		Member m = new Member();
		m.setName("린델로프");
		m.setAge(21);
		
		// 클래스의 데이터를 json 형태로 표시
		String result = "{\"name\" : \""+m.getName()+"\",\"age\" : "+m.getAge()+"}";
		System.out.println(result);
		
	}
}
