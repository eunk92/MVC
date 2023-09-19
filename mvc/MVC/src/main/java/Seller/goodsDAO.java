package Seller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class goodsDAO {
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

	/* 
	 * public Goods selectOne(String goodsCode) { // db 연결 Connection con = dbcon();
	 * 
	 * // sql 작성 String sql = "select * from goodsTbl where goodsCode=?";
	 * PreparedStatement pst = null; ResultSet rs = null; Goods g = null;
	 * 
	 * // 실행 try { pst = con.prepareStatement(sql); pst.setString(1, goodsCode);
	 * 
	 * rs = pst.executeQuery();
	 * 
	 * if (rs.next()) { String code = rs.getString(1); int brand = rs.getInt(2);
	 * String name = rs.getString(3); int price = rs.getInt(4); int stock =
	 * rs.getInt(5);
	 * 
	 * g = new Goods(code, brand, name, price, stock); } } catch (SQLException e) {
	 * e.printStackTrace(); } // 자원해제 close(rs, pst, con); return g; }
	 */

	/*
	 * public ArrayList<String> selectAll(){ //연결 Connection con =dbcon();
	 * 
	 * //sql String sql="select * from goodsTbl";
	 * 
	 * //sql실행 PreparedStatement pst=null; ResultSet rs =null;
	 * 
	 * ArrayList<String> list =new ArrayList<>(); try {
	 * pst=con.prepareStatement(sql); rs =pst.executeQuery();
	 * 
	 * while( rs.next()) { list.add(rs.getString(1)); list.add(rs.getString(2));
	 * list.add(rs.getString(3)); list.add(rs.getString(4));
	 * list.add(rs.getString(5)); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } //해제 close(rs,pst, con);
	 * return list; }
	 */

	// 전체 조회 메서드
	public ArrayList<Goods> selectAll() {
		// db 연결
		Connection con = dbcon();

		// sql 작성
		String sql = "select * from goodsTbl order by goodsCode";
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Goods> goodsList = new ArrayList<>();

		// 실행
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {

				String code = rs.getString(1);
				int brand = rs.getInt(2);
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
			pst.setInt(2, g.getGoodsBrand());
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
			pst.setInt(1, g.getGoodsBrand());
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

	public void close(AutoCloseable... a) {
		for (AutoCloseable item : a) {
			try {
				item.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		goodsDAO dao = new goodsDAO();
		ArrayList<Goods> list = dao.getListPage(1, 4);
		for (Goods goods : list) {
			System.out.println(goods);
		}
		

	}

}
