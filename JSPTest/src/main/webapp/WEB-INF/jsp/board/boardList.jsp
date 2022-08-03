<%@page import="java.util.List"%>
<%@page import="co.edu.dao.BoardDAO"%>
<%@page import="co.edu.vo.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<style>
.center {
  text-align: center;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
</head>

<body>
<div align="center">
	<div>
		<h1>게시글목록</h1>
	</div>

			<div>
				
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
			<c:forEach var="list" items="${list }" >
			<tr>
				<td>${list.seq }</td>
				<td>${list.title }</td>
				<td>${list.writer }</td>
				<td>${list.writeDate }</td>
				<td>${list.visitCnt }</td>
			</tr>
			</c:forEach>
			
						
		</tbody>
	</table>
	
	<br>
	<div class="center">
	
		  <div class="pagination">
		  <c:if test = "${pageInfo.prev }">
		  <a href="boardListPaging.do?pageNum=${pageInfo.startPage - 1 }&amount=${pageInfo.cri.amount}">prev</a>
		  </c:if>
		  <c:forEach var ="num" begin="${pageInfo.startPage }" end="${pageInfo.endPage}"> 
		  <a href="boardListPaging.do?pageNum=${num }&amount=${pageInfo.cri.amount }">${num }</a>
		  </c:forEach>
		  <c:if test = "${pageInfo.next }">
		  <a href="boardListPaging.do?pageNum=${pageInfo.endPage + 1 }&amount=${pageInfo.cri.amount}">next</a>
		  
		  </c:if>
		  </div>
		</div>
			</div><br />
</div>
</body>