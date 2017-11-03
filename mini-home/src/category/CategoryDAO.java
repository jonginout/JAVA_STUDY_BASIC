package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionPool;
import common.Page;
import diary.DiaryDomain;

public class CategoryDAO {
	
	//Category_no 넣어서 group_no를 받아야한다.
	public int searchCategoryGroupNo(int categoryNo) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	select category_group_no	");
			sql.append("	from category	");
			sql.append("	where category_no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, categoryNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
	
	public List<CategoryDomain> selectCategoryPageList(Page page) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		List<CategoryDomain> list = new ArrayList<>();
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("	select * from	");
			sql.append("	(select rownum rnum, a.*	");
			sql.append("	from 	");
			sql.append("		(select * from category 	");
			sql.append("		where homepage_no = ? and category_group_no = ?	");
			sql.append("		order by category_no ) a	)");
			sql.append("	 where rnum between ? and ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, page.getHomepageNo());
			pstmt.setInt(index++, page.getCategoryNo());
			pstmt.setInt(index++, page.getBegin());
			pstmt.setInt(index++, page.getEnd());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryDomain category = new CategoryDomain();
				
				category.setCategoryNo(rs.getInt("category_no"));
				category.setCategoryGroupNo(rs.getInt("category_group_no"));
				category.setHomepageNo(rs.getInt("homepage_no"));
				category.setName(rs.getString("name"));
				
				list.add(category);
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
	
	public int selectCategoryListCount(CategoryDomain category) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	select count(*)	");
			sql.append("	from category	");
			sql.append("	where homepage_no = ?	");
			sql.append("	  and category_group_no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, category.getHomepageNo());
			pstmt.setInt(index++, category.getCategoryGroupNo());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
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
	
	//카테고리 그룹이 3인것을 리스트에 담는다
	public List<CategoryDomain> selectCategory(int homepageNo, int categoryGroupNo) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<CategoryDomain> list = new ArrayList<>();
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("	select *	");
			sql.append("	from category	");
			sql.append("	where category_group_no = ?	");
			sql.append("	       and homepage_no = ?	");
			sql.append("	order by category_no	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, categoryGroupNo);
			pstmt.setInt(2, homepageNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryDomain category = new CategoryDomain();
				
				category.setCategoryNo(rs.getInt("category_no"));
				category.setCategoryGroupNo(rs.getInt("category_group_no"));
				category.setHomepageNo(rs.getInt("homepage_no"));
				category.setName(rs.getString("name"));
				
				list.add(category);
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
	
	//homepageNo와 categoryGroupNo를 이용해서 insert 한다.
	public void insertCategory(CategoryDomain category) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("	insert into 	");
			sql.append("	category(category_no, category_group_no, homepage_no, name)	");
			sql.append("	values	");
			sql.append("	(category_no_sq.nextval, ?, ?, ?)	");
			
			pstmt = con.prepareStatement(sql.toString());
			int index=1;
			pstmt.setInt(index++, category.getCategoryGroupNo());
			pstmt.setInt(index++, category.getHomepageNo());
			pstmt.setString(index++, category.getName());
			
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
	
	// categoryNo 를 통해서 update문 작성
	public void modifyCategory(CategoryDomain category) throws Exception{ 
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	update category 	");
			sql.append("	set name = ? 	");
			sql.append("	where category_no = ? 	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, category.getName());
			pstmt.setInt(2, category.getCategoryNo());
			
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
	
	public CategoryDomain searchCategory(int categoryNo) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	select *	");
			sql.append("	from category	");
			sql.append("	where category_no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, categoryNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				CategoryDomain category = new CategoryDomain();
				
				category.setCategoryNo(rs.getInt("category_no"));
				category.setCategoryGroupNo(rs.getInt("category_group_no"));
				category.setHomepageNo(rs.getInt("homepage_no"));
				category.setName(rs.getString("name"));
				
				return category;
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
	
	public void deleteCategory(int categoryNo) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	delete from	");
			sql.append("	category	");
			sql.append("	where category_no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, categoryNo);
			
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
	}
		