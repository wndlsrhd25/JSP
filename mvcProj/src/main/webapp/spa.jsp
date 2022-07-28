<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>spa.jsp</title>
</head>

<body>
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>비밀번호</th>
			</tr>

		</thead>
		<tbody id="list"></tbody>
	</table>
	<!-- Ajax(비동기방식처리) -->

	<script>
		/* 비동기방식 처리해야하는 일이 병렬로 처리됨 */
		/*      setTimeout(function () {
		       console.log("A");
		     }, 2000);

		     setTimeout(function () {
		       console.log("B");
		     }, 3000);

		     setTimeout(function () {
		       console.log("C");
		     }, 1000);
		 */

		/* 동기방식 a끝나고 b ,b끝나면 c  */
		/*    setTimeout(function () {
		       console.log("A");
		       setTimeout(function () {
		           console.log("B");
		           setTimeout(function () {
		               console.log("C");
		             }, 1000);
		           
		         }, 3000);

		     }, 2000); */

		let i = 0;
		let xhtp = new XMLHttpRequest(); //비동기방식 처리
		xhtp.open("get", 'dataList.txt');
		xhtp.send(); //send로 호출되야지 작업이 실행됨
		//클릭을 하면 함수를 실행시키겠습니다
		/* xhtp.onreadystatechange = callBackOne; */
		xhtp.onreadystatechange = callBackThree;
		
		let fields = ['id','name','mail','pass'];
		function callBackThree(){
			if (this.readyState == 4 && this.status == 200) {
				let data = JSON.parse(this.responseText); // object -> json 
				
				let tbody = document.getElementById('list');
				//데이터 건수 반복.
				for(let obj of data){
				//tr,td,td,td,td	
				let tr = document.createElement('tr');
				
				//필드 개수 반복
				for(let field of fields){
				let td1 = document.createElement('td');
				td1.innerText = obj[field];
				tr.append(td1);	
				}
				
				tbody.append(tr);
				}
			}
				
		}
			
		
		//memberjsoncontroller에서 실행하는것
		function callBackTwo() {
			if (this.readyState == 4 && this.status == 200) {
				let data = JSON.parse(this.responseText); //json -> object
				console.log(data);
				
				let ul = document.createElement('ul');
				for (let obj of data){
					let li = document.createElement('li');
					li.innerHTML = obj.name +', ' + obj.age;
					ul.append(li);
				}
				
				console.log(ul);
				document.querySelector('body').append(ul);
			}

		}

		function callBackOne() {
			if (this.readyState == 4 && this.status == 200) {
				let data = JSON.parse(this.responseText); //json -> object
				console.log(data);

				let name = document.createElement('p');
				name.innerText = data.name;
				let age = document.createElement('p');
				age.innerText = data.age;

				document.querySelector('body').append(name, age);
			}
		}
	</script>
</body>



</html>