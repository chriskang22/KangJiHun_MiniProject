package com.kang.subwayAlarm;

public class SiteOption {
	private int subwayPerPage;
	private int boardPerPage;
	public SiteOption() {
		// TODO Auto-generated constructor stub
	}
	public SiteOption(int subwayPerPage, int boardPerPage) {
		super();
		this.subwayPerPage = subwayPerPage;
		this.boardPerPage = boardPerPage;
	}
	public int getSubwayPerPage() {
		return subwayPerPage;
	}
	public void setSubwayPerPage(int subwayPerPage) {
		this.subwayPerPage = subwayPerPage;
	}
	public int getBoardPerPage() {
		return boardPerPage;
	}
	public void setBoardPerPage(int boardPerPage) {
		this.boardPerPage = boardPerPage;
	}
}
