/*
 *   insert 
 *   delete
 *   detail
 *   modify
 * 	 list
 */
package com.jongin.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jongin.common.ConnectionPool;
import com.jongin.common.MyAppSqlConfig;

public class BoardMapper {
	
	private static SqlSession session;
	private final static String NS = "board.BoardMapper.";
	
	public BoardMapper() {
		session = MyAppSqlConfig.getSqlSessionInstance();
		if(session==null) {System.out.println("DB연결실패");}
	}
	
	public Integer getBoardNoSequence() throws Exception {
		return session.selectOne(NS+"getBoardNoSequence");
	}
	
	public void insertBoard(BoardDomain board, int nextVal){
		board.setNo(nextVal);
		session.insert(NS+"insertBoard", board);
	}
	
	public void insertFile(FileDomain file, int nextVal)
			throws Exception {
		// t17_board 테이블에 데이터를 입력..
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into t17_board_file(  ");
			sql.append("	file_no,  ");
			sql.append("	no,  ");
			sql.append("	file_path,  ");
			sql.append("	ori_name,  ");
			sql.append("	system_name,  ");
			sql.append("	file_size  ");
			sql.append(") values (  ");
			sql.append("	s17_board_file_no.nextval,  ");
			sql.append("   ?, ?, ?, ?, ? ");
			sql.append(") ");

			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setInt(index++, nextVal);
			pstmt.setString(index++, file.getFilePath());
			pstmt.setString(index++, file.getOriName());
			pstmt.setString(index++, file.getSystemName());
			pstmt.setLong(index++, file.getFileSize());
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
	
	
	public void modifyBoard(BoardDomain board) {
		session.update(NS+"modifyBoard", board);
	}
	
	public void deleteBoard(int no) throws Exception { 
		session.delete(NS+"deleteBoard", no);
	}
	
	public void deleteFile(int fileNo) throws Exception { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			.append("  from t17_board_file ")
			.append(" where file_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, fileNo);
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
	
	public List<FileDomain> listFile(int no) throws Exception {
		List<FileDomain> files = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			.append("  from t17_board_file ")
			.append(" where no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FileDomain file = new FileDomain();
				file.setNo(no);
				file.setFileNo(rs.getInt("file_no"));
				file.setFilePath(rs.getString("file_path"));
				file.setOriName(rs.getString("ori_name"));
				file.setSystemName(rs.getString("system_name"));
				file.setFileSize(rs.getInt("file_size"));
				file.setRegDate(rs.getTimestamp("reg_date"));
				files.add(file);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return files;
	}
	
	public BoardDomain detailBoard(int no) {
		return session.selectOne(NS+"getBoardDetail", no);
	}
	
	public Integer listCount(String search, String field) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(no) ")
			   .append("  from t17_board ")
			   .append("  where "+field+" like '%"+search+"%' ");
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
	
	public List<BoardDomain> listBoard(int startPoint, int endPoint, String search, String field) throws Exception {
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
			   .append("  from t17_board a ")
			   .append("  where "+field+" like '%"+search+"%' ) ")
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
	
	public CommentDomain detailComment(int comment_no) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from t17_comment ")
			   .append(" where comment_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, comment_no);
			ResultSet rs = pstmt.executeQuery();
			// 결과에 대한 정보를 담는 ResultSet
			if(rs.next()) {
				CommentDomain comment = new CommentDomain();
				comment.setNo(rs.getInt("no"));
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setWriter(rs.getString("writer"));
				comment.setContent(rs.getString("content"));
				comment.setRegDate(rs.getTimestamp("reg_date"));
				return comment;
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













