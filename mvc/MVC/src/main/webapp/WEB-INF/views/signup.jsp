<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* CSS 스타일 정의 */
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}
select[name="buyerAddress"] {
		width:100%;
    height: 50px;
    font-size: 20px; 
    	  text-align: center; /* 옵션 텍스트 중앙 정렬 추가 */
    	  margin-bottom: 20px
    
}
.container {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.header {
	text-align: center;
	padding: 20px;
}

h1 {
	color: #333;
}

p {
	font-size: 18px;
	color: #555;
}

.signup-form { /* 폼 클래스를 signup-form으로 변경 */
	background-color: #fff;
	padding: 20px;
	max-width: 600px; /* 폼의 최대 너비 설정 */
	width: 100%;
	border: 1px solid #ddd;
	box-shadow: 0px 0px 5px #ccc;
	margin-bottom: 20px; /* 버튼과의 간격 조절 */
}

span {
	display: block;
	margin-bottom: 10px;
	font-weight: bolder;
}

input[type="text"], input[type="password"] {
	width: 96%;
	height: 50px;
	padding: 10px;
	font-size: 20px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input:focus {
	border-color: #0982f0;
	outline: none;
}
select:focus {
	border-color: #0982f0;
	outline: none;
}

input[type="submit"] {
	font-weight: bolder;
	font-size: 18px;
	background-color: #2070eb;
	color: white;
	height: 50px;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	width: 100%;
}

a {
	color: #007bff;
	text-decoration: none;
}

options {
	display: flex;
	justify-content: flex-end;
	align-items: center;

}

.options label {
	margin-right: 20px;
}

.signup-link { /* 회원가입 링크 클래스를 추가 */
	margin-top: 20px;
	text-align: right;
}
</style>
</head>
<body>
	<div class="header">
		<h1>회원가입</h1>
		<p>${message}</p>
	</div>

	<div class="container">
		<!-- 회원가입 폼 -->
		<form class="signup-form"
			action="<%=request.getContextPath()%>/signup" method="post">
			<span>아이디 :</span> <input type="text" name="buyerId" placeholder="ID">
			<br>
			<span>비밀번호 :</span> <input type="password" name="buyerPw" placeholder="PASSWORD">
			<br>
			<span>이름 :</span>
				<input type="text" name="buyerName" placeholder="이름">
			<br>
			<span>지역:</span>
			<select name="buyerAddress">
				<option value="서울">서울</option>
				<option value="부산">부산</option>
				<option value="광주">광주</option>
			</select> <input type="submit" value="회원가입">
		</form>

		<p>
			<a href="<%=request.getContextPath()%>/login">로그인 화면으로 돌아가기</a>
		</p>
		<p>
			<a href="<%=request.getContextPath()%>/home">홈으로 돌아가기</a>
		</p>
	</div>
</body>
</html>