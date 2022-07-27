<%@page import="co.edu.common.MemberVO"%>
<%@page import="co.edu.common.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String name = request.getParameter("name");
String pw = request.getParameter("passwd");
String mail = request.getParameter("email");

MemberService service = MemberService.getInstance();
MemberVO vo = new MemberVO();
vo.setId(id);
vo.setName(name);
vo.setPasswd(pw);
vo.setMail(mail);
service.memberAdd(vo);

request.setAttribute("member",vo);
//처리결과 : memberOutput.jsp
RequestDispatcher rd = request.getRequestDispatcher("memberOutput.jsp");
rd.forward(request,response);


%>