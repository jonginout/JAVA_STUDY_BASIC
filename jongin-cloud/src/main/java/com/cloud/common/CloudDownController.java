package com.cloud.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.repository.vo.Tree;


@Controller
public class CloudDownController extends HttpServlet{

	@RequestMapping("/common/down.do")
	@ResponseBody
	public void down(
			String ext,
			Tree file, HttpServletResponse response
			) throws Exception {
		
		
		System.out.println("파일"+file.getTitle());
		System.out.println("패스"+file.getPath());

		String dName = file.getTitle(); 
		
		File f = new File(file.getPath());
		
		System.out.println(ext);
		
		if (ext.equals("pdf")) {
			response.setHeader(
					"Content-Disposition", 
					"inline; filename=help.pdf"
			);
		}else if (dName == null) {
			// dName 파라미터가 없을때
			// 즉 다운로드 안받고 싶을때
			response.setHeader(
					"Content-Type",
					"image/jpg"
			);
		}else {
			// 다운로드
			
			
			// 브라우저가 출력말고 다운로드 할 수 있도록 한다
			
			// 헤더 값을 변경해준다. 
			// 다운로드 할 때 사용하는 컨텐트 타입
			response.setHeader(
					"Content-Type",
					"application/octet-stream"
			);
			// 원 파일명이 한글일 경우 인코딩
			// 8859_1 방식으로 문자열을 조합해라
			dName = new String(
					dName.getBytes("utf-8"), "8859_1");
			
			// 다운로드시 파일명 지정
			// 다운로드시 파일을 지정하는 타입
			response.setHeader(
					"Content-Disposition", 
					"attachment;filename=" + dName
			);
			// 보내는 데이터는 바이너리 인코딩 임을 알려준다.
			response.setHeader(
					"Content-Transfer-Encoding", 
					"binary"
			);	
			// 브라우저에게 내가 보내는 거의 길이를 알려준다

			response.setHeader(
					"Content-Length", 
					String.valueOf(f.length())
			);
				 // 다른 데이터 타입을 스트링으로
			// -------------------------------------------------------------
		}
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		OutputStream out = response.getOutputStream(); // 파일이니깐 write 하면 안됨
		BufferedOutputStream bos = new BufferedOutputStream(out);
		while (true) {
			int ch = bis.read();
			if (ch == -1) break;
			
			bos.write(ch); //사용자에게 출력
		}
		
		bis.close();
		fis.close();

		bos.close();
		out.close();
		
	}
	
}