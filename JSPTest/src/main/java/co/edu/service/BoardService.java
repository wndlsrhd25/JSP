package co.edu.service;

import java.util.List;

import co.edu.dao.BoardDAO;
import co.edu.vo.BoardVO;
import co.edu.vo.CartVO;
import co.edu.vo.Criteria;

public class BoardService {

	
	private static BoardService instance = new BoardService();
	BoardDAO dao = new BoardDAO();
	
	private BoardService() {}
	public static BoardService getInstance() {
		return instance;
	}
	
	//글등록
	public void addBoard(BoardVO vo) {
	 dao.insertBoard(vo);
	 
	}
	
	//글목록
	public List<BoardVO> BoardList(){
		return dao.boardList();
	}
	
	//페이징
	public List<BoardVO> getListPaging(Criteria cri){
		return dao.getListPaging(cri);
	}

	//cart 목록
	public List<CartVO> cartList(){
		return dao.cartList();
	}

	
	//수량변경
	public void updatCart(int no, int qty) {
		dao.updateCart(no,qty);
	}
}
