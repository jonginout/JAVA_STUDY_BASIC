package diary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ConnectionPool;
import common.Page;

public class DiaryDAO {
	
	
	// board_no 로 homepage_no, category_no 를 찾는다. 
	public DiaryDomain searchBoard(int no) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println(no);
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("	select *	");
			sql.append("	from board	");
			sql.append("	where no = ?	");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				DiaryDomain diary = new DiaryDomain();
				
				diary.setNo(rs.getInt("no"));
				diary.setHomepageNo(rs.getInt("homepage_no"));
				diary.setCategoryNo(rs.getInt("category_no"));
				
				diary.setTitle(rs.getString("title"));
				diary.setWriter(rs.getString("writer"));
				diary.setContent(rs.getString("content"));
			
				diary.setRegDate(rs.getTimestamp("reg_date"));
				diary.setUpdateDate(rs.getTimestamp("update_date"));
				diary.setSecret(rs.getString("secret"));
				
				return diary;
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
	
	// 다이어리 글 insert
	public void insertDiary(DiaryDomain diary) throws Exception{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("	insert into  ");
			sql.append("	board(no, homepage_no, category_no, title, writer, content) 	");
			sql.append("    values(no_sq.nextval, ?, ?, ?, ?, ?)	 ");
			
			pstmt = con.prepareStatement(sql.toString());
		
			int index=1;
			pstmt.setInt(index++, diary.getHomepageNo());
			pstmt.setInt(index++, diary.getCategoryNo());
			pstmt.setString(index++, diary.getTitle());
			pstmt.setString(index++, diary.getWriter());
			pstmt.setString(index++, diary.getContent());
			
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
	
	//다이어리 리스트 
	public List<DiaryDomain> selectDiary(DiaryDomain diary) throws Exception {
		
		List<DiaryDomain> diaryList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("	select * 	");
			sql.append("	from board	");
			sql.append("	where homepage_no = ? and	");
			sql.append("		  category_no = ?	");
			sql.append("	order by no desc	");
			
			pstmt = con.prepareStatement(sql.toString());
			
			int index=1;
			pstmt.setInt(index++, diary.getHomepageNo());
			pstmt.setInt(index++, diary.getCategoryNo());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DiaryDomain diary2 = new DiaryDomain();
				
				diary2.setNo(rs.getInt("no"));
				diary2.setHomepageNo(rs.getInt("homepage_no"));
				diary2.setCategoryNo(rs.getInt("category_no"));
				
				diary2.setTitle(rs.getString("title"));
				diary2.setWriter(rs.getString("writer"));
				diary2.setContent(rs.getString("content"));
			
				diary2.setRegDate(rs.getTimestamp("reg_date"));
				diary2.setUpdateDate(rs.getTimestamp("update_date"));
				diary2.setSecret(rs.getString("secret"));
				
				diaryList.add(diary2);
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
		return diaryList;
	}
	
	//다이어리글 상세
		public DiaryDomain detailDiary(int no) throws Exception {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("	select * 	");
				sql.append("	from board	");
				sql.append("	where no = ? ");
				
				pstmt = con.prepareStatement(sql.toString());
				
				int index=1;
				pstmt.setInt(index++, no);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					DiaryDomain diary = new DiaryDomain();
					diary.setNo(rs.getInt("no"));
					diary.setHomepageNo(rs.getInt("homepage_no"));
					diary.setCategoryNo(rs.getInt("category_no"));
					
					diary.setTitle(rs.getString("title"));
					diary.setWriter(rs.getString("writer"));
					diary.setContent(rs.getString("content"));
				
					diary.setRegDate(rs.getTimestamp("reg_date"));
					diary.setUpdateDate(rs.getTimestamp("update_date"));
					diary.setSecret(rs.getString("secret"));
					
					return diary;
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
		
		// 다이어리글 삭제
		public void deleteDiary(int no) throws Exception{
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("	delete from	board	");
				sql.append("	where no = ?	");
				
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
		
		public void modifyDiary(DiaryDomain diary) throws Exception{
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("	update board	");
				sql.append("	set title = ?, 	");
				sql.append("		content = ? 	");
				sql.append("	where no = ? 	");
	
				pstmt = con.prepareStatement(sql.toString());
				int index = 1;
				pstmt.setString(index++, diary.getTitle());
				pstmt.setString(index++, diary.getContent());
				pstmt.setInt(index++, diary.getNo());
				
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
		
		public List<DiaryDomain> selectDiaryPageList(Page page) throws Exception{
			Connection con = null;
			PreparedStatement pstmt = null;
			List<DiaryDomain> list = new ArrayList<>();
			
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("	select * from	");
				sql.append("	(select rownum rnum, a.*	");
				sql.append("	from 	");
				sql.append("		(select * from board 	");
				sql.append("		where homepage_no = ? and category_no = ?	");
				sql.append("		order by no desc) a	)");
				sql.append("	 where rnum between ? and ?	");
				
				pstmt = con.prepareStatement(sql.toString());
				int index = 1;
				pstmt.setInt(index++, page.getHomepageNo());
				pstmt.setInt(index++, page.getCategoryNo());
				pstmt.setInt(index++, page.getBegin());
				pstmt.setInt(index++, page.getEnd());
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					DiaryDomain diary = new DiaryDomain();
					
					diary.setNo(rs.getInt("no"));
					diary.setHomepageNo(rs.getInt("homepage_no"));
					diary.setCategoryNo(rs.getInt("category_no"));
					
					diary.setTitle(rs.getString("title"));
					diary.setWriter(rs.getString("writer"));
					diary.setContent(rs.getString("content"));
				
					diary.setRegDate(rs.getTimestamp("reg_date"));
					diary.setUpdateDate(rs.getTimestamp("update_date"));
					diary.setSecret(rs.getString("secret"));
					
					list.add(diary);
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
		
		public int selectDiaryListCount(DiaryDomain diary) throws Exception{
			Connection con = null;
			PreparedStatement pstmt = null;
		
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("	select count(*)	");
				sql.append("	from board	");
				sql.append("	where homepage_no = ?	");
				sql.append("	  and category_no = ?	");
				
				pstmt = con.prepareStatement(sql.toString());
				int index = 1;
				pstmt.setInt(index++, diary.getHomepageNo());
				pstmt.setInt(index++, diary.getCategoryNo());
				
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
}
