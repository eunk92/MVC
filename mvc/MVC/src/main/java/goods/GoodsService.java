package goods;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class GoodsService {

	GoodsDAO dao = new GoodsDAO();
	
	// 상품목록 전체조회
	public JSONArray getSelectAll(){
		
		ArrayList<Goods> goodsList = dao.selectAll();
		JSONArray arr = new JSONArray();
		
		for(int i = 0; i<goodsList.size(); i++) {
			Goods g = goodsList.get(i);
			JSONObject o = new JSONObject();
			o.put("goodsCode", g.getGoodsCode());
			o.put("goodsBrand", g.getGoodsBrand());
			o.put("goodsName", g.getGoodsName());
			o.put("goodsPrice", g.getGoodsPrice());
			o.put("goodsStock", g.getGoodsStock());
			arr.put(o);
			
		}
		return arr;
	}
	
	
	
	// 상품 브랜드별 조회
	public JSONArray getSelectCheck(String goodsBrand) {
		ArrayList<Goods> goodsList = dao.selectCheck(goodsBrand);
		JSONArray arr = new JSONArray();
		
		for(int i = 0; i<goodsList.size(); i++) {
			Goods g = goodsList.get(i);
			JSONObject o = new JSONObject();
			o.put("goodsCode", g.getGoodsCode());
			o.put("goodsBrand", g.getGoodsBrand());
			o.put("goodsName", g.getGoodsName());
			o.put("goodsPrice", g.getGoodsPrice());
			o.put("goodsStock", g.getGoodsStock());
			arr.put(o);
			
		}
		return arr;
		
	}
	
	
	
	
	
	// 병진
	public JSONObject getSelectOne(String goodsCode){
		Goods g = dao.selectOne(goodsCode);
		JSONObject o = new JSONObject();
		
		o.put("goodsCode", g.getGoodsCode());
		o.put("goodsBrand", g.getGoodsBrand());
		o.put("goodsName", g.getGoodsName());
		o.put("goodsPrice", g.getGoodsPrice());
		o.put("goodsStock", g.getGoodsStock());
		
		return o;
	}
	
	
	
	
	
	public static void main(String[] args) {
		GoodsService s = new GoodsService();
		String str = "G0001";
		JSONObject obj = s.getSelectOne(str);
		System.out.println(obj);
	}
}
