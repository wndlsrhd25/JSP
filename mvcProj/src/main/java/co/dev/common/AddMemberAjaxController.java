package co.dev.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.dev.service.MemberService;
import co.dev.vo.MemberVO;

public class AddMemberAjaxController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		resp.setContentType("text/json;charset=utf-8");
		
		//todo 회원정보 등록 -> json 값 반환
		MemberService service = MemberService.getInstance();
		
		String id = req.getParameter("id");
		String nm = req.getParameter("name");
		String pw = req.getParameter("passwd");
		String ml = req.getParameter("mail");
		


		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setName(nm);
		vo.setPasswd(pw);
		vo.setMail(ml);
		
		
		service.addMember(vo);
		
		//json 반환
		Gson gson = new GsonBuilder().create();
		try {
			resp.getWriter().print(gson.toJson(vo)); //vo가 가지고 있는 필드형을 그대로 가져와서 만들어줌 id,name,passwd,mail
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
