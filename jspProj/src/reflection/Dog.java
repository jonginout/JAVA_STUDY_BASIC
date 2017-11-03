package reflection;

public class Dog {

	private String name;
	private int age;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void fuckYou() {
		System.out.println("뻐큐");
	}

	public String fuckYou2() {
		return "꺼져";
	}
	
	public Object fuckYou3(int aaa, String bbb, Object ccc) {
		Object obj = aaa+bbb+ccc;
		return obj;
	}
	
}
