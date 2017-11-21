package aop.test03;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProxyController implements Controller{

	private Controller controller;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ProxyController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void execute() throws Exception {
		System.out.println(
				controller.getClass().getName()+
				".execute 메소드 호출된 시간 : "+sdf.format(new Date())
		);
		controller.execute();		
	}
	
}
