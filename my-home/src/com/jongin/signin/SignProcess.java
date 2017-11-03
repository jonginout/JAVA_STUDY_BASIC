package com.jongin.signin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signin/signinprocess")
public class SignProcess extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		
		SigninMapper dao = new SigninMapper();
		Signin signin = new Signin();
		signin.setId(id);
		signin.setPass(pass);
		signin.setName(name);
		
		dao.insertMember(signin);
		response.sendRedirect(request.getContextPath()+"/login/loginform");

	}
	
}