package Board;

import java.util.ArrayList;

public class BoardService {

	boardDAO dao = new boardDAO();

	public ArrayList<Question> board() {

		ArrayList<Question> list = dao.getList();
		for(Question b : list) {
			System.out.println(b +"<br>");
		}
		return list;

	}
}
