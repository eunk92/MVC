package Seller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.Goods;

@WebServlet("/goodsAlter")
public class GoodsAlterServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		sellerService s = new sellerService();
		
		ArrayList<Goods> list = s.selectAll();
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("WEB-INF/views/alter.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        String goodsCode = req.getParameter("goodsCode");
        String goodsBrand = req.getParameter("goodsBrand");
        String goodsName = req.getParameter("goodsName");
        int goodsPrice = Integer.parseInt(req.getParameter("goodsPrice"));
        int goodsStock = Integer.parseInt(req.getParameter("goodsStock"));
        
		sellerService s = new sellerService();
		
		s.alterGoods(new Goods(goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock));
		
		resp.sendRedirect("/MVC/goodsAlter");
		*/
		
		resp.setContentType("text/html;charset=UTF-8");
	    req.setCharacterEncoding("UTF-8");
	    sellerService s = new sellerService();
		String goodsCode = req.getParameter("goodsCode");
	    String goodsBrand = req.getParameter("goodsBrand");
	    String goodsName = req.getParameter("goodsName");
	    String goodsPrice_ = req.getParameter("goodsPrice");
	    String goodsStock_ = req.getParameter("goodsStock");
	    
	    if(goodsCode.equals("")  ||  goodsName.equals("")  ||  goodsPrice_.equals("")  ||goodsStock_.equals("")    ) {	
	    	String message  = URLEncoder.encode("상품 코드와 상품명을 확인해주세요.","utf-8");
	   	 resp.sendRedirect("/MVC/goodsAlter?message="+message);        	
	    	  return;
	    } 
	    // int goodsBrand =0;
	    int goodsPrice =0;
	    int goodsStock =0;

		//goodsBrand= Integer.parseInt(goodsBrand_);         
		goodsPrice=  Integer.parseInt(goodsPrice_);        
		goodsStock=  Integer.parseInt(goodsStock_);

		
		s.alterGoods(new Goods(goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock));
		
		String message  = URLEncoder.encode("변경완료.","utf-8");
		resp.sendRedirect("/MVC/goodsAlter?message="+message);
	}
	
}
