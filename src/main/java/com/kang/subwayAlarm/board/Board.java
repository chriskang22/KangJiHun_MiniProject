package com.kang.subwayAlarm.board;

import java.util.Date;

public class Board {
	private int b_no;
	private String b_title;
	private String b_owner;
	private Date b_when;
	private Date b_updateWhen;
	private String b_txt;
	private int b_viewcnt;
	private int b_good;
	private int b_likeCheck;
	public Board() {
		// TODO Auto-generated constructor stub
	}
	public Board(int b_no, String b_title, String b_owner, Date b_when, Date b_updateWhen, String b_txt, int b_viewcnt,
			int b_good, int b_likeCheck) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_owner = b_owner;
		this.b_when = b_when;
		this.b_updateWhen = b_updateWhen;
		this.b_txt = b_txt;
		this.b_viewcnt = b_viewcnt;
		this.b_good = b_good;
		this.b_likeCheck = b_likeCheck;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_owner() {
		return b_owner;
	}
	public void setB_owner(String b_owner) {
		this.b_owner = b_owner;
	}
	public Date getB_when() {
		return b_when;
	}
	public void setB_when(Date b_when) {
		this.b_when = b_when;
	}
	public Date getB_updateWhen() {
		return b_updateWhen;
	}
	public void setB_updateWhen(Date b_updateWhen) {
		this.b_updateWhen = b_updateWhen;
	}
	public String getB_txt() {
		return b_txt;
	}
	public void setB_txt(String b_txt) {
		this.b_txt = b_txt;
	}
	public int getB_viewcnt() {
		return b_viewcnt;
	}
	public void setB_viewcnt(int b_viewcnt) {
		this.b_viewcnt = b_viewcnt;
	}
	public int getB_good() {
		return b_good;
	}
	public void setB_good(int b_good) {
		this.b_good = b_good;
	}
	public int getB_likeCheck() {
		return b_likeCheck;
	}
	public void setB_likeCheck(int b_likeCheck) {
		this.b_likeCheck = b_likeCheck;
	}
}
