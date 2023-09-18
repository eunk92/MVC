package Login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 회원 가입 페이지로 이동
        req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자가 입력한 회원 정보를 가져옵니다.
        String buyerId = req.getParameter("buyerId");
        String buyerPw = req.getParameter("buyerPw");
        String buyerName = req.getParameter("buyerName");
        String buyerAddress = req.getParameter("buyerAddress");

        // 중복된 아이디 체크
        LoginDAO3 dao = new LoginDAO3();
        boolean id = dao.idCheck(buyerId);

        if (buyerId.isEmpty() || buyerPw.isEmpty() || buyerName.isEmpty() || buyerAddress.isEmpty()) {
            req.setAttribute("message", "모든 정보를 입력해주세요.");
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
            return; // 필드가 비어있을 경우 회원가입 프로세스를 중단합니다.
        }
        if (!id) {
            // 중복된 아이디가 있을 경우
            req.setAttribute("message", "이미 사용 중인 아이디입니다.");
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
        } else {
            // 중복된 아이디가 없을 경우 회원 가입 진행
            boolean signupResult = dao.signupUser(buyerId, buyerPw, buyerName, buyerAddress);

            if (signupResult) {
                // 회원가입 성공 시
                req.setAttribute("message", "회원가입이 완료되었습니다.");
                req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp").forward(req, resp);
            } else {
                // 회원가입 실패 시
                req.setAttribute("message", "회원가입에 실패하였습니다.");
                req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp").forward(req, resp);
            }
        }
    }
}