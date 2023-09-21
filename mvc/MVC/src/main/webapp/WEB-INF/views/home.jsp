<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 페이지</title>
<!--  <link href="<%=request.getContextPath()%>/css/home.css" rel="stylesheet" >-->
<style>
body {
	margin: 0;
	padding: 0;
}

.header {
	height: 100px;
	background-color: blue;
	color: white;
	text-align: center;
	line-height: 50px;
	padding: 10px;
}

.navbar {
	height: 70px;
	background-color: gray;
	color: white;
	line-height: 20px;
}

.navbar ul {
	list-style-type: none;
	padding: 0;
	display: flex;
	justify-content: space-between;
}

.navbar .left {
	
}

.navbar .right {
	
}

.navbar li {
	display: inline;
	margin-right: 10px;
}

.body {
	height: 500px;
	background-color: bluesky;
	text-align: center;
	padding-top: 50px;
}

.boardZone{

}
.tableStripedTop{
	text-align: center;
	border: 1px solid darkgray;
	
}

.tr1{
	background-color: gray; 
	text-align: center;
}

.footer {
	height: 200px;
	background-color: yellowgreen;
	color: black;
	text-align: center;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>

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
									<button onclick="loadCart()">장바구니</button>
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
				if(data.length != 0)
					$("#cart_price").append('<a href = "/MVC/all">주문하기</a>');
			},
			error : function() {
				//
				alert("요청에 실패했습니다");
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
</script>

</head>


<body>
	<div class="header">
		<h1>핸드폰창고</h1>
	</div>
	<div class="navbar">
			<div class="left">
				<button value="0" onclick="sendList(0)">삼성</button>
				<button value="1" onclick="sendList(1)">애플</button>
				<button value="2" onclick="sendList(2)">전체</button>
				<button onclick="window.location.href='<%=request.getContextPath()%>/board'">게시판</button>
			</div>
			<ul class="right">
				<%
				String inputId = (String) session.getAttribute("Id");
				%>
				<%
				if (inputId == null) {
				%>
				<li><a href="<%=request.getContextPath()%>/login">로그인</a></li>
				<%
				} else {
				%>
				<li><a href="<%=request.getContextPath()%>/logout">로그아웃</a></li> <%
				} %>
				<li> <a href="<%=request.getContextPath() %>/signup">회원가입</a></li>
			</ul>
	</div>
	
	<section>
		
			<table id="result">
				<!-- 여기에 동적으로 생성된 테이블 내용이 들어갈 것입니다. -->
			</table>
		 	<h3 style = "color : red" id = cart_count></h3><br>
		 	<h3 id = cart_price></h3><br>
	</section>
    
	<div class="footer">
		<p>핸드폰판매 고객센터 이용약관 쇼핑몰 이용약관 개인정보 처리방침 회사정보 회사명에이콘통신 대표 조은경</p>
		<p>사업자번호181-22-01015 주소 서울특별시 마포구 양화로 122 4층 개인정보관리책임자 박태민 이메일</p>
		<p>ekzzang@naver.com 판매제휴업체 SKT - 밀수 / KT - 밀수 / LGU+ - 밀수 대표
			김병진,김민규</p>
		<p>사업자번호845-82-01440 통신판매업신고번호 : 제2023-서울마포-0015호 </p>
	</div>
</body>
</html>

