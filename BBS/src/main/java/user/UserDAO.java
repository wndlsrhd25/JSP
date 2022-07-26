package user;


public class UserDAO extends DAO{

				
	public int login(String userId, String userPassword) {
		String SQL = "SELECT user_password FROM BBS_USERS WHERE user_id =?";
		connect();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				} else
					return 0;
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(User user) {
		String SQL ="INSERT INTO BBS_USERS VALUES (?,?,?,?,?)";
		connect();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			
		return pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return -1; //데이터베이스 오류
	}
	
}
