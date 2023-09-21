package Seller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import goods.Goods;


public class sellerDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:testdb";
	String user = "scott";
	String password = "tiger";

	// db접속 메서드
	public Connection dbcon() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (con != null)
				System.out.println("ok");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	

	// 전체 조회 메서드
	public ArrayList<Goods> selectAll() {
		// db 연결
		Connection con = dbcon();

		// sql 작성
		String sql = "select goodsCode, decode(goodsbrand, '0', '삼성', '1', '애플'), "
				   + "goodsName, goodsprice, goodsstock from goodsTbl order by goodsCode";
				   
		
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Goods> goodsList = new ArrayList<>();

		// 실행
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {

				String code = rs.getString(1);
				String brand = rs.getString(2);
				String name = rs.getString(3);
				int price = rs.getInt(4);
				int stock = rs.getInt(5);

				Goods g = new Goods(code, brand, name, price, stock);
				goodsList.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 해제
		close(rs, pst, con);
		return goodsList;
	}

	// 추가(등록) 메서드
	public void RegisterGoods(Goods g) {

		// db 연결
		Connection con = dbcon();

		// sql 작성
		String sql = "insert into goodsTbl values(?,?,?,?,?)";
		PreparedStatement pst = null;

		// 실행
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, g.getGoodsCode());
			pst.setString(2, g.getGoodsBrand());
			pst.setString(3, g.getGoodsName());
			pst.setInt(4, g.getGoodsPrice());
			pst.setInt(5, g.getGoodsStock());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 해제
		close(pst, con);
	}
	
	// 민규
	public boolean RegisterGoods2(String goodsCode,int goodsBrand,String goodsName,int goodsPrice,int goodsStock) {
		Connection con = dbcon();
		PreparedStatement pst = null;

		try {
			// 데이터베이스 연결

			String sql = "insert into goodsTbl(goodsCode,goodsBrand,goodsName,goodsPrice,goodsStock) values(?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1,goodsCode);
			pst.setInt(2, goodsBrand);
			pst.setString(3,goodsName);
			pst.setInt(4, goodsPrice);
			pst.setInt(5, goodsStock);
		

			int result = pst.executeUpdate();

			if (result > 0) {
				//등록성공
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pst, con);
		// 등록 실패
		return false;
	}
	
	// 민규
	public boolean codeCheck(String goodsCode ) {
		 Connection con = dbcon();
			PreparedStatement pst = null;
			ResultSet rs =null;
			String sql = "SELECT goodsCode FROM  goodsTbl WHERE goodsCode = ?";
			try {
				pst=con.prepareStatement(sql);
				pst.setString(1, goodsCode);
				rs=pst.executeQuery();
				return !rs.next();		
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}close(rs,pst,con);
			return false;
	 }

	// 수정 메서드
	public void aleterGoods(Goods g) {
		// db 연결
		Connection con = dbcon();

		PreparedStatement pst = null;

		// sql 작성
		String sql = "update goodsTbl set goodsBrand=?, goodsName=?, goodsPrice=?, goodsStock=? where goodsCode=? ";

		// 실행
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, g.getGoodsBrand());
			pst.setString(2, g.getGoodsName());
			pst.setInt(3, g.getGoodsPrice());
			pst.setInt(4, g.getGoodsStock());
			pst.setString(5, g.getGoodsCode());
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 해제
		close(pst, con);

	}

	// 삭제 메서드
	public void deleteOne(String goodsCode) {

		// 연결
		Connection con = dbcon();

		// sql 작성
		String sql = "delete from goodsTbl where goodsCode=?";

		// sql 실행
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, goodsCode);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 해제
		close(pst, con);
	}

	// 페이지 다오
	

	public void close(AutoCloseable... a) {
		for (AutoCloseable item : a) {
			try {
				item.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


}
