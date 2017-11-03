package com.jongin.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jongin.common.HanbitFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/modify")
public class ModifyBoardController extends HttpServlet{

	@Override
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat(/* /회원명/게시판 */"/yyyy/MM/dd/HH");
		String subPath = sdf.format(new Date());
		String uploadPath = "C:/jongin/upload";
		File f = new File(uploadPath + subPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		MultipartRequest mRequest = new MultipartRequest(
				request,	// request 자체를 넘긴다.
				uploadPath+subPath,	// 어디에 저장할지 위치
				1024 * 1024 * 100,	// 최대 사이즈 크기 (1024*1024 = 1메가)
				"utf-8",	// 파라미터값 인코딩을 뭐로 하겠냐
				new HanbitFileRenamePolicy()	// 기본제공 이름 중복 정책
				//생성자만 호출해도 자동으로 rename 메소드 호출
			);
		
		// 화면에서 넘어온 파라미터 추출하기
		int no = Integer.parseInt(mRequest.getParameter("no"));
		String title = mRequest.getParameter("title");
		String content = mRequest.getParameter("content");
		String writer = mRequest.getParameter("writer");
		System.out.println(no);
		System.out.println(title);
		System.out.println(content);
		System.out.println(writer);
		
		List<FileDomain> fileList = new ArrayList<>();
		
		Enumeration<String> fNames = mRequest.getFileNames();
		while (fNames.hasMoreElements()) {
			String fName = fNames.nextElement();
			System.out.println("fName : " + fName);
			File file = mRequest.getFile(fName);
			if (file != null) {
				long fileSize = file.length();
				String oriName = mRequest.getOriginalFileName(fName);
				String systemName = mRequest.getFilesystemName(fName);
				System.out.println("사이즈 : " + fileSize);
				System.out.println("원래이름 : " + oriName);
				System.out.println("시스템이름 : " + systemName);
				FileDomain fd = new FileDomain();
				fd.setFilePath(subPath);
				fd.setFileSize(fileSize);
				fd.setOriName(oriName);
				fd.setSystemName(systemName);
				fileList.add(fd);
			}
		}
		
		// Domain 클래스에 파라미터 담기
		BoardDomain board = new BoardDomain();
		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		// DAO를 호출해서 작업처리 지시
		BoardMapper dao = new BoardMapper();
		try {
			dao.modifyBoard(board);
			for (FileDomain file : fileList) {				
				dao.insertFile(file, no);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		
		// 처리된 결과를 보여줄 화면 관련 처리
		// 게시물 등록이 성공하였음..
		response.sendRedirect(request.getContextPath()+"/board/detail?no="+no);

	}
	
}
