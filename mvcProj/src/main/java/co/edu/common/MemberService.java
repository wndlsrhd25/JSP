package co.edu.common;

public class MemberService {
	//memberDAO에서 최소한의 기능만 구현하고 여기서 처리할거임
	
	//싱글톤
	private static MemberService instance = new MemberService();
	
	
	private MemberService() {
		
	}

	public static MemberService getInstance() {
		
		return instance;
	}
	
	MemberDAO dao = new MemberDAO();

	// 입력과 삭제 or 입력과 수정을 함께 구현가능함
	public void memberAdd(MemberVO vo) {
		dao.insertMember(vo);
	
	}


}
