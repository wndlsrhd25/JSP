package co.edu.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.service.BoardService;
import co.edu.vo.BoardVO;

public class InsertBoardControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//입력
		
				BoardService service = BoardService.getInstance();
				
				String title = req.getParameter("bbsTitle");
				String writer = req.getParameter("bbsWriter");
				String content = req.getParameter("bbsContent");
				
				System.out.println(title);
				System.out.println(writer);
				System.out.println(content);
				BoardVO vo = new BoardVO();
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setContent(content);
				
				service.addBoard(vo);
				
				req.setAttribute("board", vo);
		
			 HttpUtil.forward(req, resp, "board/insertBoard.tiles");
			
	}
		}