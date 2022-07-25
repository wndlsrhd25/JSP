<%@page import="com.tst.common.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

  <head>
    <meta charset="UTF-8">
    <title>empResult.jsp</title>
  </head>

  <body>
    <table border="1">
      <thead>
        <tr>
          <th>사원번호</th>
          <th>이름</th>
          <th>이메일</th>
          <th>급여</th>
        </tr>
      </thead>
      <tbody>


        <%
        //자바코드
		request.getParameter("first_name");
		List<Employee> list = (List<Employee>) request.getAttribute("data");
		for (Employee emp : list) {
		%>
        <tr>
          <td><%=emp.getEmployeeId()%></td>
          <td><%=emp.getFirstName()%></td>
          <td><%=emp.getEmail()%></td>
          <td><%=emp.getSalary()%></td>
        </tr>
        <%
		//자바 코드
		//out.print("<br>" + "사원번호 : " + emp.getEmployeeId()+", 이름 " + emp.getFirstName()+", 직무 : "+ emp.getJobId());

		}
		%>
      </tbody>

    </table>
  </body>

</html>