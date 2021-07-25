package com.ssafy.peopool.model;

public class Recruit {
	int rec_index; // 공고 인덱스
	int ent_index; // 기업 인덱스
	String rec_detail; // 업무내용
	String rec_qualifications; // 자격사항
	String rec_preferention; // 우대사항
	String rec_starttime; // 공고 시작 시간
	String rec_endtime; // 공고 종료 시간
	String rec_requiredDoc; // 공고 필수 서류
	char rec_deadline; // 공고 마감 여부

	public Recruit() {
	}

	public int getRec_index() {
		return rec_index;
	}

	public void setRec_index(int rec_index) {
		this.rec_index = rec_index;
	}

	public int getEnt_index() {
		return ent_index;
	}

	public void setEnt_index(int ent_index) {
		this.ent_index = ent_index;
	}

	public String getRec_detail() {
		return rec_detail;
	}

	public void setRec_detail(String rec_detail) {
		this.rec_detail = rec_detail;
	}

	public String getRec_qualifications() {
		return rec_qualifications;
	}

	public void setRec_qualifications(String rec_qualifications) {
		this.rec_qualifications = rec_qualifications;
	}

	public String getRec_preferention() {
		return rec_preferention;
	}

	public void setRec_preferention(String rec_preferention) {
		this.rec_preferention = rec_preferention;
	}

	public String getRec_starttime() {
		return rec_starttime;
	}

	public void setRec_starttime(String rec_starttime) {
		this.rec_starttime = rec_starttime;
	}

	public String getRec_endtime() {
		return rec_endtime;
	}

	public void setRec_endtime(String rec_endtime) {
		this.rec_endtime = rec_endtime;
	}

	public String getRec_requiredDoc() {
		return rec_requiredDoc;
	}

	public void setRec_requiredDoc(String rec_requiredDoc) {
		this.rec_requiredDoc = rec_requiredDoc;
	}

	public char getRec_deadline() {
		return rec_deadline;
	}

	public void setRec_deadline(char rec_deadline) {
		this.rec_deadline = rec_deadline;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recruit [rec_index=").append(rec_index).append(", ent_index=").append(ent_index)
				.append(", rec_detail=").append(rec_detail).append(", rec_qualifications=").append(rec_qualifications)
				.append(", rec_preferention=").append(rec_preferention).append(", rec_starttime=").append(rec_starttime)
				.append(", rec_endtime=").append(rec_endtime).append(", rec_requiredDoc=").append(rec_requiredDoc)
				.append(", rec_deadline=").append(rec_deadline).append("]");
		return builder.toString();
	}

}
