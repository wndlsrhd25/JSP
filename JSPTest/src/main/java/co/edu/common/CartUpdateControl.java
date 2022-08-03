package co.edu.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.service.BoardService;

public class CartUpdateControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String qty = req.getParameter("qty");
		
		BoardService service = BoardService.getInstance();
		service.updatCart(Integer.parseInt(no), Integer.parseInt(qty));
		
		resp.getWriter().print("success");
		

	}

}
