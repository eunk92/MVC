<%@page import="board.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판( •̀ ω •́ )✧</title>

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
	
	
	function send( obj){
		
		let tr  = obj.parentElement; // 클릭한 요소의 부모 요소 받아오기
		let td  = tr.querySelector("#td1").innerHTML;
		
		$.ajax({
			type : "post",
			dataType : "json",
			url: "/MVC/board",
			data: "code=" + td,
			success: function(data){
				let item = data;
				let str = "<tr>"
						+ "<td>"+item.questionCode+"</td>"
						+ "<td>"+item.title+"</td>"
						+ "<td>"+item.buyerId+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td colspan=\"3\">"+item.quetionContents+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ 	"<td colspan=\"3\">"
						+ 		"<button onclick=\"modifySend("+item.questionCode+")\">수정</button>"
						+ 		"<button onclick=\"deleteSend("+item.questionCode+")\">삭제</button>"
						+ 	"</td>"
						+ "</tr>";
				$("#result").empty();
				$("#result").append(str);
			},
			error:function(){
				alert("요청 실패");
			}
		});
	}
	
	
	
	function deleteSend(deletecode){
		$.ajax({
			type : "get",
			dataType : "text", // 서버에서 넘어오는 데이터의 타입
			url: "/MVC/delete",
			data: "deletecode=" + deletecode,
			success: function(data){
				alert("삭제되었습니다.");
				$("#result").empty();
				window.location.reload(); // 새로고침
			},
			error:function(){
				alert("요청 실패");
			}
		});
	}
	
	function modifySend(deletecode){
		$.ajax({
			type : "get",
			dataType : "text", // 서버에서 넘어오는 데이터의 타입
			url: "/MVC/update",
			data: "deletecode=" + deletecode,
			success: function(data){
				alert("삭제되었습니다.");
				$("#result").empty();
				window.location.reload(); // 새로고침
			},
			error:function(){
				alert("요청 실패");
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
	
	<section class="container">
		<div class="row">
			<table class="table tableStriped" style="text-align: center; border: 1px solid orange">
				<thead>
					<tr>
						<th style="background-color: gray; text-align: center;">번호</th>
						<th style="background-color: gray; text-align: center;">제목</th>
						<th style="background-color: gray; text-align: center;">작성자</th>
						<th style="background-color: gray; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<% ArrayList<Question> list = (ArrayList<Question>)request.getAttribute("questionList"); %>
					<%for(int i=0; i<list.size(); i++) {%>
					<tr>
						<td id="td1" onclick="send(this)"><%=list.get(i).getQuestionCode()%></td>
						<td id="td2" onclick="send(this)"><%=list.get(i).getTitle() %></td>
						<td id="td3" onclick="send(this)"><%=list.get(i).getBuyerId() %></td>
						<td id="td4" onclick="send(this)"><%=list.get(i).getWriteDate() %></td>
					</tr>
				<%} %>

				</tbody>
			</table>
			
			<a href="<%=request.getContextPath()%>/write" class="btn btnPrimary pullRight">글쓰기</a>
				
			<table id="result">
			</table>	
			
		</div>
	</section>

	<div class="footer">
		<p>핸드폰판매 고객센터 이용약관 쇼핑몰 이용약관 개인정보 처리방침 회사정보 회사명에이콘통신 대표 조은경</p>
		<p>사업자번호181-22-01015 주소 서울특별시 마포구 양화로 122 4층 개인정보관리책임자 박태민 이메일</p>
		<p>ekzzang@naver.com 판매제휴업체 SKT - 밀수 / KT - 밀수 / LGU+ - 밀수 대표
			김병진,김민규</p>
		<p>사업자번호845-82-01440 통신판매업신고번호 : 제2023-서울마포-0015호</p>
	</div>
</body>
</html>

