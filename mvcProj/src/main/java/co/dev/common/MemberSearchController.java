package co.dev.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.dev.service.MemberService;
import co.dev.vo.MemberVO;

public class MemberSearchController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		String id = req.getParameter("id");
		String job = req.getParameter("job");

		if (id.isEmpty()) {
			req.setAttribute("error", "id를 입력하세요.");
			if (job.equals("search")) {
				Utils.forward(req, resp, "memberView/memberSearch.jsp");

			}else if (job.equals("update")) {
				Utils.forward(req, resp, "memberView/memberUpdate.jsp");
			}
			return;
		}

			MemberService service = MemberService.getInstance();
			
			//vo값이 있으면 attribute를 넣고 없으면 result라는 걸 보이게함
			MemberVO vo = service.getMember(id);
			
			if(vo == null) {
				//memberUpdate에서 보여줌
				req.setAttribute("result", "검색된 정보가 없습니다.");
			}
			
			req.setAttribute("member", vo);
			
			if (job.equals("search")) {
				Utils.forward(req, resp, "memberResult/memberSearchOutput.jsp");
			} else if (job.equals("update")) {
				Utils.forward(req, resp, "memberView/memberUpdate.jsp");
			}
		}
	}

