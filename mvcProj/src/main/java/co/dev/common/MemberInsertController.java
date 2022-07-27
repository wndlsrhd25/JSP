package co.dev.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.service.MemberService;
import co.dev.vo.MemberVO;

public class MemberInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//입력. -> 뷰페이지
		MemberService service = MemberService.getInstance();

		String id = req.getParameter("id");
		String pw = req.getParameter("passwd");
		String nm = req.getParameter("name");
		String ml = req.getParameter("mail");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setName(nm);
		vo.setPasswd(pw);
		vo.setMail(ml);
		
		
		service.addMember(vo);
		
		
		//요청처리 결과 뷰 페이지 전송. vo를 memeber라는 값에 넣음
		req.setAttribute("member", vo);
		
		/*
		RequestDispatcher rd = req.getRequestDispatcher("memberResult/memberInsertOutput.jsp");
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		
		
		
		//위에 대신에 Utils 클래스만든걸로 사용함
		Utils.forward(req, resp, "memberResult/memberInsertOutput.jsp");
	}

}
