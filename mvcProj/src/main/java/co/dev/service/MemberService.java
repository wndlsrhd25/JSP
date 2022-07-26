package co.dev.service;

import java.util.List;

import co.dev.dao.MemberDAO;
import co.dev.dao.MemberDAOMybatis;
import co.dev.vo.MemberVO;

//비지니스 처리
public class MemberService {
	
	private static MemberService instance = new MemberService();
	//MemberDAO dao = new MemberDAO(); jdbc 쿼리 처리
	
	MemberDAOMybatis dao = MemberDAOMybatis.getInstance(); //Mybatis 처리
	
	private MemberService() {}
	public static MemberService getInstance() {
		return instance;
		
	}
	
	//회원가입
	public void addMember(MemberVO vo) {
		dao.insertMember(vo);
	}

	
	//회원목록
	public List<MemberVO> memberList(){
		return dao.getList();
	}
	
	//회원 조회
	public MemberVO getMember(String id) {
		return dao.searchMember(id);
	}
	
	//회원정보수정
	public void modifyMember(MemberVO vo) {
		dao.UpdateMember(vo);
	}
	
	//회원정보삭제
	public boolean eraseMember(String id) {
		return dao.DeleteMember(id);
	}
}
