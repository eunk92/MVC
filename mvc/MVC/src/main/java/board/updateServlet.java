package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/update")
public class updateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BoardService s = new BoardService();

		String modigyTitle = req.getParameter("boardTitle");
		String modifyContents = req.getParameter("boardContent");
		int modifycode = Integer.parseInt(req.getParameter("questionCode"));
		
		System.out.println(modigyTitle);
		System.out.println(modifyContents);
		System.out.println(modifycode);
		
		
		s.modifyBoard(modigyTitle,modifyContents,modifycode);
		 
	}
	
	

}
