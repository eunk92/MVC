package order;

import java.util.ArrayList;

import org.json.JSONObject;

public class orderService {
	
	orderDAO dao = new orderDAO();
	
	public ArrayList<Order> orderAll(){
		ArrayList<Order> list = dao.orderAll();
		return list;
	}
	
	public ArrayList<Order> orderOne(String id){
		ArrayList<Order> list = dao.orderOne(id);
		return list;
	}
	
	public void orderInsert(String id, String code, int total){
		dao.orderInsert(id,code,total);
	}
}
