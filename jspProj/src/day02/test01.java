package day02;


import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/day02/test01")
public class test01 extends GenericServlet{
	
	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException{
		//추상 메소드 구현
	}
	
}
