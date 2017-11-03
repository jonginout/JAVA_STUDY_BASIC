package day02;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyHttpservlet;

// 서블릿 클래스를 정의하는 방법
// 1. 서블릿 인터페이스를 상속하기
// 2. GenericServlet 추상 클래스를 상속하기
// 3. HttpServlet 추상 클래스를 상속하기
// get방식으로 올때 post 올때마다 다른 메소드를 호출한다.
// 즉 get 방식 post 방식 처리를 따로 하고 싶으면 HttpServlet을 사용

@WebServlet("/day02/test02")
public class test02 extends MyHttpservlet{
	


	
	
}
