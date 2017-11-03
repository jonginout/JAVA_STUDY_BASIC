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
	
	
	public void insertBoard(BoardDomain board)
			throws Exception {
		// t17_board 테이블에 데이터를 입력..
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into t17_board( ");
			sql.append("	no,  ");
			sql.append("	title,  ");
			sql.append("	writer,  ");
			sql.append("	content  ");
			sql.append(") values (  ");
			sql.append("	s17_board_no.nextval,  ");
			sql.append("   ?, ?, ? ");
			sql.append(") ");

			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, board.getTitle());
			pstmt.setString(index++, board.getWriter());
			pstmt.setString(index++, board.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	
	public void modifyBoard(BoardDomain board) throws Exception { //예외를 간접처리
		
		//멤버변수로 안두는 이유는 필요할때만 쓰고 버리기 위해
		//이 메소드에서만 살아있는 객체이기 때문에
		
		Connection con = null;	//연결객체
		PreparedStatement pstmt = null;
		
		try { //외부자원을 쓰는것은 무조건 예외처리가 필요
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update t17_board")
			   .append("	set title = ?, ")
			   .append("		content = ?, ")
			   .append("		writer = ? ")
			   .append(" where no = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, board.getTitle());
			pstmt.setString(index++, board.getContent());
			pstmt.setString(index++, board.getWriter());
			pstmt.setInt(index++, board.getNo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e; //나를 부른쪽으로 예외를 넘긴다.
		}finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	public void deleteBoard(int no) throws Exception { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			   .append("  from t17_board ")
			   .append(" where no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			   
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	public BoardDomain detailBoard(int no) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from t17_board ")
			   .append(" where no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			// 결과에 대한 정보를 담는 ResultSet
			if(rs.next()) {
				BoardDomain board = new BoardDomain();
				board.setNo(no);
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				return board;
			}
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return null;
	}
	
	public Integer listCount() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(no) ")
			   .append("  from t17_board ");
			pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer listCount = rs.getInt("count(no)");
				return listCount;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return null;
	}
	
	public List<BoardDomain> listBoard(int startPoint, int endPoint) throws Exception {
		List<BoardDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from ")
			   .append("  ( select a.*, row_number() ")
			   .append("  over (order by no desc) ")
			   .append("  as num ")
			   .append("  from t17_board a ) ")
			   .append(" where num between ? and ? ");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, startPoint);
			pstmt.setInt(index++, endPoint);
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
	
//////////////////////////	댓글
//////////////////////////	댓글
//////////////////////////	댓글
	
	public Integer listCommentCount(int no ) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select count(comment_no) ")
			   .append("  from t17_comment ")
			   .append(" where no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer listComment = rs.getInt("count(comment_no)");
				return listComment;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return null;
	}
	
	public List<CommentDomain> listComment(int no, int startPoint, int endPoint) throws Exception {
		List<CommentDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from ")
			   .append("  ( select a.*, row_number() ")
			   .append("  over (order by comment_no desc) ")
			   .append("  as num ")
			   .append("  from t17_comment a ")
			   .append("  where no = ? ) ")
			   .append(" where num between ? and ? ");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, no);
			pstmt.setInt(index++, startPoint);
			pstmt.setInt(index++, endPoint);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDomain comment = new CommentDomain();
				comment.setNo(rs.getInt("no"));
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setWriter(rs.getString("writer"));
				comment.setContent(rs.getString("content"));
				comment.setRegDate(rs.getTimestamp("reg_date"));
				list.add(comment);
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
	
	public void insertComment(CommentDomain comment)
			throws Exception {
		// t17_board 테이블에 데이터를 입력..
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into t17_comment( ");
			sql.append("	comment_no,  ");
			sql.append("	no,  ");
			sql.append("	writer,  ");
			sql.append("	content  ");
			sql.append(") values (  ");
			sql.append("	t17_comment_comment_no.nextval,  ");
			sql.append("   ?, ?, ?");
			sql.append(") ");

			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setInt(index++, comment.getNo());
			pstmt.setString(index++, comment.getWriter());
			pstmt.setString(index++, comment.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void deleteComment(int no, int commentNo) throws Exception { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			   .append("  from t17_comment ")
			   .append(" where no = ? and comment_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, no);
			pstmt.setInt(index++, commentNo);
			pstmt.executeUpdate();
			   
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	public void modifyComment(CommentDomain comment) throws Exception { //예외를 간접처리
		
		//멤버변수로 안두는 이유는 필요할때만 쓰고 버리기 위해
		//이 메소드에서만 살아있는 객체이기 때문에
		
		Connection con = null;	//연결객체
		PreparedStatement pstmt = null;
		
		try { //외부자원을 쓰는것은 무조건 예외처리가 필요
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update t17_comment")
			   .append("	set content = ?, ")
			   .append("		writer = ? ")
			   .append(" where no = ? and comment_no = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, comment.getContent());
			pstmt.setString(index++, comment.getWriter());
			pstmt.setInt(index++, comment.getNo());
			pstmt.setInt(index++, comment.getCommentNo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e; //나를 부른쪽으로 예외를 넘긴다.
		}finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		
	}	
	
}













