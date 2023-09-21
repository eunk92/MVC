package goods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

@WebServlet("/goods")
public class GoodsSelectServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		
		GoodsService s = new GoodsService();
		JSONArray arr = null;
		
		// 버튼 눌렀을때 값 가져오기
		String code = request.getParameter("code");
		switch(code) {
			case "0":
				arr = s.getSelectCheck(code);
				break;
			case "1":
				arr = s.getSelectCheck(code);
				break;
			case "2":
				arr = s.getSelectAll();
				break;
		}
		
		
		response.getWriter().println(arr);
		
	}
}
