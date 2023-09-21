package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.Question;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/views/write.jsp").forward(req, resp);

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String buyerId = req.getParameter("buyerid");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		System.out.println(buyerId);
		System.out.println(title);
		System.out.println(content);
		

		BoardService ws = new BoardService();
		BoardDAO dao = new BoardDAO();

		Question q = new Question();
		
		q.setBuyerId("kdy");
		q.setTitle(title);
		q.setQuetionContents(content);

		dao.insertContents(q);

		resp.sendRedirect(req.getContextPath() + "/board");
	}
}