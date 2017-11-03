/*
 *   insert 
 *   delete
 *   detail
 *   modify
 * 	 list
 */
package photoGallary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.CategoryDomain;
import common.ConnectionPool;
import common.Page;
import fileGallary.CommentDomain;

public class GallaryDAO {
	
	//게시글 삽입
	public void insertBoard(BoardDomain board)
			throws Exception {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into board( ");
			sql.append("	no,  ");
			sql.append("	homepage_no,  ");
			sql.append("	category_no,  ");
			sql.append("	title,  ");
			sql.append("	writer,  ");
			sql.append("	content  ");
			sql.append(") values (  ");
			sql.append("	?,  ");
			sql.append("   ?, ?, ?, ?, ? ");
			sql.append(") ");

			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			
			pstmt.setInt(index++, board.getNo());
			pstmt.setInt(index++, board.getHomepageNo());
			pstmt.setInt(index++, board.getCategoryNo());
			pstmt.setString(index++, board.getTitle());
			pstmt.setString(index++, board.getWriter());
			pstmt.setString(index++, board.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	//게시글 상세
			public CommentDomain detailComment(int commentNo) throws Exception {
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = ConnectionPool.getConnection();
					StringBuffer sql = new StringBuffer();
					sql.append("select * ")
					   .append("  from board_comment ")
					   .append(" where comment_no = ? ");
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setInt(1, commentNo);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						CommentDomain comment = new CommentDomain();
						comment.setNo(rs.getInt("no"));
						comment.setCommentNo(commentNo);
						comment.setWriter(rs.getString("writer"));
						comment.setContent(rs.getString("content"));
						comment.setRegDate(rs.getTimestamp("reg_date"));
						return comment;
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
	
	//카테고리 삽입
		public void insertCategory(CategoryDomain category)
				throws Exception {
		
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("insert into category( ");
				sql.append("	category_no,  ");
				sql.append("	category_group_no,  ");
				sql.append("	name,  ");
				sql.append("	homepage_no  ");
				sql.append(") values (  ");
				sql.append("  ?, ?, ?, ? ");
				sql.append(") ");

				pstmt = con.prepareStatement(sql.toString());
				
				int index = 1;
				
				pstmt.setInt(index++, category.getCategoryNo());
				pstmt.setInt(index++, category.getCategoryGroupNo());
				pstmt.setString(index++, category.getName());
				pstmt.setInt(index++, category.getHomepageNo());

				pstmt.executeUpdate();
				
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					pstmt.close();
				} catch (Exception e2) {}
				ConnectionPool.releaseConnection(con);
			}
		}
	
	//게시글 수정
	public void modifyBoard(BoardDomain board) 
			throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update board ")
			   .append("   set title = ?, ")
			   .append("       writer = ?, ")
			   .append("       content = ?, ")
			   .append("       update_date = sysdate")
			   .append(" where no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, board.getTitle());
			pstmt.setString(index++, board.getWriter());
			pstmt.setString(index++, board.getContent());
			pstmt.setInt(index++, board.getNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	//카테고리 수정
	public void modifyCategory(CategoryDomain category) 
			throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update category ")
			   .append("   set name = ? ")
			   .append(" where category_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setString(index++, category.getName());
			pstmt.setInt(index++, category.getCategoryNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	//댓글 수정
	public void modifyComment(photoGallary.CommentDomain comment) 
			throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update board_comment ")
			   .append("   set content = ?, ")
			   .append("       update_date = sysdate")
			   .append(" where comment_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			
			pstmt.setString(index++, comment.getContent());
			pstmt.setInt(index++, comment.getCommentNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {}
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	//게시글 삭제
	public void deleteBoard(int no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			   .append("  from board ")
			   .append(" where no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, no);
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
	
	//카테고리 삭제
		public void deleteCategory(int category_no) throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("delete ")
				   .append("  from category ")
				   .append(" where category_no = ? ");
				pstmt = con.prepareStatement(sql.toString());
				
				pstmt.setInt(1, category_no);
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
	
	//코멘트 삭제
	public void deleteComment(int commentNo) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			   .append("  from board_comment ")
			   .append(" where comment_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, commentNo);
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
	
	// 첨부된 파일 삭제
	public void deleteFile(int fileNo) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete ")
			   .append("  from board_file ")
			   .append(" where file_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, fileNo);
			
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
	
	//게시글 상세
	public BoardDomain detailBoard(int no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from board ")
			   .append(" where no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				BoardDomain board = new BoardDomain();
				board.setNo(no);
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				return board;
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
	
	// 게시글 목록
		public List<CategoryDomain> listCategory(int homepageNo, int categoryGroupNo) throws Exception {
			List<CategoryDomain> list = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("select * from category where homepage_no = ? and category_group_no = ?");
				
				pstmt = con.prepareStatement(sql.toString());
				int i = 1;
				
				pstmt.setInt(i++, homepageNo);
				pstmt.setInt(i++, categoryGroupNo);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					CategoryDomain category = new CategoryDomain();
					category.setCategoryGroupNo(categoryGroupNo);
					category.setHomepageNo(homepageNo);
					category.setCategoryNo(rs.getInt("category_no"));
					category.setName(rs.getString("name"));
					list.add(category);
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
	
	// 게시글 목록
	public List<BoardDomain> listBoard(int homepageNo, int categoryNo, Page page) throws Exception {
		List<BoardDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			/*sql.append("select * ")
			   .append("  from board ")
			   .append("  where homepage_no = ? ")
			   .append("  and category_no = ? ")
			   .append(" order by no desc ");
			*/
			
			sql.append("select * from (select rownum rnum, a.* from (select * from board where homepage_no = ? and category_no = ? order by no desc) a) where rnum between ? and ?");
			
			pstmt = con.prepareStatement(sql.toString());
			int i = 1;
			
			pstmt.setInt(i++, homepageNo);
			pstmt.setInt(i++, categoryNo);
			pstmt.setInt(i++, page.getBegin());
			pstmt.setInt(i++, page.getEnd());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDomain board = new BoardDomain();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setHomepageNo(homepageNo);
				board.setCategoryNo(categoryNo);
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setUpdateDate(rs.getTimestamp("update_date"));
				list.add(board);
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
	
	public int  listBoardCount(int homepageNo, int categoryNo) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from board where homepage_no = ? and category_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, homepageNo);
			pstmt.setInt(i++, categoryNo);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
	
	public int  listCommentCount(int no) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from board_comment where no = ?");
			pstmt = con.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, no);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
	
	//댓글 목록
	public List<CommentDomain> listComment(int no, Page page) throws Exception {
		List<CommentDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select * from (select rownum rnum, a.* from (select * from board_comment where no = ? order by no desc) a) where rnum between ? and ?");

			pstmt = con.prepareStatement(sql.toString());
			int i = 1;
			pstmt.setInt(i++, no);
			pstmt.setInt(i++, page.getBegin());
			pstmt.setInt(i++, page.getEnd());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CommentDomain comment = new CommentDomain();
				comment.setNo(rs.getInt("no"));
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setWriter(rs.getString("writer"));
				comment.setContent(rs.getString("content"));
				comment.setRegDate(rs.getTimestamp("reg_date"));
				comment.setUpdateDate(rs.getTimestamp("update_date"));
				list.add(comment);
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
	
	//댓글 삽입
	public void insertComment(photoGallary.CommentDomain comment)
			throws Exception {
		// t17_board 테이블에 데이터를 입력..
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into board_comment( ");
			sql.append("	comment_no,  ");
			sql.append("	no,  ");
			sql.append("	writer,  ");
			sql.append("	content  ");
			sql.append(") values (  ");
			sql.append("	comment_no_sq.nextval,  ");
			sql.append("   ?, ?, ? ");
			sql.append(") ");

			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setInt(index++, comment.getNo());
			pstmt.setString(index++, comment.getWriter());
			pstmt.setString(index++, comment.getContent());
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
	
	// 게시글 입력 시 다음 시퀀스 번호 주기
	public int detailBoardNo() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int no = -1;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select no_sq.nextval ")
			   .append("  from dual ");
			
			pstmt = con.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				no = rs.getInt(1);
				return no;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {}
			ConnectionPool.releaseConnection(con);
		}
		return no;
	}
	
	// 게시글 입력 시 다음 시퀀스 번호 주기
		public int detailCategoryNo() throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;
			int no = -1;
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select category_no_sq.nextval ")
				   .append("  from dual ");
				
				pstmt = con.prepareStatement(sql.toString());

				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					no = rs.getInt(1);
					return no;
				}
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					pstmt.close();
				} catch (Exception e) {}
				ConnectionPool.releaseConnection(con);
			}
			return no;
		}
	
	// 파일 업로딩
		public void insertFile(BoardFileDomain file, int no)
				throws Exception {
			// t17_board 테이블에 데이터를 입력..
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("insert into board_file( ");
				sql.append("	file_no,  ");
				sql.append("	no,  ");
				sql.append("	file_path,  ");
				sql.append("	ori_name,  ");
				sql.append("	system_name,  ");
				sql.append("	file_size  ");
				sql.append(") values (  ");
				sql.append("	file_no_sq.nextval,  ");
				sql.append("   ?, ?, ?, ?, ? ");
				sql.append(") ");

				pstmt = con.prepareStatement(sql.toString());
				
				int index = 1;
				pstmt.setInt(index++, no);
				pstmt.setString(index++, file.getFilePath());
				pstmt.setString(index++, file.getOriName());
				pstmt.setString(index++, file.getSystemName());
				pstmt.setLong(index++, file.getFileSize());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					pstmt.close();
				} catch (Exception e2) {}
				ConnectionPool.releaseConnection(con);
			}
		}
		
		// 파일 총 개수
		public int  listBoardFileCount() throws Exception{
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select count(*) from board_file");
				pstmt = con.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
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
		
		// 게시글 속의 파일 목록
		public List<BoardFileDomain> listBoardFile(int no) throws Exception {
			
			List<BoardFileDomain> list = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ConnectionPool.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select * ")
				   .append("  from board_file")
				   .append("  where no = ? ")
				   .append("  order by file_no desc");
				
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, no);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardFileDomain file = new BoardFileDomain();
					file.setFileNo(rs.getInt("file_no"));
					file.setNo(rs.getInt("no"));
					file.setFilePath(rs.getString("file_path"));
					file.setOriName(rs.getString("ori_name"));
					file.setSystemName(rs.getString("system_name"));
					file.setFileSize(rs.getLong("file_size"));
					list.add(file);
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
		
		
		
}












