package di.test05;

public class Menu {
	public Menu() {
		System.out.println("Menu 기본 생성자");
	}
	
	/////////////////////////////////////////

	
	private String desc;
	//생성자로 문자열 주입
	public Menu(String desc) {
		this.desc = desc;
		System.out.println("Menu 문자열 생성자"+desc);
	}
	
	public void printDesc() {
		System.out.println("desc : "+desc);
	}

	/////////////////////////////////////////
	
	private ISand sand;
	
	// 생성자로 객체 주입
	public Menu(ISand sand) {
		this.sand = sand;
		System.out.println("Menu 샌드위치 생성자 ");
	}
	
	public void printInfo() {
		sand.info();
		System.out.println(sand.getClass());
	}
}
