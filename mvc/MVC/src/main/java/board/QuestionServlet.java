package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/board")
public class QuestionServlet extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			BoardService s = new BoardService();
			
			ArrayList<Question> list = s.getSelectAll();
			
			// 로그인 유효성 검사
			ArrayList<Question> sendList = new ArrayList<Question>() ;
			HttpSession session = request.getSession();
			String inputId = (String)session.getAttribute("Id");
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getBuyerId().equals(inputId)) {
					sendList.add(list.get(i));
				}else {
					int num = list.get(i).getNum();
					int questionCode = list.get(i).getQuestionCode();
					String buyerId = list.get(i).getBuyerId();
					String title = "비밀글입니다";
					String quetionContents = list.get(i).getQuetionContents();
					String writeDate = list.get(i).getWriteDate();
					
					Question question = new Question(num, questionCode, buyerId, title, quetionContents, writeDate );
					sendList.add(question);
				}
			}
			
			request.setAttribute("questionList", sendList);
			request.getRequestDispatcher("WEB-INF/views/board.jsp").forward(request, response);

		}
	
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			BoardService s = new BoardService();
			JSONObject obj = null;
			
			int code = Integer.parseInt(request.getParameter("code"));
			obj = s.getSelectCheck(code);
			
			response.getWriter().println(obj);
		}
}
