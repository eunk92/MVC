package cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/cartlist.do")
public class CartListServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//json 관련 라이브러리
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html;charset=utf-8");
		
		//세션에 불러오기
		HttpSession session = req.getSession();
		JSONArray cartList = (JSONArray)session.getAttribute("cartList");
		
		System.out.println("출력2 " + cartList);
		resp.getWriter().println(cartList);
	}
}
