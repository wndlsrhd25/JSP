package co.dev.dao;

import java.util.List;

import co.dev.vo.MemberVO;

public class ConfigTest {

	public static void main(String[] args) {
		MemberDAOMybatis dao = MemberDAOMybatis.getInstance();
		
		//삭제
		dao.DeleteMember("user1");
	
		//전체리스트 출력
		List<MemberVO> list = dao.getList();
		
		for(MemberVO member : list) { 
			
			System.out.println(member.toString());
			
		}
		
		/*
		 * MemberVO vo =dao.searchMember("user4"); System.out.println(vo.toString());
		 */
	
	}
}
