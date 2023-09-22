<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 페이지</title>


<link href="<%=request.getContextPath()%>/css/home.css" rel="stylesheet" type="text/css" />

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
	    background-image: url("./img/imgs.jpg");
	    background-position: center;
	    background-size: cover;
	    opacity: 0.5;
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
	
	
	
	
	#cart_count{
	text-align: center;
	}
	#cart_price{
	text-align: center;
	}
	#order{
	 background-color: #2e3b4e;
	    color: white;
	    border: none;
	    padding: 5px 15px;
	    
	    font-size: 16px;
	    font-weight: bold;
	    border-radius: 5px;
	    cursor: pointer;
	    margin-left: 20px;
	}
</style>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src ="<%=request.getContextPath()%>/script/homeScript.js"></script>

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

	
	<section  id="back">
	
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

