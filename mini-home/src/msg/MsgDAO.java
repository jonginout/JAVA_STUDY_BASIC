package msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionPool;
import common.Page;
import common.Search;

public class MsgDAO {

	public int listMsgCount(Search search) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String delField = "rec_delete";
		if(search.getField().equals("send_id")) {
			delField = "send_delete";
		}
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count("+search.getField()+") ")
			   .append("  from MSG ")
			   .append("where "+search.getField()+" = ? and "+delField+" = 'F' ");
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, search.getWord());
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
	
	public List<MsgDomain> listMsg(Search search, Page page) throws Exception {
		List<MsgDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		String delField = "rec_delete";
		if(search.getField().equals("send_id")) {
			delField = "send_delete";
		}
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			.append("    from (select rownum runum, a.*")
			.append("     from (select * ")
			.append("   from MSG ")
			.append("		where "+search.getField()+" = ? and "+delField+" = 'F' ")
			.append("		order by msg_no desc ) a ) ")
			.append("where runum between ? and ? ");
			
			
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, search.getWord());
			pstmt.setInt(index++, page.getBegin());
			pstmt.setInt(index++, page.getEnd());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MsgDomain msg = new MsgDomain();
				msg.setMsgNo(rs.getInt("msg_no"));
				msg.setSendId(rs.getString("send_id"));
				msg.setRecId(rs.getString("rec_id"));
				msg.setContent(rs.getString("content"));
				msg.setContent(rs.getString("content"));
				msg.setIsRead(rs.getString("is_read").charAt(0));
				msg.setSendDate(rs.getTimestamp("send_date"));
				list.add(msg);
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
	
	public void insertMsg(MsgDomain msg) throws Exception {
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		con = ConnectionPool.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into MSG( ")
			.append("	msg_no,  ")
			.append("	rec_id,  ")
			.append("	send_id,  ")
			.append("	content  ")
			.append(") values (  ")
			.append(" msg_no_sq.nextval, ")
			.append("   ?, ?, ? ")
			.append(") ");
		
		pstmt = con.prepareStatement(sql.toString());
		int index = 1;
		pstmt.setString(index++, msg.getRecId());
		pstmt.setString(index++, msg.getSendId());
		pstmt.setString(index++, msg.getContent());
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
	
	public MsgDomain detailMsg(int msgNo) throws Exception { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			   .append("  from MSG ")
			   .append("where msg_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, msgNo);
			ResultSet rs = pstmt.executeQuery();
			// 결과에 대한 정보를 담는 ResultSet
			if(rs.next()) {
				MsgDomain msg = new MsgDomain();
				msg.setMsgNo(rs.getInt("msg_no"));
				msg.setSendId(rs.getString("send_id"));
				msg.setRecId(rs.getString("rec_id"));
				msg.setContent(rs.getString("content"));
				msg.setContent(rs.getString("content"));
				msg.setIsRead(rs.getString("is_read").charAt(0));
				msg.setSendDate(rs.getTimestamp("send_date"));
				return msg;
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

	
	public void readMsg(int msgNo) throws Exception { //예외를 간접처리

		Connection con = null;	
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update MSG")
			   .append("	set is_read = 'Y' ")
			   .append(" where msg_no = ?");
		 
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, msgNo);
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
	
	
	
	public void deleteMsg(String field, int msgNo) throws Exception { //예외를 간접처리

		Connection con = null;	
		PreparedStatement pstmt = null;
		String delField = "rec_delete";
		if(field.equals("send_id")) {
			delField = "send_delete";
		}
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update MSG")
			   .append("	set "+delField+" = 'Y' ")
			   .append(" where msg_no = ?");
		 
			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, msgNo);
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
	
	
}
