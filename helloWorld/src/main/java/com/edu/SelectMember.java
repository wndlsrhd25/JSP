package com.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.common.Member;
import com.edu.common.MemberDAO;

@WebServlet("/selectMember")
public class SelectMember extends HttpServlet {
	
	//연습한 예제 - memeber2와 연결
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String value = req.getParameter("select");
		resp.setContentType("text/html;charset=UTF-8");
		MemberDAO dao = new MemberDAO();
		List<Member> list = dao.selectMember(value);
		System.out.println(value);
		System.out.println(list);
		PrintWriter out = resp.getWriter();
		out.print("<table border = '1'>");
		out.print("<thead><tr><th>아이디</th><th>롤</th></tr></thead>");
		out.print("<tbody>");
		for(Member meb : list) {
			if(meb.getMemberRole()==1) {
				out.print("<tr><td>"+meb.getMemberId()+"</td>"
						+"<td>일반유저</td></tr>");
				
			}else {
				out.print("<tr><td>"+meb.getMemberId()+"</td>"
						+"<td>관리자</td></tr>");
			}
			
		}
		
		out.print("</tbody>");
		out.print("</table>");
		
	}
	
	

}
