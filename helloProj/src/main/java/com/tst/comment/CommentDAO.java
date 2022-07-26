package com.tst.comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tst.board.BoardVO;
import com.tst.common.DAO;

public class CommentDAO extends DAO {

	
	//등록
	public void insertComment(CommentVO vo) {
		String sql ="insert into comments values(?,(select nvl(max(comment_id),0)+1 from comments), ?,?,sysdate)";
	connect();
	try {
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, vo.getBoardId());
		pstmt.setInt(2, vo.getCommentId());
		pstmt.setString(3, vo.getCommentContent());
		pstmt.setString(4, vo.getUserId());
		pstmt.setString(5, vo.getCommentDate());

		int rs = pstmt.executeUpdate();
		System.out.println(rs + "건 댓글 입력");
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		disconnect();
	}

}
	
	//댓글 전체조회
	public List<CommentVO> commentList(){
		connect();
		String sql ="select * from comments order by 1";
		List<CommentVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentVO vo = new CommentVO(rs.getInt("board_id"), 
										rs.getInt("comment_id"), 
										rs.getString("comment_content"),
										rs.getString("user_id"), 
										rs.getString("comment_date") 
		
				);
				
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}
	
}
