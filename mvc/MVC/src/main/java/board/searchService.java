package board;

import java.util.ArrayList;

public class searchService {
	BoardDAO dao = new BoardDAO();

    public ArrayList<Question> searchContents(String query) {
    	
        return dao.performSearch(query);
        
    }
}
