package Page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import goods.Goods;

public class PageDAO {

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
			String sql = "select * from( select rownum num, goodsCode, decode(goodsbrand, '0', '삼성', '1', '애플'), goodsName, goodsPrice, goodsStock from goodsTbl order by goodsCode) where num between ? and ?";
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
					String brand = rs.getString(3);
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
}
