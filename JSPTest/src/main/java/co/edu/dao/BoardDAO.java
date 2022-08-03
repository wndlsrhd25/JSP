package co.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import co.edu.vo.BoardVO;
import co.edu.vo.CartVO;
import co.edu.vo.Criteria;


public class BoardDAO {


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
	
	// 등록.
		public void insertBoard(BoardVO vo) {

			String sql = "insert into test_board values(board_SEQ.NEXTVAL,?,?,?,sysdate,0)";
			connect();
			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getWriter());
				pstmt.setString(3, vo.getContent());

				int rs = pstmt.executeUpdate();
				System.out.println(rs + "건 입력");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}

		}
		
		// 조회수 증가
		public void setCnt(int seq) {
			String sql = "update test_board set cnt=cnt+1 where seq=?";
			connect();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, seq);
				pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}

		}
	
		//단건 조회
		public BoardVO getBoard(int seq) {
			connect();
			String sql = "select * from test_board where seq =?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, seq);
				rs = pstmt.executeQuery();
				while (rs.next()) {

					BoardVO vo = new BoardVO();
					vo.setSeq(seq);
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setContent(rs.getString("content"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setVisitCnt(rs.getInt("visitCnt"));

					return vo;

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}

			return null;

		}
		
		// 목록.
		public List<BoardVO> boardList() {
			connect();
			String sql = "select * from test_board order by 1";
			List<BoardVO> list = new ArrayList<>();

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					
					BoardVO vo = new BoardVO();
							vo.setSeq(rs.getInt(1));
							vo.setTitle(rs.getString(2));
							vo.setWriter(rs.getString(3));
							
						
					list.add(vo);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}

			return list;
		}
		
		//페이징 처리
		public List<BoardVO> getListPaging(Criteria cri){
			List<BoardVO> list = new ArrayList<>();
			String sql = "SELECT seq, title, content, write_date, writer ,visit_cnt \r\n"
					+ "FROM(SELECT ROWNUM rn, seq, title, content, write_date, writer, visit_cnt\r\n"
					+ "FROM (SELECT rownum rn ,seq, title, content, write_date, writer, visit_cnt FROM test_board ORDER BY seq DESC)\r\n"
					+ "WHERE ROWNUM<=?) WHERE rn>?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getAmount()*cri.getPageNum());
			pstmt.setInt(2, cri.getAmount()*(cri.getPageNum()-1));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriteDate(rs.getString("write_date"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setVisitCnt(rs.getInt("visit_cnt"));
				list.add(board);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			disconnect();
		} return list;
		
		}
		
		
		//CART 전체 데이터
		public List<CartVO> cartList(){
			String sql = "select * from cart";
			List<CartVO> cartList = new ArrayList<>();
			connect();
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					CartVO cart = new CartVO();
					cart.setNo(rs.getInt("no"));
					cart.setProductNm(rs.getString("product_nm"));
					cart.setPrice(rs.getInt("price"));
					cart.setQty(rs.getInt("qty"));
					cartList.add(cart);
				}
						
				
			}catch (SQLException e) {
				
					e.printStackTrace();
				} finally {
					disconnect();
				} return cartList;
				
		}
		
		//수량 변경
		   
		   public void updateCart(int no, int qty) {
		      String sql = "update cart set qty = " + qty + " where no = " + no;
		      try {
		         connect();
		         stmt = conn.createStatement();
		         int r = stmt.executeUpdate(sql);
		         if (r>0) {
		            System.out.println(r+"건 수정.");
		         }
		      } catch(SQLException e) {
		         e.printStackTrace();
		      } finally {
		         disconnect();
		      }
		   }

}





