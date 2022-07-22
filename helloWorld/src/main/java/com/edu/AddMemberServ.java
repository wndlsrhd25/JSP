package com.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.common.EmpDAO;

@WebServlet("/addMember")
public class AddMemberServ extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//사용자 : user_name=user2&user_pass=1234$role=1
		String name = req.getParameter("user_name");
		String pass = req.getParameter("user_pass");
		String role = req.getParameter("role");
		
		//get : 수정, post :입력
		EmpDAO dao = new EmpDAO();
		if(req.getMethod().toUpperCase().equals("GET")){
//			dao.updateMember(name, pass, role);
			
			//수정하기
			int up =dao.updateMember(name, pass, role);
			 
			if(up<1) {
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('계정 수정이 실패했습니다'); "
						+ "</script>");
				out.flush();
				resp.getWriter().print("failed");
				
			}else {
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('계정 수정이 성공했습니다'); "
						+ "</script>");
				out.flush();
				resp.getWriter().print("Completed");
			}
		} else {
//			dao.insertMember(name, pass, role);
			//입력하기
			boolean isTrue =dao.insertMember(name, pass, role);
			 
			if(isTrue=false) {
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('계정 등록이 실패했습니다'); "
						+ "</script>");
				out.flush();
				resp.getWriter().print("failed");
				
			}else {
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('계정 등록이 성공했습니다'); "
						+ "</script>");
				out.flush();
				resp.getWriter().print("Completed");
			}
		
		}
		
	
		
	}
}
