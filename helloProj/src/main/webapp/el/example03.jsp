<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el/exampe03.jsp</title>
</head>
<body>

<%
String param = request.getParameter("msg");
%>
<c:catch var="ex">
<%
if(param.equals("add")){
	out.print(param);
}
%>

</c:catch>
<c:out value="${ex }"></c:out>
</body>
</html>