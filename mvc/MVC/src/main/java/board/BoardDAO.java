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
		
		
		String sql = "select rownum as num, questioncode, buyerid, title, quetioncontents, writedate  "
				   + "from (select * from questionTbl order by questioncode)";
		
		ArrayList<Question> questionList = new ArrayList<Question>();
		
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();	
			
			while(rs.next()) {
				int num = rs.getInt(1);
				int questionCode = rs.getInt(2);
				String buyerId = rs.getString(3);
				String title = rs.getString(4);
				String quetionContents = rs.getString(5);
				String writeDate = rs.getString(6);
				
				
				Question question = new Question(num, questionCode, buyerId, title, quetionContents, writeDate );
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
	public Question selectCheck(int rownum){
		Connection con = dbcon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select * "
				   + "from (select rownum as num, questioncode, buyerid, title, quetioncontents, writedate "
				   + "from (select * from questionTbl order by questioncode)) "
				   + "where num = ? ";
		
		Question question = null;
		
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, rownum);
			rs = pst.executeQuery()	;
			
			if(rs.next()) {
				int num = rs.getInt(1);
				int questionCode = rs.getInt(2);
				String buyerId = rs.getString(3);
				String title = rs.getString(4);
				String quetionContents = rs.getString(5);
				String writeDate = rs.getString(6);
				
				question = new Question(num, questionCode, buyerId, title, quetionContents, writeDate );
				
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
	public void modifyOne(String title, String quetioncontents, int questioncode) {
		Connection con = dbcon();
		String sql = "update questionTbl set title = ?, quetioncontents = ?   where questioncode = ?";
		PreparedStatement pst = null;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, title); 
			pst.setString(2, quetioncontents); 
			pst.setInt(3, questioncode); 
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pst, con);
		
	}
	
	
	
	
	
	
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
	
	
	
	// 게시글 내용 검색
	public ArrayList<Question> performSearch(String query) {
	    Connection con = dbcon();
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    ArrayList<Question> searchResults = new ArrayList<>();

	    String sql = "select * "
	    		+ "from (select rownum as num, questioncode, buyerid, title, quetioncontents, writedate "
	    		+ "from (select * from questionTbl order by questioncode)) "
	    		+ "where title like ? or title like ? or title like ? or title like ? "
	    		+ "or quetioncontents like ? or quetioncontents like ? or quetioncontents like ? or quetioncontents like ?";
	   
	    try {
	        pst = con.prepareStatement(sql);
	        
	        pst.setString(1, query);
	        pst.setString(2, "%" + query);
	        pst.setString(3, query + "%");
	        pst.setString(4, "%" + query + "%");
	        
	        pst.setString(5, query);
	        pst.setString(6, "%" + query);
	        pst.setString(7, query + "%");
	        pst.setString(8, "%" + query + "%");

	        rs = pst.executeQuery();

	        while (rs.next()) {
	            
	            int num = rs.getInt(1);
				int questionCode = rs.getInt(2);
				String buyerId = rs.getString(3);
				String title = rs.getString(4);
				String quetionContents = rs.getString(5);
				String writeDate = rs.getString(6);
	            
				Question question = new Question(num, questionCode, buyerId, title, quetionContents, writeDate );
				
				searchResults.add(question);
				
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(rs, pst, con);
	    }
	    return searchResults;
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
		
		String str = "은경";
		ArrayList<Question> a = dao.performSearch(str);
		System.out.println(a);
	}
	
}
