package com.framework.core.domain;

public class Po implements Cloneable{
	private int startNum;

	private int endNum;

	private int pageNum;

	private int pageSize;

	public void setData(int pageNum, int pageSize) {
		setPageNum(pageNum);
		setPageSize(pageSize);
		startNum = (pageNum - 1) * pageSize;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
