package Board;

public class deleteService {
	boardDAO dao = new boardDAO();
	public void deleteBoard(String code) {
		
		dao.deleteOne(code);
	}
}
