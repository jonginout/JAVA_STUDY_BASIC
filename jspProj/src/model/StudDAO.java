package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudDAO {

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql,id = "hr", pw="hr", url="localhost:1521:xe";
	
	public StudDAO() {
		// TODO Auto-generated constructor stub
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+url,id, pw);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> banlist(){
		ArrayList<String> res = new ArrayList<>();
		sql = "select distinct ban from stud order by ban";
		
		
		try {
			ptmt = con.prepareStatement(sql);
			
			
			rs = ptmt.executeQuery();
			
			while(rs.next())
			{
				res.add(rs.getString("ban"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	
	public ArrayList<StudVO> list(String ban)
	{
		ArrayList<StudVO> res = new ArrayList<>();
		sql = "select * from stud ";
		
		if(ban!=null)
			sql += " where ban = ?";
		
		sql += " order by id";
		
		//System.out.println(sql);
		
		try {
			ptmt = con.prepareStatement(sql);
			
			if(ban!=null)
				ptmt.setString(1, ban);
			rs = ptmt.executeQuery();
			
			while(rs.next())
			{
				StudVO vo = new StudVO();
				vo.setId(rs.getString("id"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setBan(rs.getString("ban"));
				
				
				res.add(vo);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public StudVO detail(String id)
	{
		StudVO res = null;
		sql = "select * from stud where id = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			
			rs = ptmt.executeQuery();
			
			if(rs.next())
			{
				res = new StudVO();
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
	
	
	public void insert(StudVO vo)
	{
		sql = "insert into stud (id, pw, email, gender, hobby, content) "
				+ "values (?,?,?,?,?,?)";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.id);
			ptmt.setString(2, vo.pw);
			ptmt.setString(3, vo.email);
			ptmt.setString(4, vo.gender);
			ptmt.setString(5, vo.hobby);
			ptmt.setString(6, vo.content);
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void delete(String id)
	{
		sql = "delete from stud where id = ?";
		//System.out.println(sql);
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	
	public void modify(StudVO vo)
	{
		sql = "update stud set pw =?, email = ?, gender =?, hobby =?, content =? where id =? ";
		
		try {
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, vo.pw);
			ptmt.setString(2, vo.email);
			ptmt.setString(3, vo.gender);
			ptmt.setString(4, vo.hobby);
			ptmt.setString(5, vo.content);
			ptmt.setString(6, vo.id);
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void close()
	{
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(ptmt!=null) try {ptmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
	}
	
}
