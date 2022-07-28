package co.dev.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.service.MemberService;
import co.dev.vo.MemberVO;

public class MemberDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		//파라메터
		String id = req.getParameter("id");
	

		
		MemberService service = MemberService.getInstance();
		service.eraseMember(id);
		
		//공유 : vo
		req.setAttribute("member", id);
		
		Utils.forward(req, resp, "memberResult/memberDeleteOutput.jsp");
	}

}
