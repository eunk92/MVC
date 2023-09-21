<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="order.Order"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<style>
* {
	margin: 0px auto;
	text-align: center;
}

#title {
	line-height: 200px;
	border: 1px solid black;
}

table {
	margin-top: 20px;
	border-collapse: collapse;
}

tr {
	border: 1px solid black;
}

td {
	width: 100px;
	border: 1px solid black;
}
</style>
<script>
function orderClick(ordernum,date,good,id,address,total){
	$("#ordertbl").empty();
	$("#ordertbl").append("주문번호 : " + ordernum + "<br>"
	        +"주문날짜 : " + date + "<br>"
	        +"주문상품 : " + good + "<br>"
	        +"I D : " + id + "<br>"
	        +"주 소 : " + address + "<br>"
	        +"주문가격 : " + (total*1000).toLocaleString() + "원<br><br>"
	        +'<Button onclick="reload()">닫기</Button>');
}
	function reload(){
		 location.reload();
	}
</script>
</head>
<body>
	<%
	ArrayList<Order> list = (ArrayList<Order>) request.getAttribute("list");
	System.out.println(list);
	%>
	<div id=title>
		<h1>
			주문이 완료 되었습니다.
			<h1>
	</div>
	<a href="/MVC/home">홈으로</a>
	<br>
	<br>

	<h3>주문내역</h3>
	<table id="ordertbl">
		<tr>
			<td>주문번호</td>
			<td>날짜</td>
			<td>주문상품</td>
			<td>ID</td>
			<td>주소</td>
			<td>주문금액</td>
		</tr>

		<%
		for (Order item : list) {
		%>
		<tr
			onclick="orderClick(<%=item.getOrderNum()%>,'<%=item.getOrderDate()%>','<%=item.getGoodsName()%>','<%=item.getBuyerId()%>','<%=item.getAddress()%>',<%=item.getTotal()%>)">
			<td><%=item.getOrderNum()%></td>
			<td><%=item.getOrderDate()%></td>
			<td><%=item.getGoodsName()%></td>
			<td><%=item.getBuyerId()%></td>
			<td><%=item.getAddress()%></td>
			<td><fmt:formatNumber value="<%=item.getTotal() * 1000%>"
					pattern="#,###" /></td>
		</tr>
		<%
		}
		%>
	</table>
	<br>
</body>
</html>