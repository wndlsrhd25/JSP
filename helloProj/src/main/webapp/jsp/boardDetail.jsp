<%@page import="com.tst.board.BoardVO"%>
<%@page import="com.tst.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetail.jsp</title>
</head>
<body>
<%
	// 파라미터를 읽어서 BoardDAO에서 한건 조회기능을 사용해서.
	// 화면에 출력하도록 구현하세요
	
	int bno = Integer.parseInt(request.getParameter("id"));

	BoardDAO dao = new BoardDAO();
	BoardVO vo = dao.getBoard(bno);
	
	//if문을 돌려
	dao.setCnt(bno);
%>
<!-- 글번호, 제목, 내용, 작성자, 작성일자, 조회수 -->

<table border='1'>
		
			<tr><th>글번호</th><td><%= vo.getBoardId() %></td></tr>
			<tr><th>제목</th><td><%= vo.getTitle() %></td></tr>
			<tr><th>내용</th><td><%= vo.getContent() %></td></tr>
			<tr><th>작성자</th><td><%= vo.getWriter() %></td></tr>
			<tr><th>작성일시</th><td><%= vo.getCreateDate() %></td></tr>
			<tr><th>조회수</th><td><%= vo.getCnt() %></td></tr>
	
		
		</table>
		
		<!-- bno를 넘길거임 -->
		
		<%	
		String loginId = (String) session.getAttribute("loginId");
		if(loginId.equals(vo.getWriter())){
		%>
		<br><button onclick="location.href='boardList.jsp'">글 목록으로</button>
		<button onclick="location.href='updateForm.jsp?bno=<%= vo.getBoardId() %>'">수정</button>
		<button onclick="del()">삭제</button>
		<%
		} else {		
		%>
		<br><button onclick="location.href='boardList.jsp'">글 목록으로</button>
		<%
		}
		%>
		
	<script>
		function del() {
			let con_test = confirm("정말 삭제하시겠습니까");
			if (con_test) {
				
				 location.href="delete.jsp?bno=<%= vo.getBoardId() %>";
				 

			} else{
				location.href="boardList.jsp"
			}
		}
	</script>
</body>
</html>