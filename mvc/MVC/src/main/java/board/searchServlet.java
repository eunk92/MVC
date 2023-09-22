package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/search")
public class searchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("q");

        BoardService s = new BoardService();
       // ArrayList<Question> list = s.getSelectAll();
        
        ArrayList<Question> searchResults = new ArrayList<Question>();
        ArrayList<Question> searchList = new ArrayList<Question>();

        if (query != null && !query.isEmpty()) {
            searchResults = s.searchContents(query); 
        }        
        
        HttpSession session = req.getSession();
		String inputId = (String)session.getAttribute("Id");
		for(int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getBuyerId().equals(inputId)) {
				searchList.add(searchResults.get(i));
			}
//			else {
//				int num = list.get(i).getNum();
//				int questionCode = list.get(i).getQuestionCode();
//				String buyerId = list.get(i).getBuyerId();
//				String title = "비밀글입니다";
//				String quetionContents = list.get(i).getQuetionContents();
//				String writeDate = list.get(i).getWriteDate();
//				
//				Question question = new Question(num, questionCode, buyerId, title, quetionContents, writeDate );
//				searchResults.add(question);
//			}
		}
        
        
        
        
        
        
        
        req.setAttribute("questionList", searchList);
        req.getRequestDispatcher("WEB-INF/views/board.jsp").forward(req, resp);
    }
}








