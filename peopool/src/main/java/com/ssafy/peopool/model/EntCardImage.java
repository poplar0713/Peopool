package com.ssafy.peopool.model;

public class EntCardImage {
	int ent_index; // 기업 인덱스
	String ent_name; // 기업이름
	String ent_contact; // 기업 연락처
	String ent_email; // 기업 이메일
	String ent_image; // 기업 프로필 사진
	String ent_ceo; // 기업 대표
	String ent_history; // 기업 연혁
	String ent_address; // 기업 주소
	String ent_website; // 기업 사이트
	String ent_introduce; // 기업 소개
	String savefolder;
	String savefile;
	String originfile;
	
	public EntCardImage() {
		super();
	}

	public int getEnt_index() {
		return ent_index;
	}

	public void setEnt_index(int ent_index) {
		this.ent_index = ent_index;
	}

	public String getEnt_name() {
		return ent_name;
	}

	public void setEnt_name(String ent_name) {
		this.ent_name = ent_name;
	}

	public String getEnt_contact() {
		return ent_contact;
	}

	public void setEnt_contact(String ent_contact) {
		this.ent_contact = ent_contact;
	}

	public String getEnt_email() {
		return ent_email;
	}

	public void setEnt_email(String ent_email) {
		this.ent_email = ent_email;
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

	public String getSavefolder() {
		return savefolder;
	}

	public void setSavefolder(String savefolder) {
		this.savefolder = savefolder;
	}

	public String getSavefile() {
		return savefile;
	}

	public void setSavefile(String savefile) {
		this.savefile = savefile;
	}

	public String getOriginfile() {
		return originfile;
	}

	public void setOriginfile(String originfile) {
		this.originfile = originfile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntCardImage [ent_index=").append(ent_index).append(", ent_name=").append(ent_name)
				.append(", ent_contact=").append(ent_contact).append(", ent_email=").append(ent_email)
				.append(", ent_image=").append(ent_image).append(", ent_ceo=").append(ent_ceo).append(", ent_history=")
				.append(ent_history).append(", ent_address=").append(ent_address).append(", ent_website=")
				.append(ent_website).append(", ent_introduce=").append(ent_introduce).append(", savefolder=")
				.append(savefolder).append(", savefile=").append(savefile).append(", originfile=").append(originfile)
				.append("]");
		return builder.toString();
	}

	
}
