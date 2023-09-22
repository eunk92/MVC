
	// 게시판 
	// 게시판 한건 조회
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
				let str = "<tr id=\"boardTr1\">"
						+ 	"<td id=\"boardNum\">"+item.num+"</td>"
						+ 	"<td id=\"boardTitle\">"+item.title+"</td>"
						+ 	"<td id=\"boardId\">"+item.buyerId+"</td>"
						+ "</tr>"
						+ "<tr id=\"boardTr2\">"
						+ 	"<td id=\"boardContent\" colspan=\"3\">"+item.quetionContents+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ 	"<td colspan=\"3\">"
						+ 		"<button class=\"btn3\" onclick=\"modifyBtn("+item.questionCode+")\" style=\"padding:0;\">수정</button>"
						+ 		"<button class=\"btn3\" onclick=\"confirmDelete("+item.questionCode+")\" style=\"padding:0;\">삭제</button>"
						//+ 		"<button class=\"btn3\" onclick=\"deleteSend("+item.questionCode+")\">삭제</button>"
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
	
	function confirmDelete(questionCode) {
		let result = confirm('게시글을 삭제하시겠습니까?');
	    if (result) {
	        deleteSend(questionCode);
	    }else{
	    	window.location.reload();
	    }
		
	}
	
	// 게시판 글 삭제
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
	
	
	//게시판 글 수정 화면
	function modifyBtn(questionCode){
		
		let boardNum = document.getElementById("boardNum").innerHTML;
		let boardTitle = document.getElementById("boardTitle").innerHTML;
		let boardId = document.getElementById("boardId").innerHTML;
		let boardContent = document.getElementById("boardContent").innerHTML;
		
		let str = "<tr id=\"boardTr1\">"
				+ 	"<td id=\"boardNum\">"+boardNum+"</td>"
				+ 	"<td id=\"boardTitle\"><input id=\"inputTitle\" type=\"text\" value=\""+boardTitle+"\"></td>"
				+ 	"<td id=\"boardId\">"+boardId+"</td>"
				+ "</tr>"
				+ "<tr id=\"boardTr2\">"
				+ 	"<td id=\"boardContent\" colspan=\"3\"><input id=\"inputContent\" type=\"textarea\" value=\""+boardContent+"\"></td>"
				+ "</tr>"
				+ "<tr>"
				+ 	"<td colspan=\"3\">"
				+ 		"<button class=\"btn3\" onclick=\"modifysend('"+questionCode+"')\">수정</button>"
				+ 		"<button class=\"btn3\">취소</button>"
				+ 	"</td>"
				+ "</tr>";
			
		$("#result").empty();
		$("#result").append(str);
	}
	
	
	
	//게시판 글 수정 
	function modifysend(questionCode){
		
		let table = document.getElementById("result");
		
		let boardTitle = table.querySelector("#inputTitle").value;
		let boardContent = table.querySelector("#inputContent").value;
		
		$.ajax({
			type : "get",
			dataType : "text", // 서버에서 넘어오는 데이터의 타입
			url: "/MVC/update",
			data: "questionCode=" + questionCode + "&boardTitle=" + boardTitle + "&boardContent=" + boardContent,
			success: function(data){
				alert("수정되었습니다.");
				$("#result").empty();
				window.location.href="/MVC/board";
			},
			error:function(){
				alert("요청 실패");
			}
		});
	}
	