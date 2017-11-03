package common;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import login.Login;


// 세션 아이디를 반환한다.
public class WriterSelect {

	public static String writerSel(HttpServletRequest request)
			throws IOException {
		
		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("user");

		return login.getId();
		
	}
	
}
