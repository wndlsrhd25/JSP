<%@page import="com.tst.board.BoardVO"%>
<%@page import="com.tst.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <%
    int id = Integer.parseInt(request.getParameter("bno"));
    
    BoardVO vo = new BoardVO();
    vo.setBoardId(id);

    
    BoardDAO dao = new BoardDAO();
    dao.deleteBoard(vo);
    
    response.sendRedirect("boardList.jsp");
    
    %>