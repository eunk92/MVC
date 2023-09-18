package Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
   

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 
    	  req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp").forward(req, resp);
    	
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자가 입력한 ID와 비밀번호를 가져옵니다.
    	LoginService s = new LoginService();
        String inputId = req.getParameter("Id");
        String inputPw  = req.getParameter("Pw");
        
        // 옵션값을 받아와서 구매회원과 판매회원 구별가능
        String option  = req.getParameter("option");
        
        System.out.println( "로그인옵션" + option);
        if (option == null || option.isEmpty()) {
            req.setAttribute("message", "회원옵션을 선택하세요.");
            req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp").forward(req, resp);
            return;
        }
        
        boolean loginResult;
        if( option.equals("buyer")) {
        // 로그인 서비스를 통해 로그인을 처리합니다.
        	  loginResult = s.loginUser(inputId, inputPw);
        }else {
        	  loginResult = s.loginUser2(inputId, inputPw);
        }

        if (loginResult) {
            // 로그인 성공 시
        	HttpSession session = req.getSession();
            session.setAttribute("Id", inputId);
            
            resp.sendRedirect("/mvc/home");
        } else {
            // 로그인 실패 시
            req.setAttribute("message", "로그인 실패. 아이디 또는 비밀번호를 확인하세요.");       
            req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp").forward(req, resp);
        }

     
       
    }
}