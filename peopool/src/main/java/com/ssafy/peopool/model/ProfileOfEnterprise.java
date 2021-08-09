package com.ssafy.peopool.model;

public class ProfileOfEnterprise {
	int ent_index; // 기업 인덱스
	String ent_image; // 기업 프로필 사진
	String ent_ceo; // 기업 대표
	String ent_history; // 기업 연혁
	String ent_address; // 기업 주소
	String ent_website; // 기업 사이트
	String ent_introduce; // 기업 소개

	public ProfileOfEnterprise() {
	}

	public int getEnt_index() {
		return ent_index;
	}

	public void setEnt_index(int ent_index) {
		this.ent_index = ent_index;
	}

	public String getEnt_image() {
		return ent_image;
	}

	public void setEnt_image(String ent_image) {
		this.ent_image = ent_image;
	}

	public String getEnt_ceo() {
		return ent_ceo;
	}

	public void setEnt_ceo(String ent_ceo) {
		this.ent_ceo = ent_ceo;
	}

	public String getEnt_history() {
		return ent_history;
	}

	public void setEnt_history(String ent_history) {
		this.ent_history = ent_history;
	}

	public String getEnt_address() {
		return ent_address;
	}

	public void setEnt_address(String ent_address) {
		this.ent_address = ent_address;
	}

	public String getEnt_website() {
		return ent_website;
	}

	public void setEnt_website(String ent_website) {
		this.ent_website = ent_website;
	}

	public String getEnt_introduce() {
		return ent_introduce;
	}

	public void setEnt_introduce(String ent_introduce) {
		this.ent_introduce = ent_introduce;
	}

	@Override
	public String toString() {
		return "ProfileOfEnterprise [ent_index=" + ent_index + ", ent_image=" + ent_image + ", ent_ceo=" + ent_ceo
				+ ", ent_history=" + ent_history + ", ent_address=" + ent_address + ", ent_website=" + ent_website
				+ ", ent_introduce=" + ent_introduce + "]";
	}


	
}
