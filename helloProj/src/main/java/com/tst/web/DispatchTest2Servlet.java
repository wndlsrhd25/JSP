package com.tst.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatch2")
public class DispatchTest2Servlet extends HttpServlet{
	
	//bookInput.html -> dispatch1 -> dispatch2
	//					setAttribute()  setAttribute()

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;chrset=utf-8");
		
		String ti = (String) req.getAttribute("param1");
		String auth = (String) req.getAttribute("param2");
		String pub = (String) req.getAttribute("param3");
		
		resp.getWriter().print("<h3>Dispatch page 2</h3>");
		resp.getWriter().print("책제목 : " +ti + "<br>");
		resp.getWriter().print("책제목 : " +auth + "<br>");
		resp.getWriter().print("책제목 : " +pub + "<br>");
		
//		
//		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/dispatch2");
//		rd.forward(req, resp);
		
		
		
	}
}
