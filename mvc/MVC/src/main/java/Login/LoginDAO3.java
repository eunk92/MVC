package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO3 {

	  String driver = "oracle.jdbc.driver.OracleDriver" ;
	  String url="jdbc:oracle:thin:@localhost:1521:testdb";
	  String user="scott";
	  String password="tiger";	 
	 
	public Connection dbcon(){	 
		Connection con =null;
		try {
			Class.forName(driver);
			con  =DriverManager.getConnection(url, user, password);
			if( con != null) System.out.println("ok");
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
	//로그인영역
	public boolean login(String inputId, String inputPw) {
	    Connection con = dbcon();
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        // 데이터베이스 연결

	        String sql = "Select buyerId , buyerPw from buyerTbl where buyerId=?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, inputId);

	        rs = pst.executeQuery();

	        if (rs.next()) {
	            // 데이터베이스에서 ID에 해당하는 사용자 정보를 가져옴
	            String dbBuyerId = rs.getString("buyerId");
	            String dbBuyerPw = rs.getString("buyerPw");


	            // 입력한 PW와 데이터베이스에서 가져온 PW 비교
	            if (inputPw.equals(dbBuyerPw)) {
	                // 로그인 성공
	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    close(rs, pst, con);
	    // 로그인 실패
	    return false;
	}
    public boolean signupUser(String buyerId, String buyerPw, String buyerName, String buyerAddress) {
        Connection con = dbcon();
        PreparedStatement pst = null;

        try {
            // 데이터베이스 연결

            String sql = "INSERT INTO  buyerTbl(buyerId, buyerPw, buyerName, buyerAddress) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, buyerId);
            pst.setString(2, buyerPw);
            pst.setString(3, buyerName);
            pst.setString(4, buyerAddress);

            int result = pst.executeUpdate();
            
            if (result > 0) {
                // 회원가입 성공
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(pst, con);
        // 회원가입 실패
        return false;
    }
    public boolean idCheck(String buyerId) {
        Connection con = dbcon();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT buyerId FROM buyerTbl WHERE buyerId = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, buyerId);

            rs = pst.executeQuery();

            // 아이디가 중복되지 않으면 true를 반환
            return !rs.next(); // rs.next()가 false이면 중복 아이디가 없음

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, pst, con);
        }

        // 데이터베이스 오류 등의 문제가 발생하면 중복 아이디로 간주하지 않도록 false 반환
        return false;
    }
}
	

