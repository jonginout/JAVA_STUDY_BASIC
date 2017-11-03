package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quiz02DAO {

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql,id = "hr", pw="hr", url="localhost:1521:xe";
	
	public Quiz02DAO() {
		// TODO Auto-generated constructor stub
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+url,id, pw);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Quiz02VO detail(String id, String pw)
	{
		Quiz02VO res = null;
		sql = "select * from stud where id = ? and pw = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			ptmt.setString(2, pw);
			
			rs = ptmt.executeQuery();
			
			if(rs.next())
			{
				res = new Quiz02VO();
				res.setId(rs.getString("id"));
				res.setGender(rs.getString("gender"));
				res.setEmail(rs.getString("email"));
				res.setPw(rs.getString("pw"));
				res.setContent(rs.getString("content"));
				res.setHobby(rs.getString("hobby"));
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	public void close()
	{
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(ptmt!=null) try {ptmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
	}
	
}
