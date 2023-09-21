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


@WebServlet("/goodsRegister")
public class GoodsRegisterServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		sellerService s = new sellerService();
		
		ArrayList<Goods> list = s.selectAll();
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		sellerService s = new sellerService();
        /*
		String goodsCode = req.getParameter("goodsCode");
        String goodsBrand = req.getParameter("goodsBrand");
        String goodsName = req.getParameter("goodsName");
        int goodsPrice = Integer.parseInt(req.getParameter("goodsPrice"));
        int goodsStock = Integer.parseInt(req.getParameter("goodsStock"));
        
        s.RegisterGoods(new Goods(goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock));
		resp.sendRedirect("/MVC/goodsRegister");
        */
		
		String goodsCode = req.getParameter("goodsCode");
        String goodsBrand_ = req.getParameter("goodsBrand");
        String goodsName = req.getParameter("goodsName");
        String goodsPrice_ = req.getParameter("goodsPrice");
        String goodsStock_ = req.getParameter("goodsStock");
	
        
        //유효성  check        
        if(goodsCode.equals("")  ||  goodsName.equals("")  ||  goodsPrice_.equals("")  ||goodsStock_.equals("")    ) {
       
        	//utf-8로 인코딩하려면 URLEncoder 임포트 필요 
        	
        	String message  = URLEncoder.encode("상품 코드와 상품명을 확인해주세요.","utf-8");
       	 resp.sendRedirect("/MVC/goodsRegister?message="+message);        	
        	  return;
        } 
        
        int goodsBrand =0;
        int goodsPrice =0;
        int goodsStock =0;
    
    	goodsBrand= Integer.parseInt(goodsBrand_);         
    	goodsPrice=  Integer.parseInt(goodsPrice_);        
    	goodsStock=  Integer.parseInt(goodsStock_);
    
        
        
        // 아이디 중복 여부 체크 
   
		boolean id = s.GoodsCodeCheck(goodsCode);
		
		
		//// id 중복아니면 등록
		if(  !id) {  
			String message  = URLEncoder.encode("이미 사용 중인 제품 코드 입니다.","utf-8");
			 resp.sendRedirect("/MVC/goodsRegister?message="+message);  
		}else {
			boolean registerResult = s.RegisterGoods2(goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock);
			if(registerResult  ) {
				//최종 등록
				String message  = URLEncoder.encode("등록이 완료되었습니다.","utf-8");
				 resp.sendRedirect("/MVC/goodsRegister?message="+message);
				}
			else {
				String message  = URLEncoder.encode("등록에 실패했습니다.","utf-8");
			 resp.sendRedirect("/MVC/goodsRegister?message="+message);
			}
		}
		
		
		
		
	}

}
