package goods;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Seller.Goods;

public class GoodsDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:ptm";
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
				int goodsBrand = rs.getInt(2);
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
				int goodsBrand = rs.getInt(2);
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
	
	
	// 페이징을 위한 준비
	public int getTotalCnt() {
		Connection con = dbcon();
		String sql = "select count(*) from goodsTbl";
		ResultSet rs = null;
		PreparedStatement pst = null;

		int totalCnt = 0;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				// 1열의 갯수
				totalCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs, pst, con);
		return totalCnt;
	}

	// 현재 페이지, 페이지에 나타낼 목록의 갯수
	public ArrayList<Goods> getListPage(int page, int pageSize) {
		int startPage, endPage = 0;

		startPage = (page - 1) * pageSize + 1;
		endPage = page * pageSize;
		
		System.out.println("start page " + startPage);
		System.out.println("end page " + endPage);

		Connection con = dbcon();
		String sql = "select * from( select rownum num, goodsCode, goodsBrand, goodsName, goodsPrice, goodsStock from goodsTbl order by goodsCode) where num between ? and ?";
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Goods> list = new ArrayList<>();
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, startPage);
			pst.setInt(2, endPage);
			rs = pst.executeQuery();

			while (rs.next()) {
				int num = rs.getInt(1);
				String code = rs.getString(2);
				int brand = rs.getInt(3);
				String name = rs.getString(4);
				int price = rs.getInt(5);
				int stock = rs.getInt(6);
				Goods g = new Goods(num, code, brand, name, price, stock);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs, pst, con);
		return list;
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
