package com.jongin.board;

import java.util.List;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;
import org.springframework.web.mvc.RequestParam;

//이것을 붙혀준 클래스만 디스패쳐가 처리한당
@Controller
public class BoardController{
	
	@RequestMapping(value="/board/delete.do")
	public String delete(int no) throws Exception {
		
		BoardMapper dao = new BoardMapper();
		dao.deleteBoard(no);

		return "redirect:/board/list.do";	
	}
	
	
	@RequestMapping(value="/board/detail.do")
	public ModelAndView detail(int no) throws Exception {

		BoardMapper dao = new BoardMapper();
		BoardDomain board = dao.detailBoard(no);

		ModelAndView mav = new ModelAndView("/board/detail.jsp");
		mav.addAttribute("board", board);

		return mav;
	}
	

	@RequestMapping(value="/board/list.do")
	public ModelAndView list(
			@RequestParam(name="pageNo", defaultValue="1") int no
			// 파라미터 이름이 pageNo인걸 찾아서 no변수에 담아줘
			// 만약에 pageNo 파라미터에 값이 없다면 기본값은 1이다. 라고 정의한 어노테이션 사용
			) throws Exception {

		BoardMapper dao = new BoardMapper();
		List<BoardDomain> list = dao.listBoard();
		
		ModelAndView mav = new ModelAndView("/board/list.jsp");
		mav.addAttribute("list", list);
		
		return mav;

	}
	
	
	@RequestMapping(value="/board/modify.do")
	public String modify(BoardDomain board) throws Exception {

		BoardMapper dao = new BoardMapper();
		dao.modifyBoard(board);

		return "redirect:/board/list.do";
	}
	
	
	@RequestMapping(value="/board/modifyform.do")
	public ModelAndView modifyForm(int no) throws Exception {

		BoardMapper dao = new BoardMapper();
		BoardDomain board = dao.detailBoard(no);
		
		String view = "/board/modifyForm.jsp";
		ModelAndView mav = new ModelAndView(view);
		mav.addAttribute("board", board);
		
		return mav;
	}
	
	@RequestMapping(value="/board/write.do")
	public String write(BoardDomain board) throws Exception {

		BoardMapper dao = new BoardMapper();
		dao.insertBoard(board);

		return "redirect:/board/list.do";
	}
	
	@RequestMapping(value="/board/writeform.do")
	public void writeForm() throws Exception {}
	
	
}


