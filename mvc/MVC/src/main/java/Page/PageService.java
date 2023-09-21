package Page;

import java.util.ArrayList;

import goods.Goods;

public class PageService {
	
	PageDAO dao = new PageDAO();
	
	public int getTotalCnt() {
		return dao.getTotalCnt();
	}
	
	// 페이징을 겸한 전체 조회
	public  ArrayList<Goods> getListPage(int page, int pageSize){
		return dao.getListPage(page, pageSize);
	}
}
