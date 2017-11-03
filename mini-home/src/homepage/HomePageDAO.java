package homepage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionPool;
import common.Page;
import common.Search;

public class HomePageDAO {
	
	public void visitsUpHomePage(int homepage_no) throws Exception { //예외를 간접처리

		Connection con = null;	
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update HOMEPAGE")
			   .append("	set visits = visits + 1  ")
			   .append(" where homepage_no = ?");

			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, homepage_no);
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
	
	public int listHomePageCount(Search search) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) ")
			   .append("  from HOMEPAGE ")
			   .append("where "+search.getField()+" like '%"+search.getWord()+"%' ");
			
			System.out.println(search.toString());

			pstmt = con.prepareStatement(sql.toString());
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
	
	public void destroyHomePage(int homepageNo) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			   .append("  from HOMEPAGE ")
			   .append(" where homepage_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, homepageNo);
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			ConnectionPool.releaseConnection(con);
		}

	}
	
	public void fileDeleteHomePage(int homepageNo, String field) throws Exception { //예외를 간접처리
		
		Connection con = null;	
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update HOMEPAGE")
			.append("	set "+field+" = '' ")
			.append(" where homepage_no = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, homepageNo);
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
	
	public void modifyHomePage(HomePageDomain homePage) throws Exception { //예외를 간접처리

		Connection con = null;	
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update HOMEPAGE")
			   .append("	set bgm = ?, ")
			   .append("	    profile = ?, ")
			   .append("	    background_img = ?, ")
			   .append("	    title = ?, ")
			   .append("	    introduce = ?, ")
			   .append("	    open_range = ?, ")
			   .append("	    photo_gallary_use_yn = ?, ")
			   .append("	    file_gallary_use_yn = ?, ")
			   .append("	    guest_book_use_yn = ?, ")
			   .append("	    diary_use_yn = ? ")
			   .append(" where homepage_no = ?");
		 
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			String photoGallaryUseYn = homePage.getPhotoGallaryUseYn() ? "Y" : "F";
			String fileGallaryUseYn = homePage.getFileGallaryUseYn() ? "Y" : "F";
			String guestBookUseYn = homePage.getGuestBookUseYn() ? "Y" : "F";
			String diaryUseYn = homePage.getDiaryUseYn() ? "Y" : "F";
			pstmt.setString(index++, homePage.getBgm());
			pstmt.setString(index++, homePage.getProfile());
			pstmt.setString(index++, homePage.getBackgroundImg());
			pstmt.setString(index++, homePage.getTitle());
			pstmt.setString(index++, homePage.getIntroduce());
			pstmt.setString(index++, homePage.getOpenRange());
			pstmt.setString(index++, photoGallaryUseYn);
			pstmt.setString(index++, fileGallaryUseYn);
			pstmt.setString(index++, guestBookUseYn);
			pstmt.setString(index++, diaryUseYn);
			pstmt.setInt(index++, homePage.getHomepageNo());
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
	
	public HomePageDomain detailHomePage(int homepageNo) throws Exception { 
			
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select * ")
				   .append("  from HOMEPAGE ")
				   .append(" where homepage_no = ? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, homepageNo);
				ResultSet rs = pstmt.executeQuery();
				// 결과에 대한 정보를 담는 ResultSet
				if(rs.next()) {
					HomePageDomain homePage = new HomePageDomain();
					homePage.setHomepageNo(homepageNo);
					homePage.setId(rs.getString("id"));
					homePage.setBgm(rs.getString("bgm"));
					homePage.setProfile(rs.getString("profile"));
					homePage.setBackgroundImg(rs.getString("background_img"));
					homePage.setIntroduce(rs.getString("introduce"));
					homePage.setTitle(rs.getString("title"));
					homePage.setVisits(rs.getInt("visits"));
					homePage.setOpenRange(rs.getString("open_range"));
					homePage.setPhotoGallaryUseYn(rs.getString("photo_gallary_use_yn").charAt(0));
					homePage.setGuestBookUseYn(rs.getString("guest_book_use_yn").charAt(0));
					homePage.setDiaryUseYn(rs.getString("diary_use_yn").charAt(0));
					homePage.setFileGallaryUseYn(rs.getString("file_gallary_use_yn").charAt(0));
					return homePage;
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
	
	
	public List<HomePageDomain> listHomePage(String id) throws Exception {
		List<HomePageDomain> homePageList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
				.append("  from HOMEPAGE ")
				.append(" where id = ? ");

			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HomePageDomain homePage = new HomePageDomain();
				homePage.setHomepageNo(rs.getInt("homepage_no"));
				homePage.setId(id);
				homePage.setBgm(rs.getString("bgm"));
				homePage.setProfile(rs.getString("profile"));
				homePage.setBackgroundImg(rs.getString("background_img"));
				homePage.setIntroduce(rs.getString("introduce"));
				homePage.setTitle(rs.getString("title"));
				homePage.setVisits(rs.getInt("visits"));
				homePage.setOpenRange(rs.getString("open_range"));
				homePage.setPhotoGallaryUseYn(rs.getString("photo_gallary_use_yn").charAt(0));
				homePage.setGuestBookUseYn(rs.getString("guest_book_use_yn").charAt(0));
				homePage.setDiaryUseYn(rs.getString("diary_use_yn").charAt(0));
				homePage.setFileGallaryUseYn(rs.getString("file_gallary_use_yn").charAt(0));
				homePageList.add(homePage);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return homePageList;
	}

	//홈페이지 검색 시
		public List<HomePageDomain> searchListHomePage(Page page, Search search) throws Exception {
			List<HomePageDomain> homePageList = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select * ")
					.append("    from (select rownum runum, a.*")
					.append("     from (select * ")
					.append("   from HOMEPAGE ")
					.append("		where "+search.getField()+" like '%"+search.getWord()+"%' ")
					.append("    order by homepage_no desc)  a)")
					.append("where runum between ? and ? ");
				
				// 검색을 한거에서 1~5 순서 중요

				
				System.out.println(page.getBegin());
				System.out.println(page.getEnd());
				
				pstmt = con.prepareStatement(sql.toString());
				int index = 1;
				pstmt.setInt(index++, page.getBegin());
				pstmt.setInt(index++, page.getEnd());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					HomePageDomain homePage = new HomePageDomain();
					homePage.setHomepageNo(rs.getInt("homepage_no"));
					homePage.setId(rs.getString("id"));
					homePage.setBgm(rs.getString("bgm"));
					homePage.setProfile(rs.getString("profile"));
					homePage.setBackgroundImg(rs.getString("background_img"));
					homePage.setIntroduce(rs.getString("introduce"));
					homePage.setTitle(rs.getString("title"));
					homePage.setVisits(rs.getInt("visits"));
					homePage.setPhotoGallaryUseYn(rs.getString("photo_gallary_use_yn").charAt(0));
					homePage.setGuestBookUseYn(rs.getString("guest_book_use_yn").charAt(0));
					homePage.setDiaryUseYn(rs.getString("diary_use_yn").charAt(0));
					homePage.setFileGallaryUseYn(rs.getString("file_gallary_use_yn").charAt(0));
					homePageList.add(homePage);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					pstmt.close();
				} catch (Exception e) {}
				ConnectionPool.releaseConnection(con);
			}
			return homePageList;
		}
	
	
	public void insertHomePage(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
/*		hompage_no	id	bgm	profile	background_img	introduce	title	
		visits	photo_gallary_use_yn	guest_book_use_yn	diary_use_yn	
		file_gallary_use_yn*/

		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into HOMEPAGE ")
				.append(" (HOMEPAGE_NO, id, bgm, profile, background_img, title) ")
				.append(" values ")
				.append(" (HOMEPAGE_NO_SQ.NEXTVAL, ?, null, null, null, ?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			String title = id+"님의 홈페이지 입니다.";
			pstmt.setString(index++, id);
			pstmt.setString(index++, title);
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
	
}
