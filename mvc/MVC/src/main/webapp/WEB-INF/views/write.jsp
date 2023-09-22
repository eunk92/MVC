<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/board.css" rel="stylesheet" />

<title>글쓰기</title>
<style>
body {

    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
}

.header {

	width : 100%;
    background-color: #2e3b4e;
    color: white;
    text-align: center;
    padding: 10px 0;
}

.navbar {

display: flex;
    background-color:  #2e3b4e;;
    color: white;
    text-align: center;
    padding: 10px 0;
}

.navbar ul {
    list-style-type: none;
    padding: 0;
    display: flex;
    justify-content: space-between;
    flex-direction: row; /* 추가된 부분 */
}

.navbar .left {
display: flex;
    justify-content: flex-start;
}

.navbar .left button {
    background-color: #2e3b4e;
    color: white;
    border: none;
    padding: 5px 15px;
    font-size: 16px;
    font-weight: bold;
    border-radius: 5px;
    cursor: pointer;
    margin-right: 10px;
}

.navbar .right {
display: flex;
    justify-content: flex-end;

    margin-left: auto; /* 추가된 부분 */
}


.navbar .right li {
    display: inline;
    margin-right: 10px;
}

.body {
	height:100%;
    background-color: #fff;
    text-align: center;
    padding: 50px 0;
}

table {
    max-width: 100%;
    width: 80%; /* 테이블의 최대 너비의 80%로 설정 */
    border-collapse: collapse;
    box-shadow: 0px 0px 5px #ccc;
    margin: 20px auto;
}

table th, table td {
    padding: 8px; /* 셀 안의 내용과 여백(padding)을 조정 */
    border: 1px solid #ddd;
}

table th {
    background-color: #f2f2f2;
    font-weight: bold;
    text-align: center;
}

table td {
    text-align: center;
}

/* 장바구니 테이블 내용 스크롤 가능하도록 수정 */
section {
    height: 600px;
    overflow-y: auto;
}

.button-container {
    display: flex;
    justify-content: flex-end;
    max-width: 90%;
    margin: 0 auto;
}

button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    font-weight: bold;
    border-radius: 5px;
    cursor: pointer;
}


.footer {
    width: 100%;
    height: 200px;
    background-color: #2e3b4e;
    color: white;
    text-align: center;
    padding-top: 10px
}
a {
    color: white;
    text-decoration: none;
}
.wrap{
	height: 800px;
}
.writeForm {
    background-color: white;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0px 0px 5px #ccc;
    text-align: left;
	width:800px;
    margin: 0 auto;
    justify-content:center;
    display: flex; /* 아이템을 가로로 배치하기 위해 추가 */
    flex-direction: column; /* 아이템을 세로로 배치하기 위해 추가 */
}




.writeLabel {
    font-size: 20px;
    font-weight: bold;
}

#title, #content {
    width: 780px;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 18px;
}
#content{
height: 300px;
}

.writeTextarea {
    resize: none;
}

.writeButton {
    background-color: #007bff;
    color: white;
    border: none;
    width: 100px;
    padding: 10px 20px;

    font-weight: bold;
    border-radius: 5px;
    cursor: pointer;
    margin: 0 auto; /* 가로 중앙 정렬을 위해 추가 */
    display: block; /* 블록 요소로 변경하여 가운데 정렬 */
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
				
				$("#boradResult").empty(); // 화면 전체 지우기
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
		<h1 onclick="window.location.href='<%=request.getContextPath()%>/home'" style="cursor:pointer;">Resell SHOP</h1>
	
	<div class="navbar">
			<div class="left">
				<button value="0" onclick="sendList(0);">삼성</button>
				<button value="1" onclick="sendList(1)">애플</button>
				<button value="2" onclick="sendList(2)">전체</button>
				<button onclick="loadCart()" class="cart-button">장바구니</button>
				<button onclick="window.location.href='<%=request.getContextPath()%>/board'">게시판</button>
				<!-- <button onclick="window.location.href='<%=request.getContextPath()%>/home'">홈화면</button> -->
			</div>
			<ul class="right">
				<%
				String inputId = (String) session.getAttribute("Id");
				%>
				<%
				if (inputId == null) {
				%>
				<li><a href="<%=request.getContextPath()%>/login">로그인</a>   |
				 <a href="<%=request.getContextPath() %>/signup">    회원가입</a> </li>
				<%
				} else {
				%>
				<li><a href="<%=request.getContextPath()%>/logout">로그아웃</a> <%
				} %>
				
			</ul>
			</div>
	</div>



	<section>
		<div class="body" id="boradResult">
			<div class="writeForm">
				<h2>글쓰기</h2>
				<form action="<%=request.getContextPath()%>/write.do" method="post">
					<label class="writeLabel" for="title">제목:</label><br> <input type="text" id="title" name="title"><br>
					<br> <label class="writeLabel" for="content">내용:</label><br>
					<textarea id="content" name="content" class="writeTextarea" maxlength="300"></textarea>
					<br>
					<br> <input type="submit" value="글쓰기" class="writeButton")>
				</form>
	
	
				<%
				String title = request.getParameter("title");
				String content = request.getParameter("content");
	
				if (title != null && content != null) {
					HttpSession se = request.getSession();
					ArrayList<String> titles = (ArrayList<String>) se.getAttribute("titles");
					ArrayList<String> contents = (ArrayList<String>) se.getAttribute("contents");
	
					if (titles == null) {
						titles = new ArrayList<>();
					}
	
					if (contents == null) {
						contents = new ArrayList<>();
					}
	
					titles.add(title);
					contents.add(content);
	
					session.setAttribute("titles", titles);
					session.setAttribute("contents", contents);
				}
				%>
			</div>
		</div>
	
		<table id="result">
		</table>	
	</section>
		
	
	<div class="footer">
		<p>핸드폰판매 고객센터 이용약관 쇼핑몰 이용약관 개인정보 처리방침 회사정보 회사명에이콘통신 대표조은경</p>
		<p>사업자번호181-22-01015 주소 서울특별시 마포구 양화로 122 4층 개인정보관리책임자 박태민 이메일</p>
		<p>ekzzang@naver.com 판매제휴업체 SKT - 밀수 / KT - 밀수 / LGU+ - 밀수 대표
			김병진,김민규</p>
		<p>사업자번호845-82-01440 통신판매업신고번호 : 제2023-서울마포-0015호</p>
	</div>
</body>
</html>

