package friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionPool;
import common.Page;

public class FriendDAO {
	
	public boolean FriendCheck(String ownerId, String customerId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(member_id) ")
			   .append("  from FRIEND ")
			   .append("where member_id = ? and friend_member_id = ?")
			   .append(" and status = 'ACTIVE' ");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, ownerId);
			pstmt.setString(index++, customerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 첫번째 컬럼에 있는거 반환해라
				int chk = rs.getInt(1);
				if(chk>0) {
					result = true; 
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return result;
	}
	
	public int listFriendCount(String memberId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(member_id) ")
			   .append("  from FRIEND ")
			   .append("where member_id = ? ");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, memberId);
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
	
	public List<FriendDomain> listFriend(Page page, String memberId) throws Exception {
		List<FriendDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			.append("    from (select rownum runum, a.*")
			.append("     from (select * ")
			.append("   from FRIEND ")
			.append("		where member_id = ? ) a ) ")
			.append("where runum between ? and ? ");
			
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, memberId);
			pstmt.setInt(index++, page.getBegin());
			pstmt.setInt(index++, page.getEnd());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendDomain friend = new FriendDomain();
				friend.setMemberId(rs.getString("member_id"));
				friend.setFriendMemberId(rs.getString("friend_member_id"));
				friend.setStatus(rs.getString("status"));
				list.add(friend);
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
	
	//친구 신청
	public void insertFriend(FriendDomain friend) throws Exception {
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		con = ConnectionPool.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into friend( ")
			.append("	member_id,  ")
			.append("	friend_member_id,  ")
			.append("	status  ")
			.append(") values (  ")
			.append("   ?, ?, ? ")
			.append(") ");
		
		pstmt = con.prepareStatement(sql.toString());
		int index = 1;
		pstmt.setString(index++, friend.getMemberId());
		pstmt.setString(index++, friend.getFriendMemberId());
		pstmt.setString(index++, friend.getStatus());
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
	
	public void modifyFriend(FriendDomain friend) throws Exception { //예외를 간접처리
		
		//active
		
		Connection con = null;	//연결객체
		PreparedStatement pstmt = null;
		
		try { //외부자원을 쓰는것은 무조건 예외처리가 필요
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update friend")
			   .append("	set status = ? ")
			   .append(" where member_id = ? and friend_member_id = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, friend.getStatus());
			pstmt.setString(index++, friend.getMemberId());
			pstmt.setString(index++, friend.getFriendMemberId());
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
	

	public void deleteFriend(String memberId, String friendMemberId) throws Exception { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			   .append("  from friend ")
			   .append(" where member_id = ? and friend_member_id = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, memberId);
			pstmt.setString(index++, friendMemberId);
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
	
	public List<FriendDomain> listFriend(String memberId) throws Exception {
		List<FriendDomain> friendList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
				.append("  from FRIEND ")
				.append(" where member_id = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, memberId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendDomain friend = new FriendDomain();
				friend.setMemberId(rs.getString("member_id"));
				friend.setFriendMemberId(rs.getString("friend_member_id"));
				friend.setStatus(rs.getString("status"));
				friendList.add(friend);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return friendList;
	}
	

	
}
