<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Board.boardDAO" %>
<%@ page import="Board.Question" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 삭제</title>
    <link href="<%=request.getContextPath()%>/css/board.css" rel="stylesheet" />
</head>
<body>

    <div class="container">
        <h2>게시물 삭제</h2>
        <%
            String questionCodeParam = request.getParameter("questionCode");
            if (questionCodeParam != null) {
                int questionCode = Integer.parseInt(questionCodeParam);
                boardDAO boardDAO = new boardDAO();
                boolean success = boardDAO.
                if (success) {
        %>
                    <p>게시물이 성공적으로 삭제되었습니다.</p>
                    <p><a href="<%=request.getContextPath()%>/board">게시판으로 돌아가기</a></p>
        <%
        } else {
        %>
                    <p>게시물 삭제 중 오류가 발생했습니다.</p>
        <%
        }
        } else {
        %>
                <p>올바른 요청이 아닙니다.</p>
        <%
        }
        %>
    </div>

</body>
</html>
