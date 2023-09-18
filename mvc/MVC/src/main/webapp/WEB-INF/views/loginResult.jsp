<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
<style>
    /* CSS 스타일 정의 */
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
      .signup-link {
        margin-top: 20px; /* 회원가입 링크 위쪽 여백 추가 */
        text-align: right; /* 텍스트 오른쪽 정렬 */
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
    .login-form {
        background-color: #fff;
        padding: 20px;
        max-width: 600px; /* 폼의 최대 너비 설정 */
        width: 100%;
        border: 1px solid #ddd;
        box-shadow: 0px 0px 5px #ccc;
    }
    span {
        display: block;
        margin-bottom: 10px;
        font-weight: bolder;
    }
    input[type="text"],
    input[type="password"] {
        width: 96%;
        height: 50px;
        padding: 10px;
          font-size:20px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
}
input:focus {
    border-color: #0982f0; /* 포커스 시 테두리 색상 설정 */
    outline: none; /* 포커스 아웃라인 제거 */
}

    input[type="submit"] {
     font-weight: bolder;
     font-size:18px;
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
    .options {
        display: flex;
        justify-content:flex-end; 
        align-items: center;
    }
    .options label {
        margin-right: 20px; /* 각 옵션 사이의 간격 조절 */
    }
    
</style>
</head>
<body>
    <div class="header">
        <h1>Login</h1>
     <p> ${message}</p> 
  
    </div>
    
    <div class="container">
        <!-- 로그인 폼 -->
        <form class="login-form" action="<%=request.getContextPath()%>/login" method="post">
           <div class="options">
             <label><input type="radio" name="option" value="seller"> 판매 회원</label>
<label><input type="radio" name="option" value="buyer"> 구매 회원</label>
            </div>
            <span>아이디 :</span>
            <input type="text" name="Id"  placeholder="ID">
            <br>
            <span>비밀번호 :</span>
            <input type="password" name="Pw"  placeholder="PASSWORD" >
            <br>
         
            <input type="submit" value="로그인">
          
                 
        </form>
        
    
        <p class="signup-link"><a href="<%=request.getContextPath() %>/signup">회원가입</a></p>
            <p><a href="<%=request.getContextPath()%>/home">홈으로 돌아가기</a></p>
    </div>
    

</body>
</html>