package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import goods.Goods;



public class BoardDAO {

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
	
	// 게시판 전체 조회
	public ArrayList<Question> selectAll(){
		
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select * from questionTbl";
		
		ArrayList<Question> questionList = new ArrayList<Question>();
		
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();	
			
			while(rs.next()) {
				int questionCode = rs.getInt(1);
				String buyerId = rs.getString(2);
				String title = rs.getString(3);
				String quetionContents = rs.getString(4);
				String writeDate = rs.getString(5);
				
				
				Question question = new Question(questionCode, buyerId, title, quetionContents, writeDate );
				questionList.add(question);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs, pst, con);
		return questionList;
	}
	
	
	//게시판 글 한건 조회
	public Question selectCheck(int questioncode){
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select * from questionTbl where questioncode = ?";
		
		Question question = null;
		
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, questioncode);
			rs = pst.executeQuery()	;
			
			if(rs.next()) {
				int questionCode = rs.getInt(1);
				String buyerId = rs.getString(2);
				String title = rs.getString(3);
				String quetionContents = rs.getString(4);
				String writeDate = rs.getString(5);
				
				question = new Question(questionCode, buyerId, title, quetionContents, writeDate );
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(rs,pst,con);
		return question;
		
	}

	// 게시판 글쓰기
	public void insertContents(Question b) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = dbcon();
			String sql = "insert into questionTbl values (questionseq.nextval, ?, ?, ?, sysdate)";

			System.out.println(sql);
			pst = con.prepareStatement(sql);

			pst.setString(1, b.getBuyerId());
			pst.setString(2, b.getTitle());
			pst.setString(3, b.getQuetionContents());
			

			int rowsAffected = pst.executeUpdate();
			
			if (rowsAffected == 1) {

				rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int generatedQuestionCode = rs.getInt(1);
					b.setQuestionCode(generatedQuestionCode);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pst, con);
		}
	}
	
	// 게시판 글 수정
	
	//게시글 삭제
	public void deleteOne(int code) throws SQLException {		

		Connection con = dbcon();
		String sql = "delete from questionTbl where questionCode = ?";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, code); 
			
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		close(pst, con);
	}
	
	
	
	
	

	// 자원반납
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
		BoardDAO dao = new BoardDAO();
		ArrayList<Question> list = dao.selectAll();
		System.out.println(list);
		
		Question a = dao.selectCheck(1);
		System.out.println(a);
	}
	
}
