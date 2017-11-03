package anno;

@MyRuntime(id="hong", msg="annotation")  // RUNTIME
@MyClass(value="class test")             // CLASS
public class Apply {

	@MyRuntime(id="hong", msg="annotation")  // RUNTIME
	@MyClass2(value="class test")             // CLASS
	public void info() {}
	
	public void call() {}
}






