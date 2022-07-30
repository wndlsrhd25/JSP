package bbs;


import java.sql.SQLException;
import java.util.ArrayList;

public class BbsDAO extends DAO {

	public String getDate() {
		try {
			connect();
			String sql = "SELECT SYSDATE FROM DUAL;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ""; // 데이터베이스 오류
	}

	public int getNext() {
		// 게시글의 마지막 번호를 가져와서 더하기 1을 하면 그다음 번호가 됨
		try {
			connect();
			String sql = "select bbsId FROM BBS ORDER BY bbsId desc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // 첫 번째 게시물인 경우

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1; // 데이터베이스 오류
	}

	public int write(String bbsTitle, String userId, String bbsContent) {
		String sql = "insert into BBS (bbsId,bbsTitle,user_id,bbsContent,bbsAvailable) values (?,?,?,?,?)";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userId);
			pstmt.setString(4, bbsContent);
			pstmt.setInt(5, 1);

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1; // 데이터베이스 오류
	}

	public ArrayList<Bbs> getList(int pageNumber) {
		// 게시글의 마지막 번호를 가져와서 더하기 1을 하면 그다음 번호가 됨
		connect();
		String sql = "SELECT * FROM (SELECT * FROM BBS ORDER BY BBSID DESC) WHERE BBSID < ?";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsId(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserId(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailabe(rs.getInt(6));

				list.add(bbs);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list; // 데이터베이스 오류
	}

	public boolean nextPage(int pageNumber) {
		// 게시글의 마지막 번호를 가져와서 더하기 1을 하면 그다음 번호가 됨
		connect();
		String sql = "SELECT * FROM (SELECT * FROM BBS ) WHERE BBSID < ? AND BBSAVAILABLE = 1";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				//다음페이지로 넘어갈 수 잇음
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//넘어갈 수 없음
		return false;
	}

}
