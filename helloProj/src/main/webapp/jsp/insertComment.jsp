<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="com.tst.comment.CommentVO"%>
<%@page import="com.tst.comment.CommentDAO"%>

<%
	request.setCharacterEncoding("UTF-8");
	int boardId = Integer.parseInt(request.getParameter("bID"));
	int commentId = Integer.parseInt(request.getParameter("cID"));
	String commentContent = request.getParameter("content");
	String userId = request.getParameter("id");
	String commentDate = request.getParameter("date");
	
	CommentVO vo = new CommentVO();
	vo.setBoardId(boardId);
	vo.setCommentId(commentId);
	vo.setCommentContent(commentContent);
	vo.setUserId(userId);
	vo.setCommentDate(commentDate);
	
	CommentDAO dao = new CommentDAO();
	dao.insertComment(vo);
	
	out.print("completed");
	
	response.sendRedirect("boardDetail.jsp");
%>