<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

</script>
<div align="center">
	<div>
		<h1>게시글등록</h1>
	</div>
	<div>
		<form id="frm" name="frm" action="insertBoard.do" method="post">
			<div>
				<table border="1">
			
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align:center;">게시판글쓰기 양식</th>
						</tr>
			
						<tr>
							<td><input type="text" id="bbsTitle" placeholder="글제목" name="bbsTitle" maxlength="50" /></td>
						</tr>
						<tr>
							<td><textarea id="bbsContent" placeholder="글내용" name="bbsContent" maxlength="500" style="height:100px;" /></textarea></td>
						</tr>
						<tr>
							<td><input type ="text" id="bbsWriter" placeholder="글쓴이" name="bbsWriter" maxlength="20" /></td>
						</tr>
				
				</table>
			</div><br />
			<div>
				<button type="submit" >글쓰기</button>&nbsp;&nbsp;&nbsp;
				<button type="reset">취 소</button>&nbsp;&nbsp;&nbsp;
				<button type="button" onclick="location.href='main.do'">홈</button>
			</div>
		</form>
	</div>
</div>

