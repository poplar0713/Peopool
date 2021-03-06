package com.ssafy.peopool.model;

public class SugCard {
	int sug_index; // 면접제안 인덱스
	String sug_send; // 전송시간
	String sug_timeone; // 첫번재 제안시간
	String sug_timetwo; // 두번재 제안시간
	String sug_timethree; // 세번재 제안시간
	int ind_index; // 회원 인덱스
	int ent_index; // 기업 인덱스
	String sug_decision; // 결정시간
	String sug_state; // 요청 상태
	String sug_duty;// 직무
	String sug_message;// 메세지
	String name; // 기업 or 개인 이름
	
	public SugCard() {
		
	}

	public int getSug_index() {
		return sug_index;
	}

	public void setSug_index(int sug_index) {
		this.sug_index = sug_index;
	}

	public String getSug_send() {
		return sug_send;
	}

	public void setSug_send(String sug_send) {
		this.sug_send = sug_send;
	}

	public String getSug_timeone() {
		return sug_timeone;
	}

	public void setSug_timeone(String sug_timeone) {
		this.sug_timeone = sug_timeone;
	}

	public String getSug_timetwo() {
		return sug_timetwo;
	}

	public void setSug_timetwo(String sug_timetwo) {
		this.sug_timetwo = sug_timetwo;
	}

	public String getSug_timethree() {
		return sug_timethree;
	}

	public void setSug_timethree(String sug_timethree) {
		this.sug_timethree = sug_timethree;
	}

	public int getInd_index() {
		return ind_index;
	}

	public void setInd_index(int ind_index) {
		this.ind_index = ind_index;
	}

	public int getEnt_index() {
		return ent_index;
	}

	public void setEnt_index(int ent_index) {
		this.ent_index = ent_index;
	}

	public String getSug_decision() {
		return sug_decision;
	}

	public void setSug_decision(String sug_decision) {
		this.sug_decision = sug_decision;
	}

	public String getSug_state() {
		return sug_state;
	}

	public void setSug_state(String sug_state) {
		this.sug_state = sug_state;
	}

	public String getSug_duty() {
		return sug_duty;
	}

	public void setSug_duty(String sug_duty) {
		this.sug_duty = sug_duty;
	}

	public String getSug_message() {
		return sug_message;
	}

	public void setSug_message(String sug_message) {
		this.sug_message = sug_message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SugCard [sug_index=" + sug_index + ", sug_send=" + sug_send + ", sug_timeone=" + sug_timeone
				+ ", sug_timetwo=" + sug_timetwo + ", sug_timethree=" + sug_timethree + ", ind_index=" + ind_index
				+ ", ent_index=" + ent_index + ", sug_decision=" + sug_decision + ", sug_state=" + sug_state
				+ ", sug_duty=" + sug_duty + ", sug_message=" + sug_message + ", name=" + name + "]";
	}
	
	

}
