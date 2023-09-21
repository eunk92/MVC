<%@page import="goods.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
/* 전체 페이지 스타일 */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

/* 헤더 스타일 */
.header {
	text-align: center;
	padding: 20px;
}

/* 제목 스타일 */
h1 {
	color: #333;
}

a {
	color: #007bff;
	text-decoration: none;
}
/* 표 스타일 */
table {
	max-width: 100%;
	width: 400px;
	border-collapse: collapse;
	box-shadow: 0px 0px 5px #ccc;
	margin-top: 20px;
	margin-bottom: 20px; /* 아래 여백 추가 */
}

table th, table td {
	padding: 10px;
	border: 1px solid #ddd;
}

table th {
	background-color: #f2f2f2;
	font-weight: bold;
}

table td {
	text-align: center;
}

/* 버튼 스타일 */
.button-container {
	display: flex;
	justify-content: flex-end;
	max-width: 800px;
	width: 100%;
	margin: 0 auto;
}

button {
	background-color: #2070eb;
	color: white;
	border: none;
	padding: 5px 20px;
	font-size: 14px;
	font-weight: bold;
	border-radius: 4px;
}

button:hover {
	cursor: pointer;
}

.button-container button {
	background-color: #2070eb;
	width: 67px;
	height: 28px;
	color: white;
	font-weight: bold;
	font-size: 13px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	padding: 5px 10px;
	margin: 5px;
}

/* 페이지 링크 스타일 */
.page-links {
	margin-top: 20px;
	font-size: 17px;
}

.page-links a {
	margin: 0 10px;
	color: #007bff;
	text-decoration: none;
	font-weight: bold;
}

/* 수정 폼 스타일 */
.edit {
	height: 450px;
	margin: 40px;
	box-shadow: 0px 0px 5px #ccc;
	margin-top: 13px;
	width: 300px;
	font-weight: bold;
}

.edit_1 {
	justify-content: center;
	text-align: center;
margin-top: 9px;
}

.input_box {
	margin-top: 10px;
	margin-bottom: 15px;
	text-align: left;
	margin-left: 10px;
}

input:focus {
	border-color: #2070eb; /* 포커스 시 테두리 색상 설정 */
	outline: none; /* 포커스 아웃라인 제거 */
}

.input_box input[type="text"] {
	width: 90%;
	height: 15px;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	display: block; /* 인라인 요소를 블록 요소로 변경하여 수직 정렬을 맞출 수 있습니다 */
}

/* 수정 폼 요소 스타일 */
select {
	MARGIN-TOP: 30PX;
	border: 1px solid #ccc;
	border-radius: 4px;
	width: 100px;
	height: 20px;
	margin-top: 20px;
	margin-left: 40px;
	text-align: center;
}

/* 수정 버튼 스타일 */
.edit-form button {
	background-color: #2070eb;
	color: white;
	font-weight: bold;
	font-size: 14px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	padding: 10px 20px;
}

.back {
	display: flex;
	justify-content: right;
	margin-right: 40px;
}

.back button {
	width: 135px;
}

.button1 {
	margin-right: 30px;
}

.logout {
	margin-right: -722px;
	margin-top: 20px;
}

.logout a {
	color: #007bff;
	text-decoration: none;
}
p{
color: #007bff;
}
</style>
</head>
<body>
	<div class="logout">
		<a href="<%=request.getContextPath()%>/logout">로그아웃</a>
	</div>
	<div class="header">
		<h1>재고 등록</h1>
	</div>
	<%
	ArrayList<Goods> list = (ArrayList<Goods>) request.getAttribute("list");
	%>


	<div style="display: flex; justify-content: space-between;">
		<div style="flex: 1;">
			<table border="1">
				<tr>
					<th>상품 코드</th>
					<th>브랜드</th>
					<th>상품명</th>
					<th>가격</th>
					<th>재고</th>
				</tr>

				<%
				for (Goods g : list) {
				%>
				<tr>
					<td><%=g.getGoodsCode()%></td>
					<td><%=g.getGoodsBrand()%></td>
					<td><%=g.getGoodsName()%></td>
					<td><%=g.getGoodsPrice()%></td>
					<td><%=g.getGoodsStock()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>

		<div class="edit_1" style="flex: 1;">
			<!-- 디코드 부분 이쪽은 교수님이 작성해주셔서 흐름만 읽으시오 -->
		<%String messageParam = request.getParameter("message");
		String decodedMessage="상품 재고 등록";
		 if (messageParam != null) {           
	            decodedMessage = java.net.URLDecoder.decode(messageParam, "utf-8");
	 } %>
			<p> <%=decodedMessage%></p>
			<form class="edit" action="/MVC/goodsRegister" method="post">
				<!-- 상품코드 입력란에 id 추가 -->
				<div class="input_box">
					브랜드 <select name="goodsBrand">
						<option value="0">0:삼성</option>
						<option value="1">1:애플</option>
					</select><br>
				</div>
				<div class="input_box">
					상품코드 <input type="text" name="goodsCode" id="code"><br>
				</div>
				<div class="input_box">
					상품명 <input type="text" name="goodsName" id="name"><br>
				</div>
				<div class="input_box">
					가격 <input type="text" name="goodsPrice" id="price"><br>
				</div>
				<div class="input_box">
					재고 <input type="text" name="goodsStock" id="stock"><br>
				</div>
				<button>등록</button>

			</form>
			<div class="back">
				<form class="button1" action="/MVC/seller.do">
					<button>판매자 페이지</button>
				</form>
				<form action="/MVC/goodsAlter">
					<button>수정 페이지</button>
				</form>
			</div>

		</div>
	</div>

</body>
</html>