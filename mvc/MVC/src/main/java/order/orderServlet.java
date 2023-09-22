package order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/orderAdd")
public class orderServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		orderService s = new orderService();
		HttpSession session = req.getSession();
		
		String code = req.getParameter("code");
		String total_ = req.getParameter("total");
		int total = Integer.parseInt(total_);
		Object id_ = session.getAttribute("Id");
		String id = id_.toString();
		System.out.println(total);
		s.orderInsert(id, code, total);
		ArrayList<Order> list = s.orderOne(id);
		
		//세션 비우기
		session.removeAttribute("cartList");
		session.setAttribute("orderlist", list);
		req.getRequestDispatcher("WEB-INF/views/order.jsp").forward(req, resp);
	}
}
