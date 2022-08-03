package co.edu.common;

import java.sql.SQLException;

public class MemberDAO extends DAO{
	
	//입력처리
	public void insertMember(MemberVO vo) {
		String sql ="insert into member(id,name,passwd,mail) values(?,?,?,?)";
		connect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getMail());
			
			int r = pstmt.executeUpdate();
			
			System.out.println(r+"건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
	}

	

}
