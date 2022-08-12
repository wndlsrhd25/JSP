package co.dev.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.dev.common.SqlMapSessionFactory;
import co.dev.vo.MemberVO;

public class MemberDAOMybatis {
	
	//sqlSessionFactory 가져오기
	SqlSessionFactory sessionFactory;
	
	private static MemberDAOMybatis instance = new MemberDAOMybatis();
	
	private MemberDAOMybatis() {
		sessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	}
	
	public static MemberDAOMybatis getInstance() {
		return instance;
	}
	
	//전체리스트
	public List<MemberVO> getList(){
		SqlSession session = sessionFactory.openSession();
		List<MemberVO> list = session.selectList("co.dev.mybatisdb.memberMapper.getMemberList");//메퍼의 namespace + id
		session.close();
		return list;
	}
	
	//한건 조회
	public MemberVO searchMember(String id) {
		SqlSession session = sessionFactory.openSession();
		MemberVO member = session.selectOne("co.dev.mybatisdb.memberMapper.getMember",id);
		session.close();
		return member;
	}

	//입력
	public void insertMember(MemberVO vo) {
		SqlSession session = sessionFactory.openSession();
		session.insert("co.dev.mybatisdb.memberMapper.insertMember",vo);
		session.close();
	}
	
	//수정
	public void UpdateMember(MemberVO vo) {
		SqlSession session = sessionFactory.openSession();
		session.insert("co.dev.mybatisdb.memberMapper.updateMember",vo);
		session.close();
	}
	
	
	
	//삭제
	public boolean DeleteMember(String id) {
		SqlSession session = sessionFactory.openSession(true); //openSession(false);
		int r = session.delete("co.dev.mybatisdb.memberMapper.deleteMember",id);
		session.close();
		if(r>0) {
			return true;
		}else {
			return false;
		}
		/* session.commit(); */
	}
}

