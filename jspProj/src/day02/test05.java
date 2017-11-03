package day02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/day02/test05")
public class test05 extends GenericServlet{
	
	@Override
	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("image/jpg");
		
		OutputStream out = response.getOutputStream();
		// 이미지니깐 getWriter()를 안썼다
		BufferedOutputStream bos = new BufferedOutputStream(out);
		// 전송할 내용을 어떻게 출력할지
		
		String path = "C:/jongin/costa.jpg";
		FileInputStream fis = new FileInputStream(path);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		while(true) {
			int ch = bis.read();
			if(ch==-1) {break;}
//			System.out.print((char)ch);
			bos.write(ch);
		}
		
		bis.close();
		bos.close();
		
		
	}
	
}
