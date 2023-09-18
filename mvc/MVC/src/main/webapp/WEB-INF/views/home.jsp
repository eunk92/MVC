<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	margin: 0;
	padding: 0;
}

.header {
	height: 150px;
	background-color: pink;
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
<title>웹 페이지</title>
</head>

<body>



			      <%
            String inputId = (String) session.getAttribute("Id"); %>
        <%    if (inputId == null) {
            %>
            <a href="<%=request.getContextPath()%>/login">로그인</a>
            <%
            } else {
            %>
            <a href="<%=request.getContextPath()%>/logout">로그아웃</a>
            <%
            }
            %>
            <a href="<%=request.getContextPath() %>/signup">회원가입</a>
	

	<div class="footer">
		<p>핸드폰판매 고객센터 이용약관 쇼핑몰 이용약관 개인정보 처리방침 회사정보 회사명에이콘통신 대표조은경</p>
		<p>사업자번호181-22-01015 주소 서울특별시 마포구 양화로 122 4층 개인정보관리책임자 조은경 이메일</p>
		<p>ekzzang@naver.com 판매제휴업체 SKT - 밀수 / KT - 밀수 / LGU+ - 밀수 대표
			김병진,김민규</p>
		<p>사업자번호845-82-01440 통신판매업신고번호 : 제2023-서울마포-0015호</p>


	</div>
</body>
</html>

