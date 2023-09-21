package order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

public class orderDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:testdb";
	String user = "scott";
	String password = "tiger";

	public Connection dbcon() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("ok");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	public void close(AutoCloseable... a) {
		for (AutoCloseable item : a) {
			try {
				item.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 주문 목록 전체 조회
	public ArrayList<Order> orderAll() {
		ArrayList<Order> list = new ArrayList<>();
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select o.ORDERNUM, o.ORDERDATE, g.GOODSNAME, b.BUYERID,b.BUYERADDRESS,o.ORDERTOTAL from ordertbl o \r\n"
				+ "join buyerTbl b\r\n" + "on o.BUYERID = b.BUYERID\r\n" + "join goodsTbl g\r\n"
				+ "on o.GOODSCODE = g.GOODSCODE";

		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				String orderNum = rs.getString(1);
				String orderDate = rs.getString(2);
				String goodsName = rs.getString(3);
				String buyerId = rs.getString(4);
				String address = rs.getString(5);
				int Total = rs.getInt(6);

				list.add(new Order(orderNum, orderDate, goodsName, buyerId, address, Total));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs, con, pst);
		return list;
	}

	// 주문 목록 전체 조회
	public ArrayList<Order> orderOne(String id) {
		ArrayList<Order> list = new ArrayList<>();
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select o.ORDERNUM, o.ORDERDATE, g.GOODSNAME, b.BUYERID, b.BUYERADDRESS, o.ORDERTOTAL from ordertbl o "
				+ "join buyerTbl b " + "on o.BUYERID = b.BUYERID " + "join goodsTbl g "
				+ "on o.GOODSCODE = g.GOODSCODE " + "where b.BUYERID = ?"; // ? 를 사용하여 파라미터를 바인딩합니다.

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				String orderNum = rs.getString(1);
				String orderDate = rs.getString(2);
				String goodsName = rs.getString(3);
				String buyerId = rs.getString(4);
				String address = rs.getString(5);
				int Total = rs.getInt(6);

				list.add(new Order(orderNum, orderDate, goodsName, buyerId, address, Total));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs, con, pst);
		return list;
	}

	public void orderInsert(String id, String code, int total) {
		// db연결
		Connection con = dbcon();
		PreparedStatement pst = null;
		// sql작성
		String sql = "insert into ordertbl values(orderseq.nextval, sysdate, ?, ?, ?)";
		try {
			// sql실행
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, code);
			pst.setInt(3, total);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("주문서 입력완료");
		// 연결해제
		close(pst, con);
	}

	public static void main(String[] args) {
		orderDAO dao = new orderDAO();
		// 주문서 생성
		// dao.orderInsert("kbj", "A0005", 100);
		// 전체 조회
		ArrayList<Order> list = dao.orderAll();
		System.out.println(list);

		// id별 조회
		String id = "kbj";
		ArrayList<Order> list2 = dao.orderOne(id);
		System.out.println(list2);
	}
}
