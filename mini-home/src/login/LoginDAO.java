package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.ConnectionPool;

public class LoginDAO {

	// 로그인 처리
	public Login loginMember(String id, String pw) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from member ")
			   .append(" where id = ? and pw = ?");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, id);
			pstmt.setString(index++, pw);
			ResultSet rs = pstmt.executeQuery();
			// 결과에 대한 정보를 담는 ResultSet
			if(rs.next()) {
				Login login = new Login();
				login.setMemberNo(rs.getInt("member_no"));
				login.setId(rs.getString("id"));
				login.setPw(rs.getString("pw"));
				login.setName(rs.getString("name"));
				login.setBirth(rs.getString("birth"));
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
