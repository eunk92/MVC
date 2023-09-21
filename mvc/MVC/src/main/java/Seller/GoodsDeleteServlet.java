package Seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/goodsDelete")
public class GoodsDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        
		String goodsCode = req.getParameter("goodsCode");
        System.out.println(goodsCode);
		sellerService s = new sellerService();
		
		s.deleteGoods(goodsCode);
		
		resp.sendRedirect("/MVC/seller.do");
		
		
		
	}
	
}
