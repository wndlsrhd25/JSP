package co.dev.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import co.dev.service.MemberService;
import co.dev.vo.MemberVO;


public class MemberJsonController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//[{"name":"hong","age":25},{"name":"hwang","age":30}]

		resp.setContentType("text/json;charset=utf-8");
		
		MemberService service = MemberService.getInstance();
		List<MemberVO> members = service.memberList();
		
		String json = "[{\"name\":\"hong\",\"age\":25},{\"name\":\"hwang\",\"age\":30}]";
	
		JsonArray jary = new JsonArray();
		for(MemberVO vo : members) {
		//gson다운로드해야함
		JsonObject jobj = new JsonObject();
		jobj.addProperty("id",vo.getId());
		jobj.addProperty("name", vo.getName());
		jobj.addProperty("passwd", vo.getPasswd());
		jobj.addProperty("mail", vo.getMail());
		
		//jary 추가
		jary.add(jobj);
		
		}
		try {
			resp.getWriter().print(jary);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
