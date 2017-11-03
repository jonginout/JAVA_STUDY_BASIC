package vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.ConnectionPool;

public class VoteDAO {

	public void insertLikeVote(VoteDomain vote)
			throws Exception {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into vote_like( ");
			sql.append("	no,  ");
			sql.append("	member_no  ");
			sql.append(") values (  ");
			sql.append("	?, ?  ");
			sql.append(")");

			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			
			pstmt.setInt(index++, vote.getNo());
			pstmt.setInt(index++, vote.getMemberNo());

			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void insertHateVote(VoteDomain vote)
			throws Exception {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into vote_hate( ");
			sql.append("	no,  ");
			sql.append("	member_no  ");
			sql.append(") values (  ");
			sql.append("	?, ?  ");
			sql.append(")");

			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			
			pstmt.setInt(index++, vote.getNo());
			pstmt.setInt(index++, vote.getMemberNo());

			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	
	public int countLikeVote(VoteDomain vote)
			throws Exception {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from vote_like where no = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, vote.getNo());
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
		return 0;
	}
	
	public int countHateVote(VoteDomain vote)
			throws Exception {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from vote_hate where no = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, vote.getNo());
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
		return 0;
	}
	
	public int checkLikeVote(VoteDomain vote)
			throws Exception {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from vote_like where no = ? and member_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, vote.getNo());
			pstmt.setInt(index++, vote.getMemberNo());
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
		return -1;
	}
	
	public int checkHateVote(VoteDomain vote)
			throws Exception {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from vote_hate where no = ? and member_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, vote.getNo());
			pstmt.setInt(index++, vote.getMemberNo());
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
		return -1;
	}
}
