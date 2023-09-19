package Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/write")
public class writeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		req.getRequestDispatcher("WEB-INF/views/write.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		String buyerId = req.getParameter("buyerid");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		System.out.println(buyerId);
		System.out.println(title);
		System.out.println(content);
		

		writeService ws = new writeService();
		boardDAO dao = new boardDAO();

		Question q = new Question();
		
		q.setBuyerId("kdy");
		q.setTitle(title);
		q.setQuestionContents(content);

		dao.insertContents(q);

		resp.sendRedirect(req.getContextPath() + "/board");
	}
}