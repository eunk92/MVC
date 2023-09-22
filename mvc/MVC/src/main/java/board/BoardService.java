package board;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import goods.Goods;

public class BoardService {
	
	BoardDAO dao = new BoardDAO();
	
	// 글 전체 조회
	public ArrayList<Question> getSelectAll(){
		ArrayList<Question> questionList = dao.selectAll();
		return questionList;
	}
	
	
	
	// 한건 조회
	public JSONObject getSelectCheck(int rownum) {
		
		Question q = dao.selectCheck(rownum);
		JSONObject obj = new JSONObject();
		
		obj.put("num", q.getNum());
		obj.put("questionCode", q.getQuestionCode());
		obj.put("buyerId", q.getBuyerId());
		obj.put("title", q.getTitle());
		obj.put("quetionContents", q.getQuetionContents());
		obj.put("writeDate", q.getWriteDate());
		
		return obj;
		
	}
	
	// 글 등록
	public void registerContents(Question b) {
		dao.insertContents(b);
	}
	
	
	// 글 수정
	public void modifyBoard(String title, String quetioncontents, int questioncode) {
		dao.modifyOne(title, quetioncontents, questioncode );
	}
	
	
	// 글 삭제
	public void deleteBoard(int code) throws SQLException {
		dao.deleteOne(code);
	}
	
	// 글 검색
	public ArrayList<Question> searchContents(String query) {
        return dao.performSearch(query);
    }
	
	
	
	public static void main(String[] args) {
		BoardService s = new BoardService();
		ArrayList<Question> q = s.getSelectAll();
		System.out.println(q);
		
		JSONObject qu = s.getSelectCheck(5);
		System.out.println(qu);
	}
}
