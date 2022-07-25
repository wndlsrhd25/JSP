package com.tst.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendRedirect")
public class SendRedirectServlet extends HttpServlet {

	//HttpServletResponse 요청 재지정
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("site");
		
		if(param.equals("naver")) {
			//sendRedirect 이페이지로 호출된 것을 재실행 하도록 하겠다
			resp.sendRedirect("https://www.naver.com");
		} else if(param.equals("daum")) {
			resp.sendRedirect("https://www.daum.net");
		} else if(param.equals("google")) {
			resp.sendRedirect("https://www.google.com");
		}
	}
}
