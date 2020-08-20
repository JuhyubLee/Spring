package spring.jh.myapp.member.model;

public class Pagination {

	// 한 페이지 게시글 수
	private int pageSize = 10;
	// 한 블럭 페이지 수
	private int rangeSize = 10;
	// 현재 페이지
	private int curPage = 1;
	// 현재 블럭
	private int curRange = 1;
	// 총 게시글 수
	private int listCnt;
	// 총 페이지 수
	private int pageCnt;
	// 총 블럭 수
	private int rangeCnt;
	// 시작 페이지
	private int startPage = 1;
	// 끝 페이지
	private int endPage = 1;
	// 시작 index
	private int startIndex = 0;
	// 이전 페이지
	private int prevPage;
	// 다음 페이지
	private int nextPage;
	
	public Pagination(int listCnt, int curPage) {
		// 현재 페이지
		setCurPage(curPage);
		// 총 인원 수
		setListCnt(listCnt);
		// 총 페이지 수
		setPageCnt(listCnt);
		// 총 블럭 수
		setRangeCnt(pageCnt);
		// 블럭 setting
		rangeSetting(curPage);
		// startIndex 설정
		setStartIndex(curPage);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setCurRange(int curRange) {
		this.curRange = (int)((curPage-1)/rangeSize) + 1;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = (int)Math.ceil(listCnt*1.0/pageSize);
	}
	public void setRangeCnt(int rangeCnt) {
		this.rangeCnt = (int)Math.ceil(pageCnt*1.0/rangeSize);
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = (curPage-1) * pageSize;
	}
	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getRangeSize() {
		return rangeSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public int getCurRange() {
		return curRange;
	}

	public int getListCnt() {
		return listCnt;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public int getRangeCnt() {
		return rangeCnt;
	}


	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}

	public void rangeSetting(int curPage) {
		setCurRange(curPage);
		this.startPage = (curRange - 1) * rangeSize + 1;
		this.endPage = startPage + rangeSize - 1;
		
		if(endPage > pageCnt) {
			this.endPage = pageCnt;
		}
		this.prevPage = curPage - 1;
		this.nextPage = curPage + 1;
	}
	
}
