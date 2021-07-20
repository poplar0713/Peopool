package com.ssafy.peopool.dto;

// 기업 회원
public class Enterprise {
	int ent_index;	// 기업 인덱스
	String ent_id;	// 기업 아이디
	String ent_password; // 기업 비밀번호
	String ent_name;	// 기업이름
	String ent_contact;	// 기업 연락처
	String ent_email;	// 기업 이메일
	
	
	public Enterprise() {
	}


	public int getEnt_index() {
		return ent_index;
	}


	public void setEnt_index(int ent_index) {
		this.ent_index = ent_index;
	}


	public String getEnt_id() {
		return ent_id;
	}


	public void setEnt_id(String ent_id) {
		this.ent_id = ent_id;
	}


	public String getEnt_password() {
		return ent_password;
	}


	public void setEnt_password(String ent_password) {
		this.ent_password = ent_password;
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


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enterprise [ent_index=").append(ent_index).append(", ent_id=").append(ent_id)
				.append(", ent_password=").append(ent_password).append(", ent_name=").append(ent_name)
				.append(", ent_contact=").append(ent_contact).append(", ent_email=").append(ent_email).append("]");
		return builder.toString();
	}
	
	
}
