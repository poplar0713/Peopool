package com.ssafy.peopool.model;

// 개인회원
public class Individual {
	int ind_index; // 회원 Index (PK)
	String ind_id; // 회원아이디
	String ind_password; // 회원비밀번호
	String ind_name; // 회원이름
	String ind_birth; // 회원생년월일
	char ind_gender; // 회원 성별
	String ind_phone; // 회원 연락처
	String ind_email; // 회원 이메일

	public Individual() {
	}

	public int getInd_index() {
		return ind_index;
	}

	public void setInd_index(int ind_index) {
		this.ind_index = ind_index;
	}

	public String getInd_id() {
		return ind_id;
	}

	public void setInd_id(String ind_id) {
		this.ind_id = ind_id;
	}

	public String getInd_password() {
		return ind_password;
	}

	public void setInd_password(String ind_password) {
		this.ind_password = ind_password;
	}

	public String getInd_name() {
		return ind_name;
	}

	public void setInd_name(String ind_name) {
		this.ind_name = ind_name;
	}

	public String getInd_birth() {
		return ind_birth;
	}

	public void setInd_birth(String ind_birth) {
		this.ind_birth = ind_birth;
	}

	public char getInd_gender() {
		return ind_gender;
	}

	public void setInd_gender(char ind_gender) {
		this.ind_gender = ind_gender;
	}

	public String getInd_phone() {
		return ind_phone;
	}

	public void setInd_phone(String ind_phone) {
		this.ind_phone = ind_phone;
	}

	public String getInd_email() {
		return ind_email;
	}

	public void setInd_email(String ind_email) {
		this.ind_email = ind_email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Individual [ind_index=").append(ind_index).append(", ind_id=").append(ind_id)
				.append(", ind_password=").append(ind_password).append(", ind_name=").append(ind_name)
				.append(", ind_birth=").append(ind_birth).append(", ind_gender=").append(ind_gender)
				.append(", ind_phone=").append(ind_phone).append(", ind_email=").append(ind_email).append("]");
		return builder.toString();
	}

}
