/*
 *   insert 
 *   delete
 *   detail
 *   modify
 * 	 list
 */
package com.jongin.board;

import java.util.List;

public interface BoardMapper {
	
	public void insertBoard(BoardDomain board) throws Exception;
	public void modifyBoard(BoardDomain board) throws Exception;
	public void deleteBoard(int no) throws Exception;
	public BoardDomain detailBoard(int no) throws Exception;
	public List<BoardDomain> listBoard() throws Exception;
}













