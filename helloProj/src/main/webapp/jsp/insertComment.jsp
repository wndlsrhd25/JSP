<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="com.tst.comment.CommentVO"%>
<%@page import="com.tst.comment.CommentDAO"%>


<%

	request.setCharacterEncoding("UTF-8");
	int boardId = Integer.parseInt(request.getParameter("bID"));
	String commentContent = request.getParameter("content");
	String userId = request.getParameter("id");

	
	CommentVO vo = new CommentVO();
	vo.setBoardId(boardId);
	vo.setCommentContent(commentContent);
	vo.setUserId(userId);
	
	
	CommentDAO dao = new CommentDAO();
	dao.insertComment(vo);
	
	out.print("completed");
	
	response.sendRedirect("boardDetail.jsp?id="+boardId);
%>