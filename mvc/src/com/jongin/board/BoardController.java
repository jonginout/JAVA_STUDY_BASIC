package com.jongin.board;

import java.util.List;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;
import org.springframework.web.mvc.RequestParam;


//이것을 붙혀준 클래스만 디스패쳐가 처리한당
@Controller
public class BoardController{
	
	private BoardService service = null;
	
	public BoardController() {
		service = new BoardServiceImpl();
	}
	
	
	@RequestMapping(value="/board/delete.do")
	public String delete(int no) throws Exception {
//		BoardMapper dao = dao.deleteBoard(no);
		service.delete(no);
		return "redirect:/board/list.do";	
	}
	
	
	@RequestMapping(value="/board/detail.do")
	public ModelAndView detail(int no) throws Exception {

		BoardDomain board = service.detail(no);

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

		List<BoardDomain> list = service.list(no);
		
		ModelAndView mav = new ModelAndView("/board/list.jsp");
		mav.addAttribute("list", list);
		
		return mav;

	}
	
	
	@RequestMapping(value="/board/modify.do")
	public String modify(BoardDomain board) throws Exception {

		service.modify(board);
		return "redirect:/board/list.do";
	}
	
	
	@RequestMapping(value="/board/modifyform.do")
	public ModelAndView modifyForm(int no) throws Exception {

		BoardDomain board = service.modifyForm(no);
		
		String view = "/board/modifyForm.jsp";
		ModelAndView mav = new ModelAndView(view);
		mav.addAttribute("board", board);
		
		return mav;
	}
	
	@RequestMapping(value="/board/write.do")
	public String write(BoardDomain board) throws Exception {

		service.write(board);

		return "redirect:/board/list.do";
	}
	
	@RequestMapping(value="/board/writeform.do")
	public void writeForm() throws Exception {}
	
	
}


