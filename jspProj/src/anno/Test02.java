package anno;

public class Test02 {
	@MySingle(value="a")
	@MySingle2(name="b")
	public void call() {}

	// 속성명을 입력하지 않은 경우 value 속성에 설정
	@MySingle("a")
//	@MySingle2("b")  // name 속성은 자동 찾지 못한다. 에러발생...
	public void call2() {}

	@MySingle
	@MySingle2(name="b")
	public void call3() {}
}








