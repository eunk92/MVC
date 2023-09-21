package goods;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class GoodsDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:testdb";
	String user = "scott";
	String password = "tiger";

	public Connection dbcon() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (con != null)
				System.out.println("ok");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	// 상품목록 전체 조회
	public ArrayList<Goods> selectAll(){
		
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select goodsCode, decode(goodsbrand, '0', '삼성', '1', '애플'), "
				   + "goodsName, goodsprice, goodsstock from goodsTbl";
		
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();	
			
			while(rs.next()) {
				String goodsCode = rs.getString(1);
				String goodsBrand = rs.getString(2);
				String goodsName = rs.getString(3);
				int goodsPrice = rs.getInt(4);
				int goodsStock = rs.getInt(5);
				
				Goods g = new Goods(goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock );
				goodsList.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(rs, pst, con);
		return goodsList;
		
	}
	
	// 상품 브랜드별 조회
	public ArrayList<Goods> selectCheck(String goodsbrand){
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		
		String sql = "select goodsCode, decode(goodsbrand, '0', '삼성', '1', '애플'), "
				   + "goodsName, goodsprice, goodsstock from goodsTbl "
				   + "where goodsbrand = ? ";
		
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, goodsbrand);
			rs = pst.executeQuery()	;
			
			while(rs.next()) {
				String goodsCode = rs.getString(1);
				String goodsBrand = rs.getString(2);
				String goodsName = rs.getString(3);
				int goodsPrice = rs.getInt(4);
				int goodsStock = rs.getInt(5);
				
				Goods goods = new Goods(goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock );
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(rs,pst,con);
		return goodsList;
		
	}
	
	
	
	
	
	
	
	
	// 병진
	public Goods selectOne (String goodscode) {
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Goods goods = null;
		
		String sql = "select goodsCode, decode(goodsbrand, '0', '삼성', '1', '애플'), "
				   + "goodsName, goodsprice, goodsstock from goodsTbl "
				   + "where goodsCode = ?";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, goodscode);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				String goodsCode = rs.getString(1);
				String goodsBrand = rs.getString(2);
				String goodsName = rs.getString(3);
				int goodsPrice = rs.getInt(4);
				int goodsStock = rs.getInt(5);
				
				goods = new Goods(goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(con,pst,rs);
		return goods;
	}
	
	
	
	
	
	
	public void close(AutoCloseable ...a) {
		for(AutoCloseable item : a) {
			try {
				item.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		GoodsDAO dao = new GoodsDAO()	;
		ArrayList<Goods> a = dao.selectAll();
		System.out.println(a);
	}
	
}
