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
</style>
</head>
<body>
	<div class="header">
		<h1>핸드폰창고</h1>
	</div>
	<div class="navbar">
		<ul>
			<div class="left">
				<li>삼성</li>
				<li>애플</li>
				
				<li><a href="<%=request.getContextPath()%>/board">게시판</a></li>
				<li><a href="<%=request.getContextPath()%>/home">전체</a></li>
			</div>
			<div class="right">
				<li><a href="">로그인</a></li>
				<li><a href="">회원가입</a></li>
			</div>
		</ul>
	</div>

	<div class="body">
		<div class="writeForm">
			<h2>글쓰기</h2>
			<form action="<%=request.getContextPath()%>/write" method="post">
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

	<div class="footer">
		<p>핸드폰판매 고객센터 이용약관 쇼핑몰 이용약관 개인정보 처리방침 회사정보 회사명에이콘통신 대표조은경</p>
		<p>사업자번호181-22-01015 주소 서울특별시 마포구 양화로 122 4층 개인정보관리책임자 박태민 이메일</p>
		<p>ekzzang@naver.com 판매제휴업체 SKT - 밀수 / KT - 밀수 / LGU+ - 밀수 대표
			김병진,김민규</p>
		<p>사업자번호845-82-01440 통신판매업신고번호 : 제2023-서울마포-0015호</p>
	</div>
</body>
</html>

