<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.tst.board.BoardDAO"%>

<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	//로그인 시도 시 에러메세지 전달하기
	RequestDispatcher rd = request.getRequestDispatcher("loginForm.jsp");
	
	BoardDAO dao = new BoardDAO();
	if(dao.loginCheck(id, pwd) == null){
		request.setAttribute("msg", "아이디와 비밀번호를 확인하세요");
		rd.forward(request,response);
	}else {
		session.setAttribute("loginId", id);
		response.sendRedirect("boardList.jsp");
		
	}
%>

