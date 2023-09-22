
	function addToCart(itemcode) {
		
		$.ajax({
			type : "get",
			dataType : "json",
			url: "/MVC/addCart.do",
			data: "itemcode=" + itemcode,
			success: function(data){
				alert("상품이 장바구니에 추가되었습니다.");
			},
			
			error:function(){
				window.location.href="/MVC/login";
			}
		});
	}
	
	
	
	
	function sendList(code){
		let element = document.getElementById("back");
		element.style.backgroundImage = "none";
		element.style.opacity = "1";
		
		$.ajax({
			type : "get",
			dataType : "json",
			url: "/MVC/goods",
			data: "code=" + code,
			success: function(data){
				
				$("#result").empty(); // 화면 전체 지우기
				
				let str1 = `<thead>
								<tr>
									<th colspan="6">
									제품목록
									</th>
								</tr>
								<tr>
									<th>상품번호</th>
									<th>상품브랜드</th>
									<th>상품명</th>
									<th>상품가격</th>
									<th>상품재고</th>
									<th></th>
								</tr>
						   </thead>			 
						   <tbody>`;
				
				for( let i=0; i< data.length ; i++){
					let item = data[i];
			
			             str1 += "<tr>";
			             str1  +=   "<td>"+item.goodsCode+"</td>" ;
			             str1  +=   "<td>"+item.goodsBrand+"</td>" ;
			             str1  +=   "<td>"+item.goodsName+"</td>" ;
			             str1  +=   "<td>"+item.goodsPrice.toLocaleString()+"</td>";
			             str1  +=   "<td>"+item.goodsStock+"</td>" ;
			             str1  +=   "<td><button onclick=\"addToCart('"+item.goodsCode+"')\">담기</button></td>" ;
			             str1  += "</tr>";
				}	 
				
				str1  += "</tbody>";	
				$("#cart_count").empty();
				$("#cart_price").empty();
				$("#boradResult").empty();
				$("#result").append(str1); 
			},
			
			error:function(){
				alert("요청 실패");
			}
		});
	}	
	
	function loadCart() {	
		// 장바구니 목록을 불러오는 함수
		// $.ajax();  (  ) 인자정보를  객체타입으로 제공함 		
		$.ajax({
			type : "get",
			dataType : "json",
			url : "/MVC/cartlist.do",
			success : function(data) {
				if(data === null){
					alert("장바구니에 담긴 상품이 없습니다.");
					return;
				}
				$("#boradResult").empty();
				let total = 0; //총 금액
				$("#home").empty();
				$("#result").empty();
				
				$("#cart_count").empty();
				$("#cart_price").empty();
				$("#home").append('<a href = "/MVC/all">홈으로</a>');
				if(data.length != 0)
				$("#result").append("<tr>" + "<td>코드</td>" + "<td>브랜드</td>" + "<td>기종</td>"
						+ "<td>재고</td>" + "<td>가격(원)</td>"
						+ "</tr>");
				//카트 목록
				for (let i = 0; i < data.length; i++) {
					let item = data[i];
					let str = "<tr>" + "<td> " + item.goodsCode + "</td>" + "<td> "
							+ item.goodsName + "</td>" + "<td> " + item.goodsBrand
							+ "</td>" + "<td> " + item.goodsStock + "</td>"
							+ "<td> " + item.goodsPrice.toLocaleString() + "</td>"
							+ "<td><button onclick='deleteItem(" + i
							+ ")'>삭제</button></td>" + "</tr>";
					total += parseInt(item.goodsPrice);
					$("#result").append(str);
				}
				$("#cart_count").append("총 " + data.length + "개의 상품이 담겼습니다.");
				$("#cart_price").append("Total : " + total.toLocaleString() + "원 ");
				let totaldb = total/1000;
				if(data.length != 0)
					$("#cart_price").append('<a id="order" href = "/MVC/orderAdd?code='+data[0].goodsCode+"&total="+totaldb+'">주문하기</a>');
			},
			error : function() {
				alert("로그인이 필요한 서비스입니다.");
				window.location.href='/MVC/login';
			}
		});
	}
	
	//삭제 함수
	function deleteItem(index) {
		$.ajax({
			type : "get",
			dataType : "json",
			url : "/MVC/delCart",
			data : "index=" + index,
			success : function(data) {
				loadCart();
			},
			error : function() {
				alert("요청에 실패했습니다");
			}
		});
	}