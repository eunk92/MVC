package Board;


public class writeService {
	boardDAO dao = new boardDAO();
	
	public void registerContents(Question b) {
		dao.insertContents(b);
	}
}
