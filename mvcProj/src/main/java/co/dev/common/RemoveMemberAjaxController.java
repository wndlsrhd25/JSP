package co.dev.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.service.MemberService;

public class RemoveMemberAjaxController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//todo 삭제 처리 -> json 반환.
		resp.setContentType("text/json;charset=utf-8");
		
		String id = req.getParameter("id");
		
		MemberService service = MemberService.getInstance();
		boolean isDeleted = service.eraseMember(id);
		
		//{"retCode":"Success"}
		try {
			if(isDeleted) {
			resp.getWriter().print("{\"retCode\":\"Success\"}");
			}else {
			resp.getWriter().print("{\"retCode\":\"Fail\"}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
