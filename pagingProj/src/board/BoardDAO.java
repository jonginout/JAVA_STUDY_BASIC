/*
 *   insert 
 *   delete
 *   detail
 *   modify
 * 	 list
 */
package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ConnectionPool;

public class BoardDAO {
	
	public int listBoardCount() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(no) ")
			   .append("  from PAGING_BOARD ");
			pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 첫번째 컬럼에 있는거 반환해라
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return 0;
	}
	
	public List<BoardDomain> listBoard(Page page) throws Exception {
		List<BoardDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
				.append("    from (select rownum runum, a.*")
				.append("     from (select * ")
				.append("   from PAGING_BOARD ")
				.append("        order by no desc)  a)")
				.append("where runum between ? and ?  ");
			
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, page.getBegin());
			pstmt.setInt(index++, page.getEnd());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDomain board = new BoardDomain();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				list.add(board);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}

}













