package Board;

import java.util.ArrayList;

public class readService {

	boardDAO dao = new boardDAO();

	public Question board(String code) {

		Question q = dao.getOne(code);

		return q;

	}
}
