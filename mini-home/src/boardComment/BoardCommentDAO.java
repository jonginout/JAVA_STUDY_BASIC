package boardComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.Page;
import common.ConnectionPool;
import diary.DiaryDomain;

public class BoardCommentDAO {

	//파라미터 값은 board_no 여야 한다.
	public List<BoardCommentDomain> selectComment(int no) throws Exception{
		List<BoardCommentDomain> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" select * from board_comment  ");
			sql.append(" where no = ?  ");
			sql.append(" order by comment_no desc  ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardCommentDomain boardComment = new BoardCommentDomain();
				
				boardComment.setCommentNo(rs.getInt("comment_no"));
				boardComment.setContent(rs.getString("content"));
				boardComment.setNo(rs.getInt("no"));
				
				boardComment.setWriter(rs.getString("writer"));
				boardComment.setRegDate(rs.getTimestamp("reg_date"));
				boardComment.setUpdateDate(rs.getTimestamp("update_date"));
				
				list.add(boardComment);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}			
			ConnectionPool.releaseConnection(con);
		}
		
		return list;
	}
	
	public void insertComment(BoardCommentDomain comment) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" insert into board_comment( ");
			sql.append(" comment_no, no, writer, content) ");
			sql.append(" values(comment_no_sq.nextval, ?,?,?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			int index=1;
			pstmt.setInt(index++, comment.getNo());
			pstmt.setString(index++, comment.getWriter());
			pstmt.setString(index++, comment.getContent());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}			
			ConnectionPool.releaseConnection(con);
		}
	}
	
	// comment_no 가 파라미터 값이다
	public void deleteComment(int no) throws Exception{
		System.out.println(no);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" 	delete from	");
			sql.append(" 	board_comment	");
			sql.append(" 	where comment_no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}			
			ConnectionPool.releaseConnection(con);
		}
	}
	
	//comment_no 로 board_no를 찾는다.
	public int searchBoardNo(int no) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("	select no	");
			sql.append("	from board_comment	");
			sql.append("	where comment_no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("no");
			}
		
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}			
			ConnectionPool.releaseConnection(con);
		}
		
		return 0;
	}
	
	// commentNo로 content 를 가져온다
	public BoardCommentDomain searchCommentContent(int commentNo) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	select *	");
			sql.append("	from board_comment	");
			sql.append("	where comment_no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, commentNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BoardCommentDomain comment = new BoardCommentDomain();
				comment.setContent(rs.getString("content"));
				comment.setNo(rs.getInt("no"));
				
				return comment; 
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}			
			ConnectionPool.releaseConnection(con);
		}
		return null;
	}
	
	public void modifyComment(BoardCommentDomain comment) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("	update board_comment	");
			sql.append("	set content = ?  	");
			sql.append("	where comment_no = ? 	");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, comment.getContent());
			pstmt.setInt(index++, comment.getCommentNo());
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public List<BoardCommentDomain> commentListBoard(Page page) throws Exception{
		List<BoardCommentDomain> commentList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
		
			sql.append("	select * from	");
			sql.append("		(select rownum rnum, a.*	");
			sql.append("		from 	");
			sql.append("			(select *	");
			sql.append("			from board_comment	");
			sql.append("			where no = ?	");
			sql.append("			order by comment_no desc) a)	");
			sql.append("	where rnum between ? and ?	");
		
			pstmt = con.prepareStatement(sql.toString());
			
			int index=1;
			pstmt.setInt(index++, page.getNo());
			pstmt.setInt(index++, page.getBegin());
			pstmt.setInt(index++, page.getEnd());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardCommentDomain boardComment = new BoardCommentDomain();
				
				boardComment.setCommentNo(rs.getInt("comment_no"));
				boardComment.setContent(rs.getString("content"));
				boardComment.setNo(rs.getInt("no"));
				
				boardComment.setWriter(rs.getString("writer"));
				boardComment.setRegDate(rs.getTimestamp("reg_date"));
				boardComment.setUpdateDate(rs.getTimestamp("update_date"));
				
				commentList.add(boardComment);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
			ConnectionPool.releaseConnection(con);
		}
		return commentList;
	}
	
	
	//board_no 로 댓글의 수를 파악
	public int listCommentCount(int no) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	select count(*)	");
			sql.append("	from board_comment	");
			sql.append("	where no =?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
	
		} catch (Exception e) {
				throw e;
			} finally {
				try {
					pstmt.close();
				} catch (Exception e2) {
					throw e2;
				}
				ConnectionPool.releaseConnection(con);
		}
		return 0;
	}
	
}