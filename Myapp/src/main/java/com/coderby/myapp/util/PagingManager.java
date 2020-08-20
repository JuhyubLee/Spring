package com.coderby.myapp.util;

public class PagingManager {

	private int nowPage;
	private int totalPage;
	private int totalBlock;
	private int nowBlock;
	private int startPage;
	private int endPage;
	private int totalCount;
	
	public PagingManager(int totalCount, int nowPage) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		setTotalPage();
		setTotalBlock();
		setNowBlock();
		setStartPage();
		setEndPage();
	}
	
	private void setTotalPage() {
		this.totalPage = (int)Math.ceil(totalCount/10.0);
	}
	
	private void setTotalBlock() {
		this.totalBlock = (int)Math.ceil(totalPage/10.0);
	}
	
	private void setNowBlock() {
		this.nowBlock = (int)Math.ceil(this.nowPage/10.0);
	}
	
	private void setStartPage() {
		this.startPage = (int)((this.nowBlock-1)*10+1);
	}
	
	private void setEndPage() {
		if(nowBlock<totalBlock) {
			this.endPage = nowBlock*10;
		}else {
			this.endPage = totalPage;
		}
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}
