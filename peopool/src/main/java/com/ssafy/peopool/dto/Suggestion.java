package com.ssafy.peopool.dto;

// 면접제얀
public class Suggestion {
	int	sug_index;	// 면접제안 인덱스
	String sug_send;	// 전송시간
	String sug_timeone;	// 첫번재 제안시간
	String sug_timetwo;	// 두번재 제안시간
	String sug_timethree;	// 세번재 제안시간
	int ind_index;	// 회원 인덱스
	int ent_index;	// 기업 인덱스
	String sug_decision;	// 결정시간
	
	public Suggestion() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InterviewSuggestion [sug_index=").append(sug_index).append(", sug_send=").append(sug_send)
				.append(", sug_timeone=").append(sug_timeone).append(", sug_timetwo=").append(sug_timetwo)
				.append(", sug_timethree=").append(sug_timethree).append(", ind_index=").append(ind_index)
				.append(", ent_index=").append(ent_index).append(", sug_decision=").append(sug_decision).append("]");
		return builder.toString();
	}
}
