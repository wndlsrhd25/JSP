<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>spa.jsp</title>
</head>

<body>
	<h3>회원등록</h3>
	<form name="addFrm" action="addMemberAjax.do" method="post">
		아이디 : <input type="text" name="id"><br> 이름 : <input
			type="text" name="name"><br> 이메일 : <input type="text"
			name="mail"><br> 비밀번호 : <input type="password"
			name="passwd"><br> <input type="submit" value="저장">
	</form>
	


	<br><table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>비밀번호</th>
				<th>삭제</th>
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

	
		let xhtp = new XMLHttpRequest(); //비동기방식 처리
		xhtp.open("get", 'memberJson.do');
		xhtp.send(); //send로 호출되야지 작업이 실행됨
		//클릭을 하면 함수를 실행시키겠습니다
		/* xhtp.onreadystatechange = callBackOne; */
		xhtp.onreadystatechange = callBackThree;
		
		let fields = ['id','name','mail','passwd'];
				
		function callBackThree() {
		      
		      if(this.readyState==4 && this.status==200) {
		         let data = JSON.parse(this.responseText); // object -> json 
		         console.log(data);
		         
		         let tbody = document.getElementById('list');
		       //데이터 건수 반복
		         for(let obj of data) {
		            tr = makeTr(obj);
		            tbody.append(tr);            
		         }
		   }
		   }
		
		
		//end fo callBackTree
		function makeTr(obj){
			//tr,td,td,td,td	
			let tr = document.createElement('tr');
			
			//필드 개수 반복
			for(let field of fields){
				let td1 = document.createElement('td');
				td1.innerText = obj[field];
				
				tr.append(td1);	
			}
			
			//삭제버튼
			let td1 = document.createElement('td');
			let btn = document.createElement('button');
			btn.innerText = '삭제';
			//클릭이벤트
			btn.addEventListener('click',deleteCallBack);
			td1.append(btn);
			tr.append(td1);
			
			return tr;
		}
			
		function deleteCallBack(e){
			//삭제 버튼을 누르면 발생하는 이벤트가 this
			console.log(this);//이벤트에 사용되는 call함수에선 this가 이벤트를 받는 대상
			
			
			console.log(this.parentElement.parentElement.firstElementChild.innerText) //아이디만 추출해오는것
			let delId = this.parentElement.parentElement.firstElementChild.innerText; //td->tr 첫번째 택스트
			
			let delAjx = new XMLHttpRequest();
			//입력방식은 post 방식
			delAjx.open('post','removeMemberAjax.do');
			delAjx.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			delAjx.send('id='+delId);
			
			delAjx.onload = function(){
				let result = JSON.parse(delAjx.responseText);
				if(result.retCode == 'Success'){
					
				e.target.parentElement.parentElement.remove(); //위에 117 this 값을 가져오기 위해서 e.target으로 가져와야함
				alert('삭제완료');
				}else{
					alert('처리중 에러 발생');
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
		
 	//form 전송이벤트가 실행. ajax 호출 forms object 중에서 onsubmit이 실행되면 함수가 실행됨
		document.forms.addFrm.onsubmit = function(e) {
			//기본기능 차단
			 e.preventDefault();
			
			//action에 진행되는 값을 넣겠다(get)
			let url = document.addFrm.getAttribute('action');
			
			let id = document.forms.addFrm.id.value;
			let name = document.forms.addFrm.name.value;
			let pass = document.forms.addFrm.passwd.value;
			let mail = document.forms.addFrm.mail.value;
		
			let param = 'id='+id+'&name='+name+'&passwd='+pass+'&mail='+mail;
			//'id=id&name=name&passwd=passwd&mail=mail';
			
			//`id=${id}&name=${name}&passwd=${passwd}&mail=${mail}`
			console.log(param)
			
			let addAjx = new XMLHttpRequest();
			//입력방식은 post 방식
			addAjx.open('post',url);
			addAjx.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			addAjx.send(param); //id=user1&passwd=1234&name=Hong&mail=email@com
			
			
		addAjx.onload = function() {
            console.log(addAjx.responseText);
            let data = JSON.parse(addAjx.responseText); // json -> object
            // tbody태그의 id(list)
            document.getElementById('list').append(makeTr(data));
         };
			}
 	
			
		
		
	</script>
</body>



</html>