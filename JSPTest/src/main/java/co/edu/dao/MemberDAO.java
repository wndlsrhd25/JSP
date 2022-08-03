package co.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import co.edu.vo.MemberVO;




public class MemberDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	Statement stmt;
	
	public void connect() {
		try {
			InitialContext ic = new InitialContext();
			//톰캣은 가상메모리?가 java:comp/env 이렇게
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if(rs != null) 
				rs.close();
			if(pstmt != null) 
				pstmt.close();
			if(conn != null) 
				conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	//입력
		public void insertMember(MemberVO vo) {
			String sql ="insert into test_member(id,passwd,name,address) values(?,?,?,?)";
			connect();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPasswd());
				pstmt.setString(3, vo.getName());
				pstmt.setString(4, vo.getAddress());
				
				int r = pstmt.executeUpdate();
				
				System.out.println(r+"건 입력");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
		}
	
		
		//아이디 조회
		public MemberVO selectId(String id) {
			String sql ="select * from test_member where id = '"+id+"'";
			connect();
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			
				if(rs.next()) {
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setPasswd(rs.getString("passwd"));
					vo.setName(rs.getString("name"));
					vo.setAddress(rs.getString("address"));
					
					return vo;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
			return null;
		}
		
		
	
		
	
}
