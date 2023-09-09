package com.kang.subwayAlarm.board;

import java.util.Date;

public class Reply {
	private int r_no;
	private int r_b_no;
	private String r_owner;
	private String r_txt;
	private Date r_when;
	private int r_like_count;
	public Reply() {
		// TODO Auto-generated constructor stub
	}
	public Reply(int r_no, int r_b_no, String r_owner, String r_txt, Date r_when, int r_like_count) {
		super();
		this.r_no = r_no;
		this.r_b_no = r_b_no;
		this.r_owner = r_owner;
		this.r_txt = r_txt;
		this.r_when = r_when;
		this.r_like_count = r_like_count;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public int getR_b_no() {
		return r_b_no;
	}
	public void setR_b_no(int r_b_no) {
		this.r_b_no = r_b_no;
	}
	public String getR_owner() {
		return r_owner;
	}
	public void setR_owner(String r_owner) {
		this.r_owner = r_owner;
	}
	public String getR_txt() {
		return r_txt;
	}
	public void setR_txt(String r_txt) {
		this.r_txt = r_txt;
	}
	public Date getR_when() {
		return r_when;
	}
	public void setR_when(Date r_when) {
		this.r_when = r_when;
	}
	public int getR_like_count() {
		return r_like_count;
	}
	public void setR_like_count(int r_like_count) {
		this.r_like_count = r_like_count;
	}
}
