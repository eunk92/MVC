package goods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home.do")
public class HomeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		
		
		requset.getRequestDispatcher("WEB-INF/views/home.jsp").forward(requset, response);
		
	}
	
}
