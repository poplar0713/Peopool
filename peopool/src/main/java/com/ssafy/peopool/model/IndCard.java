package com.ssafy.peopool.model;

public class IndCard {
	int ind_index; // 회원 인덱스
	String ind_name; // 회원이름
	char ind_gender; // 회원 성별
	String ind_phone; // 회원 연락처
	String ind_email; // 회원 이메일
	String ind_resume; // 회원 이력서
	String ind_video; // 회원 PR동영상
	String ind_photo; // 회원 사진
	String ind_switch; // On-Off 스위치
	String ind_introduce; // 회원 자기소개
	
	public IndCard() {
		
	}

	public int getInd_index() {
		return ind_index;
	}

	public void setInd_index(int ind_index) {
		this.ind_index = ind_index;
	}

	public String getInd_name() {
		return ind_name;
	}

	public void setInd_name(String ind_name) {
		this.ind_name = ind_name;
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

	public String getInd_resume() {
		return ind_resume;
	}

	public void setInd_resume(String ind_resume) {
		this.ind_resume = ind_resume;
	}

	public String getInd_video() {
		return ind_video;
	}

	public void setInd_video(String ind_video) {
		this.ind_video = ind_video;
	}

	public String getInd_photo() {
		return ind_photo;
	}

	public void setInd_photo(String ind_photo) {
		this.ind_photo = ind_photo;
	}

	public String getInd_switch() {
		return ind_switch;
	}

	public void setInd_switch(String ind_switch) {
		this.ind_switch = ind_switch;
	}

	public String getInd_introduce() {
		return ind_introduce;
	}

	public void setInd_introduce(String ind_introduce) {
		this.ind_introduce = ind_introduce;
	}

	@Override
	public String toString() {
		return "IndCard [ind_index=" + ind_index + ", ind_name=" + ind_name + ", ind_gender=" + ind_gender
				+ ", ind_phone=" + ind_phone + ", ind_email=" + ind_email + ", ind_resume=" + ind_resume
				+ ", ind_video=" + ind_video + ", ind_photo=" + ind_photo + ", ind_switch=" + ind_switch
				+ ", ind_introduce=" + ind_introduce + "]";
	}
	
	
	

}
