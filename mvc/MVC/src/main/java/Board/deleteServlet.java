package Board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		deleteService rs = new deleteService();

		String code = req.getParameter("code");
		
		req.getRequestDispatcher("WEB-INF/views/delete.jsp").forward(req, resp);

	}

	
}
