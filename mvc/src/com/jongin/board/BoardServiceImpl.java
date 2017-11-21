package com.jongin.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jongin.common.MyAppSqlConfig;

public class BoardServiceImpl implements BoardService{

	private SqlSession session = null;
	private BoardMapper dao = null;

		
	public BoardServiceImpl() {
		session = MyAppSqlConfig.getSqlSessionInstance();
		dao = session.getMapper(BoardMapper.class);
	}
	
	public void delete(int no) throws Exception {
		dao.deleteBoard(no);
	}

	@Override
	public BoardDomain detail(int no) throws Exception {
		return dao.detailBoard(no);
	}

	@Override
	public List<BoardDomain> list(int no) throws Exception {
		return dao.listBoard();
	}

	@Override
	public void modify(BoardDomain board) throws Exception {
		dao.modifyBoard(board);
	}

	@Override
	public BoardDomain modifyForm(int no) throws Exception {
		return dao.detailBoard(no);
	}

	@Override
	public void write(BoardDomain board) throws Exception {
		dao.insertBoard(board);
	}
}
