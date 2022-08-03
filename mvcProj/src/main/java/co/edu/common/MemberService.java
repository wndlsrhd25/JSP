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
	
	MemberDAO mdao = new MemberDAO();


	//회원가
	public void memberAdd(MemberVO vo) {
		mdao.insertMember(vo);
	
	}
	
	


}
