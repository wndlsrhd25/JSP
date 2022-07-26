<%@page import="com.tst.board.BoardVO"%>
<%@page import="com.tst.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

  <head>
    <meta charset="UTF-8">
    <title>updateForm.jsp</title>
  </head>

  <body>

    <%
	// boardDetail에서 bno를 가져옴
	
	int bno = Integer.parseInt(request.getParameter("bno"));

	BoardDAO dao = new BoardDAO();
	BoardVO vo = dao.getBoard(bno);
	
	
	%>
	
    <form action="update.jsp">
      <table border="1">
        <tr>
          <th>글번호</th>
          <td><input type="text" name="bid" value="<%=vo.getBoardId() %>" readonly></td>
        </tr>
        <tr>
          <th>제목</th>
          <td><input type="text" name="btitle" value="<%= vo.getTitle() %>"></td>
        </tr>
        <tr>
          <th>내용</th>
           <td><textarea name="bcontent" cols="30" rows="3"><%= vo.getContent()%></textarea></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="수정"></td>
        </tr>
      </table>
      
    </form>
  </body>

</html>