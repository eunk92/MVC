package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/board")
public class QuestionServlet extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			BoardService s = new BoardService();
			ArrayList<Question> list = s.getSelectAll();

			request.setAttribute("questionList", list);
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
