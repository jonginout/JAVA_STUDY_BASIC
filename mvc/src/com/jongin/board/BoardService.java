package com.jongin.board;

import java.util.List;

import org.springframework.web.mvc.RequestParam;

public interface BoardService {
	public void delete(int no) throws Exception;
	public BoardDomain detail(int no) throws Exception;
	public List<BoardDomain> list(
			@RequestParam(name="pageNo", defaultValue="1") int no
			) throws Exception;
	public void modify(BoardDomain board) throws Exception;
	public BoardDomain modifyForm(int no) throws Exception;
	public void write(BoardDomain board) throws Exception;
}
