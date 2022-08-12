/**
 * cart.js
 JSPTest/cart/cart.jsp
 */
function cartList(result){
	console.log(result) //콘솔에서 아까 입력한 두건의 값이 잘 들어오는지 확인 (2) []{...},{...}] result 값을 호출함
	
	let cartTemplate = document.querySelector('#template'); //템플릿이라는 아이디 값을 가져오고싶음 cart.jsp 끝부분 87쯤에 있음
	let basket = document.querySelector('#basket');
	
	for(let i =0; i<result.length; i++){ 
		 					//chilNodes는 빈공간도 0인식하고 그다음 div 태그를 1임
		let rowDiv = cartTemplate.childNodes[1].cloneNode(true); //두번째 자식의 값을 카피하겠습니다. template의 div.row data 태그를 가져오겠다
		console.log(rowDiv);
		
		rowDiv.setAttribute('data-id',result[i].no); //result가 가지고 있는 값 중에 no를 div data_id 에 넣겠다
		rowDiv.querySelector('div.pname span').textContent = result[i].productNm;
		rowDiv.querySelector('div.basketprice input[name="p_price"]').value = result[i].price;
		rowDiv.querySelector('div.basketprice').childNodes[2].textContent = result[i].price; //히든 속성이라서 3번째 값을 텍스트로 변환하겠다(가격변경)
		rowDiv.querySelector('div.num input').value = result[i].qty; //num 밑에 input에 수량을 넣어주겠다
		rowDiv.querySelector('div.sum').textContent = (result[i].price * result[i].qty);
		rowDiv.querySelector('div.num>div.updown>input:nth-of-type(1)').setAttribute('id','p_num'+result[i].no);   
	    rowDiv.querySelector('div.num>div.updown>input:nth-of-type(1)').setAttribute('onkeyup','javascript:changePnum('+result[i].no+')');   
	    rowDiv.querySelector('div.num>div.updown>span:nth-of-type(1)').setAttribute('onclick','javascript:changePnum('+result[i].no+')');   
	    rowDiv.querySelector('div.num>div.updown>span:nth-of-type(2)').setAttribute('onclick','javascript:changePnum('+result[i].no+')');   
	      
	    basket.append(rowDiv);
	}
	
}
 
 function makeList(){
	//ajax.호출 XMLHttpRequest랑 fetch랑 같음
	fetch('../cartList.do')
	//성공시 발생
	.then(result => result.json()) 
	.then(cartList) //cartList 함수를 호출
	//실패시, 예외시 발생
	.catch(err => console.log(err))

}


function changePNum(no) {

   let currentItem = event.target; //이벤트를 받는 대상 가져옴

   let currentQty =  currentItem.closest('.updown').childNodes[1].value;
   let currentPrice= currentItem.closest('.subdiv').childNodes[1].childNodes[1].value;
   console.log(currentQty,currentPrice);
   
   if(currentItem.classList[2]=='down') {
      changeQty=parseInt(currentQty) -1;
      currentItem.closest('.updown').childNodes[1].value=changeQty;}
   else {
      changeQty = parseInt(currentQty) +1;
      currentItem.closest('.updown').childNodes[1].value=changeQty;   }
      
   fetch('../cartUpdate.do', {
      method:'post',
      headers: {'Content-type': 'application/x-www-form-urlencoded'},
      body:'no='+no+'&qty='+changeQty
   })   
   .then(result=>result.text())
   .then(result=>console.log(result))   
   .catch(err=>console.log(err))   
}


makeList(); 

