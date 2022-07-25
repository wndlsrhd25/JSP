package com.tst.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionTest")
public class SessionTestServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		HttpSession session = null;
		String param = req.getParameter("p");
		String msg = null;
		
		// 생성, 변경, 삭제
		if(param.equals("create")) {
			//true는 없으면 새로 생성하겠다는 뜻
			session = req.getSession(true); // 셍성된 세션값이 있으면 session 반환, 없으면 새로 생성.
		//이전에 만든건지 구분하는 메소드 isNew
			if(session.isNew()) {
			msg = "새로운 세션 객체가 생성";
		}else {
			msg = "기존 세션 객체 반환";
		}
		}else if (param.equals("delete")) {
			//false는 없으면 새로 생성 안하고 null을 반환 한다는 뜻
			session = req.getSession(false); // 생성된 세션이 있으면 세션 반환, 없으면 null
			if(session != null) {
				session.invalidate(); //세션삭제.
				msg = "세션 객체 삭제";
			}else {
				msg = "삭제할 세션 객체 없음";
			}
		} else if(param.equals("add")) {
			//add 는 attribute를 추가해주는것
			session = req.getSession(true);
			//메세지 추가함은 object 자리라서 어떤 형태도 들어 갈 수 있음(string을 넣은것)
			session.setAttribute("msg", "메세지 추가함");
			msg = "세션 객체에 속성 지정";
		}else if (param.equals("get")) {
			session = req.getSession(false); //세션 객체 없으면 null 리턴
			if(session !=null) {
				String str = (String) session.getAttribute("msg");
				msg = str;
			} else {
				msg = "데이터를 추출할 세션 없음";
			}
		} else if (param.equals("remove")) {
			//remove는 attribute를 삭제하는것 -> get하면 null 나옴
			session = req.getSession();
			if(session != null) {
				session.removeAttribute("msg");
				msg = "세션 객체의 속성을 삭제";
			} else {
				msg = "속성을 제거할 세션객체 없음";
			}
		}
		
		resp.getWriter().print("처리결과: " + msg);
		
		//실행 후 ?p=create 해주면 - 새로운 세션 객체가 생성
		// 다시 ?p=create 해주면 - 기존 세션 객체 반환
		
		
	}

}
