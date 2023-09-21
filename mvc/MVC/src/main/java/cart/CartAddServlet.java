package cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import goods.GoodsService;

@WebServlet("/addCart.do")
public class CartAddServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//json 관련 라이브러리
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		GoodsService s = new GoodsService();
		String itemcode = req.getParameter("itemcode");
		
		
		JSONObject o = s.getSelectOne(itemcode);
		
		
		//세션에 저장하기
		HttpSession session = req.getSession();
		JSONArray cartList = (JSONArray)session.getAttribute("cartList");
		
		if (cartList != null) {
		    cartList.put(o);
		} else {
		    cartList = new JSONArray();
		    cartList.put(o);
		    session.setAttribute("cartList", cartList);
		}
		System.out.println(cartList);

		resp.getWriter().println(cartList);
	}
}
