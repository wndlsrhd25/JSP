package co.edu.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.edu.service.BoardService;
import co.edu.vo.CartVO;

public class CartListControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json; charset=utf-8");
		//DAO 메소드, MemberService 메소드 http:///localhost/JSPTest/cartList.do
		//JSON 형태로 반환
		BoardService service = BoardService.getInstance();
		List<CartVO> cartList = service.cartList();
		
		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(cartList));//제이슨 타입으로 바꿔서 반환해줌
		
		
		
		
	}

}
