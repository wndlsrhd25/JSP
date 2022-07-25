package com.tst.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatch1")
public class DispatchTest1Servlet extends HttpServlet {

	//RequestDispatcher 요청 재지정
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		resp.getWriter().print("<h3>Dispatch page1</h3>");
		
		
		
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String publish = req.getParameter("publish");
		
		req.setAttribute("param1", title);
		req.setAttribute("param2", author);
		req.setAttribute("param3", publish);
		
		//요청정보와 응답 정보를 다 넘기겠다
		RequestDispatcher rd =this.getServletContext().getRequestDispatcher("/dispatch2");
//		rd.include(req, resp); //dispatch1과 dispatch2를 함께 요청하겠다
		rd.forward(req, resp); //dispatch2라는 페이지를 재요청 하겠습니다.
	}
}
