
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addComment.jsp</title>
</head>
<body>
<%String user = (String) session.getAttribute("loginId"); %>
<%int bnum = (int) session.getAttribute("boardId"); %>

  <form action="insertComment.jsp" method="post">
    글번호 : <input type="text" name="bID" value =<%=bnum %> readonly><br>
    댓글내용 : <textarea name="content" cols="30" rows="3"></textarea><br>
    작성자 : <input type="text" name="writer" value =<%=user %> readonly><br>
    
    <input type="submit" value="등록">
    


  </form>


</body>
</html>