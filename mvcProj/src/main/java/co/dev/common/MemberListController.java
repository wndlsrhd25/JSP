package co.dev.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.service.MemberService;

public class MemberListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		MemberService service = MemberService.getInstance();
		
		
		Utils.forward(req, resp, "memberResult/memberListOutput.jsp");
	}

}
