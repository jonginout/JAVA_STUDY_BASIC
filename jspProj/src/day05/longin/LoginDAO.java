package day05.longin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.ConnectionPool;

public class LoginDAO {

	// 로그인 처리
	public Login detailMember(String id, String pass) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from t17_member ")
			   .append(" where id = ? and pass = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, id);
			pstmt.setString(index++, pass);
			ResultSet rs = pstmt.executeQuery();
			// 결과에 대한 정보를 담는 ResultSet
			if(rs.next()) {
				Login login = new Login();
				login.setId(rs.getString("id"));
				login.setPass(rs.getString("pass"));
				login.setName(rs.getString("name"));
				return login;
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
	
}
