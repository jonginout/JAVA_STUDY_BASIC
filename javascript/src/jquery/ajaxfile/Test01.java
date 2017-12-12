package jquery.ajaxfile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/jquery/ajaxfile/test01")
public class Test01 extends HttpServlet{
	
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException {
			
			System.out.println("서블릿");
			response.setContentType("text/html; charset=utf-8");
			
			MultipartRequest mRequest = new MultipartRequest(
						request,
						"C:\\Dev\\git\\BIT_CAMP_JONGIN\\javascript\\WebContent\\upload",
						1024 * 1204 * 100,
						"utf-8",
						new DefaultFileRenamePolicy()
					);
			
			Enumeration<String> fNames = mRequest.getFileNames();
			while(fNames.hasMoreElements()) {
				String name = fNames.nextElement();
				String ori = mRequest.getOriginalFileName(name);
				String sys = mRequest.getFilesystemName(name);
				String msg = mRequest.getParameter("msg");
				
				System.out.println("ori : "+ori);
				System.out.println("sys : "+sys);
				System.out.println("msg : "+msg);
				
			}
			
			
			PrintWriter out = response.getWriter();
			out.println("<h1>Ajax 응답결과.. hello~</h1>");
			out.close();
		
	}
	
}


