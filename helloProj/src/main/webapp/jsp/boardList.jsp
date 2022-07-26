<%@page import="com.tst.board.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.tst.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>jsp/boardList.jsp</title>
</head>
<body>

<%-- 	<%
	String id = (String) session.getAttribute("loginId");
	if (id != null) {
		out.print("<h3>" + id + "님으로 로그인되었습니다</h3>");
	%>
	<button onclick="location.href='logout.jsp'">로그아웃</button>
	<%
	} else {
	out.print("<h3>손님입니다</h3>");
	%>
	<button onclick="location.href='loginForm.jsp'">로그인</button>
	<%
	}
	%> --%>
	
	 <c:choose>
	   <%-- empty 널인지 아닌지 체크해야함--%>
      <c:when test="${!empty loginId}">
      <h3><c:out value="${loginId }"></c:out>님으로 로그인했습니다.</h3>
      </c:when>
      <c:otherwise>
      <h3>비회원입니다</h3>
      </c:otherwise>
   </c:choose>
	
	<table border='1'>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>


			<%
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.boardList();
			
			%>

			<c:set var="boards" value="<%=list %>" />
			<c:forEach var="vo" items="${boards }" >
			
			<tr>
				<td><a href="boardDetail.jsp?id=${vo.boardId }">${vo.boardId }</a></td>
				<td>${vo.title }</td>
				<td>${vo.writer }</td>
				<td>${vo.createDate }</td>
				<td>${vo.cnt }</td>

			</tr>
			</c:forEach>
			
			
<%-- 				<%
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.boardList();
			for (BoardVO vo : list) {
			%>

			<tr>
				<td><a href="boardDetail.jsp?id=<%=vo.getBoardId()%>"><%=vo.getBoardId()%></a></td>
				<td><%=vo.getTitle()%></td>
				<td><%=vo.getWriter()%></td>
				<td><%=vo.getCreateDate()%></td>
				<td><%=vo.getCnt()%></td>

			</tr>
			<%
			}
			%> --%>
			
		</tbody>
	</table>
	
	
	   <c:choose>
	   <%-- empty 널인지 아닌지 체크해야함--%>
      <c:when test="${!empty loginId}">
         <a href="logout.jsp"><input type="button" value="로그아웃"></a>
      </c:when>
      <c:otherwise>
         <a href="login.jsp"><input type="button" value="로그인"></a>
      </c:otherwise>
   </c:choose>
	<button onclick="location.href='addBoard.jsp'">글쓰기</button>
</body>
</html>