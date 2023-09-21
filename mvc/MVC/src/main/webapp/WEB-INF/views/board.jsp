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
	
	// 수정
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
	
	<div class="navbar">
			<div class="left">
				<button value="0" onclick="sendList(0)">삼성</button>
				<button value="1" onclick="sendList(1)">애플</button>
				<button value="2" onclick="sendList(2)">전체</button>
				<button onclick="loadCart()" class="cart-button">장바구니</button>
				<button onclick="window.location.href='<%=request.getContextPath()%>/board'">게시판</button>
				<button onclick="window.location.href='<%=request.getContextPath()%>/home'">홈화면</button>
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

