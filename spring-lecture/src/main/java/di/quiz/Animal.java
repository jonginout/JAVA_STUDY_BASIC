package di.quiz;

public class Animal{
	private String msg;
	
	public void setMsg(String msg) {
		System.out.println("setMsg");
		this.msg = msg;
	}
	
	public void info() {
		System.out.println("info : "+msg);
	}

}
