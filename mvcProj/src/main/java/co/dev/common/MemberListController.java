package co.dev.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.service.MemberService;
import co.dev.vo.MemberVO;

public class MemberListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
		MemberService service = MemberService.getInstance();
		
		//가져와서오는것
		List<MemberVO> vo = service.memberList();
		
		//값을 집어넣어서
		req.setAttribute("list", vo);
		
		//보내줘야함
		Utils.forward(req, resp, "memberResult/memberListOutput.jsp");
		
	}

}
