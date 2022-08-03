package co.edu.service;

import co.edu.dao.MemberDAO;
import co.edu.vo.MemberVO;

public class MemberService {

	private static MemberService instance = new MemberService();
	MemberDAO mdao = new MemberDAO();

	private MemberService() {
	}

	public static MemberService getInstance() {
		return instance;

	}

	// 회원가입
	public void addMember(MemberVO vo) {
		mdao.insertMember(vo);
	}

	// 중복체크
	public MemberVO idCheck(String id) {
		return mdao.selectId(id);
	}
	
	//
	
}
