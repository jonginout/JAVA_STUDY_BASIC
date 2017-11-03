package anno;

import java.io.Serializable;

@MyTarget
public class Test01 implements Serializable {

	@MyTarget
	private String msg; 
	// 멤버변수에 어노테이션을 설정

	@MyTarget
	public void call() {}
	// 메소드에도 어노테이션을 설정
	
	public void call(@MyTarget int i) {}
	// 메소드 인자에도 어노테이션 설정
	
}
