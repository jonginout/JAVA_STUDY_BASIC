package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.ConnectionPool;

public class MemberDAO {
	// Login detailMember(Login) 
	public Member detailMember(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		Member user = new Member();
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from t17_member ")
			   .append(" where id = ? ");
			System.out.println(con);
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setId(id);
				user.setPass(rs.getString("pass"));
				user.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		
		return user;
	}
}
