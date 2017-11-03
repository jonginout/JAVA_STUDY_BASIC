package com.jongin.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jongin.login.Login;

//작성자와 세션아이디와 같는지 체크
public class WriterCheck {

	public boolean writerChk(
			HttpServletRequest request, String writer
			) throws IOException {
		
		boolean chk = true;
		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("user");
				
		if(!login.getId().equals(writer)) {
			chk = false;
		}
		return chk;
		
	}
	
}
