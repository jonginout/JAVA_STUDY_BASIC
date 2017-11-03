package day02;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day02/test04")
public class test04 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 클라이언트가 보내준 파라미터 중 : fruit
		String [] fruit = request.getParameterValues("fruit");
		// 같은 파라미터의 여러개의 값이 넘어올때 사용하는 메소드
		
		String str = "";
		if (fruit != null) {			
			for(String f : fruit) {
				System.out.println(f);
				if(f.equals("1")) {
					f="사과";
				}else if (f.equals("2")) {
					f="포도";					
				}else if (f.equals("3")) {
					f="딸기";										
				}
				str += f+" ";
			}
		}
		
		// 사용자에게 결과를 전송하기
		// 브라우저에게 전송된 데이터의 종류를 알려준다.
		// (전송된 데이터가 mp3인지 pdf인지 jpg 인지 html 등 ...)
		
		response.setContentType("text/html; charset=UTF-8");
						//  메인타입/서브타입
		// 응답 내용에 한글이 있을경우 한글이 깨지게 되므로 한글 처리를 해준다
		// charset=UTF-8
		
		// 내용을 브라우져로 전송하기 - 출력객체 얻기
		
		// 바이트 단위 출력
		// OutputStream out = response.getOutputStream();
		
		// 문자 단위 출력
		PrintWriter out = response.getWriter();
		
		// 전송할 내용
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("	<body>");
		html.append("		<h1>서블릿 결과 출력</h1>");
		html.append("		<h3>"+str+"</h3>");
		html.append("	</body>");
		html.append("</html>");
		
		
		out.write(html.toString());
		out.close();
		
		
	}
	
}
