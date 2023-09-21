package Page;

public class PageHandler {
	// 받아오는 값
	int currentPage;	// 현재페이지
	int totRecords;	// 총 레코드 수
	int pageSize;	// 한 페이지에 보려는 글의 수
	int grpSize; // 그룹의 사이즈
	
	// 구해야하는 값
	int totalPage;	// 총 페이지 수
	int currentGrp;	// 현재 그룹
	int grpStartPage;	// 현재 그룹의 시작페이지
	int grpEndPage;	// 현재 그룹의 마지막페이지
	
	public PageHandler(int currentPage, int totRecords, int pageSize, int grpSize) {
		super();
		this.currentPage = currentPage;
		this.totRecords = totRecords;
		this.pageSize = pageSize;
		this.grpSize = grpSize;
		
		calcPage();
	}
	
	private void calcPage() {
		// 1. 총페이지 수
		// 2. 현재그룹
		// 3. 현재그룹의 시작페이지
		// 4. 현재그룹의 마지막페이지
		
		// 전체페이지 구하기
		int remain = totRecords % pageSize;
		if( remain == 0 ) {
			totalPage = totRecords / pageSize;
		} else {
			totalPage = totRecords / pageSize + 1;
		}
		
		// 현재그룹구하기
		int remain2 = currentPage % grpSize;
		if( remain2 == 0 ) {
			currentGrp = currentPage / grpSize;
		} else {
			currentGrp = currentPage / grpSize + 1;
		}
		// 그룹의 시작, 끝 페이지 구하기
		grpStartPage = (currentGrp-1)*grpSize + 1;
		grpEndPage = currentGrp*grpSize;
		
		// 그룹의 마지막페이지가 전체의 마지막페이지
		if( grpEndPage > totalPage) {
			grpEndPage = totalPage;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotRecords() {
		return totRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getGrpSize() {
		return grpSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentGrp() {
		return currentGrp;
	}

	public int getGrpStartPage() {
		return grpStartPage;
	}

	public int getGrpEndPage() {
		return grpEndPage;
	}
	

	
}
