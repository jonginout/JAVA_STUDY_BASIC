package signin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.ConnectionPool;

public class SigninDAO {

	public void insertMember(Signin signin) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into member( ");
			sql.append("	member_no,  ");
			sql.append("	id,  ");
			sql.append("	pw,  ");
			sql.append("	name,  ");
			sql.append("	birth  ");
			sql.append(") values (  ");
			sql.append("   MEMBER_NO_SQ.NEXTVAL, ");
			sql.append("   ?, ?, ?, ? ");
			sql.append(") ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, signin.getId());
			pstmt.setString(index++, signin.getPw());
			pstmt.setString(index++, signin.getName());
			pstmt.setString(index++, signin.getBirth());
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
	
	public Boolean idCheck(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		Boolean check = false;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select count(id)");
			sql.append(" from member ");
			sql.append(" where id = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int count = rs.getInt("count(id)");
				if (count>0) {
					check = true;
				}
				return check;
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
}
