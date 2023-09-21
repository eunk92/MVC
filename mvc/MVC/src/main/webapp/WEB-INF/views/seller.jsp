<%@page import="Page.PageHandler"%>
<%@page import="goods.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
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

/* 표 스타일 */
table {
	max-width: 800px;
	width: 100%;
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
	margin: 0 auto; /* 좌우 여백을 가운데 정렬을 위해 수정 */
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

.logout {
	margin-right: -722px;
	margin-top: 20px;
}

.logout a {
	color: #007bff;
	text-decoration: none;
}
</style>
</head>
<body>

	<%
	PageHandler handler = (PageHandler) request.getAttribute("handler");
	ArrayList<Goods> list = (ArrayList<Goods>) request.getAttribute("list");
	%>
	<div class="logout">
		<a href="<%=request.getContextPath()%>/logout">로그아웃</a>
	</div>
	<div class="header">
		<h1>상품 관리</h1>
	</div>

	<div class="button-container">
		<!-- 수정된 버튼 컨테이너 -->
		<form action="/MVC/goodsRegister">
			<button>등록</button>
		</form>
		<form action="/MVC/goodsAlter">
			<button>수정</button>
		</form>
	</div>

	<table border="1">
		<tr>
			<th>번호</th>
			<th>상품 코드</th>
			<th>브랜드</th>
			<th>상품명</th>
			<th>가격</th>
			<th>재고</th>
			<th>관리</th>
		</tr>

		<%
		for (Goods g : list) {
		%>
		<tr>
			<td><%=g.getNum()%></td>
			<td><%=g.getGoodsCode()%></td>
			<td><%=g.getGoodsBrand()%></td>
			<td><%=g.getGoodsName()%></td>
			<td><%=g.getGoodsPrice()%></td>
			<td><%=g.getGoodsStock()%></td>
			<td><a
				href="/MVC/goodsDelete?goodsCode=<%=g.getGoodsCode()%>"><button>삭제</button></a>
			</td>
		</tr>
		<%
		}
		%>
	</table>

	<div class="page-links">
		<%
		int index = handler.getGrpStartPage();

		if (handler.getCurrentGrp() > 1) {
		%>
		<a href="/MVC/seller.do?p=<%=index - 1%>">[이전]</a>
		<%
		}
		while (index <= handler.getGrpEndPage()) {
		%>
		<a href="/MVC/seller.do?p=<%=index%>">[<%=index%>]
		</a>
		<%
		index++;
		}
		if (handler.getGrpEndPage() < handler.getTotalPage()) {
		%>
		<a href="/MVC/seller.do?p=<%=handler.getGrpEndPage() + 1%>">[다음]</a>
		<%
		}
		%>
	</div>
</body>
</html>