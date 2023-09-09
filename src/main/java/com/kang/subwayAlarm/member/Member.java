package com.kang.subwayAlarm.member;

public class Member {
	private String sm_id;
	private String sm_pw;
	private String sm_name;
	private String sm_photo;
	private String sm_addr;
	private String sm_role;
	public Member() {
		// TODO Auto-generated constructor stub
	}
	public Member(String sm_id, String sm_pw, String sm_name, String sm_photo, String sm_addr, String sm_role) {
		super();
		this.sm_id = sm_id;
		this.sm_pw = sm_pw;
		this.sm_name = sm_name;
		this.sm_photo = sm_photo;
		this.sm_addr = sm_addr;
		this.sm_role = sm_role;
	}
	public String getSm_id() {
		return sm_id;
	}
	public void setSm_id(String sm_id) {
		this.sm_id = sm_id;
	}
	public String getSm_pw() {
		return sm_pw;
	}
	public void setSm_pw(String sm_pw) {
		this.sm_pw = sm_pw;
	}
	public String getSm_name() {
		return sm_name;
	}
	public void setSm_name(String sm_name) {
		this.sm_name = sm_name;
	}
	public String getSm_photo() {
		return sm_photo;
	}
	public void setSm_photo(String sm_photo) {
		this.sm_photo = sm_photo;
	}
	public String getSm_addr() {
		return sm_addr;
	}
	public void setSm_addr(String sm_addr) {
		this.sm_addr = sm_addr;
	}
	public String getSm_role() {
		return sm_role;
	}
	public void setSm_role(String sm_role) {
		this.sm_role = sm_role;
	}
}
