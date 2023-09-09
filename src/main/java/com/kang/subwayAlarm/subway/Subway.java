package com.kang.subwayAlarm.subway;

import java.util.Date;

public class Subway {
	private String btrainNo;
	private String statnNm;
	private String trnsitCo;
	private String btrainSttus;
	private String barvlDt;
	private String bstatnNm;
	private Date recptnDt;
	private String arvlMsg2;
	public Subway() {
		// TODO Auto-generated constructor stub
	}
	public Subway(String btrainNo, String statnNm, String trnsitCo, String btrainSttus, String barvlDt, String bstatnNm,
			Date recptnDt, String arvlMsg2) {
		super();
		this.btrainNo = btrainNo;
		this.statnNm = statnNm;
		this.trnsitCo = trnsitCo;
		this.btrainSttus = btrainSttus;
		this.barvlDt = barvlDt;
		this.bstatnNm = bstatnNm;
		this.recptnDt = recptnDt;
		this.arvlMsg2 = arvlMsg2;
	}
	public String getBtrainNo() {
		return btrainNo;
	}
	public void setBtrainNo(String btrainNo) {
		this.btrainNo = btrainNo;
	}
	public String getStatnNm() {
		return statnNm;
	}
	public void setStatnNm(String statnNm) {
		this.statnNm = statnNm;
	}
	public String getTrnsitCo() {
		return trnsitCo;
	}
	public void setTrnsitCo(String trnsitCo) {
		this.trnsitCo = trnsitCo;
	}
	public String getBtrainSttus() {
		return btrainSttus;
	}
	public void setBtrainSttus(String btrainSttus) {
		this.btrainSttus = btrainSttus;
	}
	public String getBarvlDt() {
		return barvlDt;
	}
	public void setBarvlDt(String barvlDt) {
		this.barvlDt = barvlDt;
	}
	public String getBstatnNm() {
		return bstatnNm;
	}
	public void setBstatnNm(String bstatnNm) {
		this.bstatnNm = bstatnNm;
	}
	public Date getRecptnDt() {
		return recptnDt;
	}
	public void setRecptnDt(Date recptnDt) {
		this.recptnDt = recptnDt;
	}
	public String getArvlMsg2() {
		return arvlMsg2;
	}
	public void setArvlMsg2(String arvlMsg2) {
		this.arvlMsg2 = arvlMsg2;
	}
}
