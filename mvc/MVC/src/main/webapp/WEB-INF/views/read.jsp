<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Board.boardDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Board.Question"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판( •̀ ω •́ )✧</title>
<link href="<%=request.getContextPath()%>/css/board.css"
	rel="stylesheet" />
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

	<div class="container">
		<div class="row">
			<table class="table tableStriped"
				style="text-align: center; border: 1px solid darkgray">
				<thead>
					<tr>
						<th style="background-color: gray; text-align: center;">번호</th>
						<th style="background-color: gray; text-align: center;">제목</th>
						<th style="background-color: gray; text-align: center;">작성자</th>
						<th style="background-color: gray; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
					

					Question q = (Question) request.getAttribute("list");
					%>

					<tr>
						<td><%=q.getQuestionCode()%></td>
						<td class="tdTitle"><%=q.getTitle()%></td>
						<td class="tdId"><%=q.getBuyerId()%></td>
						<td><%=q.getWriteDate()%></td>
					</tr>

					<tr>
						<td>내용</td>
						<td><%=q.getQuestionContents()%></td>
					</tr>

				</tbody>
			</table>

			<a href="<%=request.getContextPath()%>/write"
				class="btn btnPrimary pullRight">글쓰기</a>
		</div>

	</div>


	<div class="footer">
		<p>핸드폰판매 고객센터 이용약관 쇼핑몰 이용약관 개인정보 처리방침 회사정보 회사명에이콘통신 대표 조은경</p>
		<p>사업자번호181-22-01015 주소 서울특별시 마포구 양화로 122 4층 개인정보관리책임자 박태민 이메일</p>
		<p>ekzzang@naver.com 판매제휴업체 SKT - 밀수 / KT - 밀수 / LGU+ - 밀수 대표
			김병진,김민규</p>
		<p>사업자번호845-82-01440 통신판매업신고번호 : 제2023-서울마포-0015호</p>


	</div>
</body>
</html>

