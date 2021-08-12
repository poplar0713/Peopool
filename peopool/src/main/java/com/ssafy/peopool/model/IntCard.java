package com.ssafy.peopool.model;

public class IntCard {
	
	int int_index; // 일정 인덱스
	int ind_index; // 회원 인덱스
	int ent_index; // 기업 인덱스
	String int_start; // 면접 시작 시간
	String int_end; // 면접 종료 시간
	String int_roomnumber; // 면접 room number
	String int_show; // 진행 여부 (show / now show)
	String name; // 개인  or 기업 이름
	String int_done; // 면접 결과
	
	public IntCard() {
		
	}

	public int getInt_index() {
		return int_index;
	}

	public void setInt_index(int int_index) {
		this.int_index = int_index;
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

	public String getInt_start() {
		return int_start;
	}

	public void setInt_start(String int_start) {
		this.int_start = int_start;
	}

	public String getInt_end() {
		return int_end;
	}

	public void setInt_end(String int_end) {
		this.int_end = int_end;
	}

	public String getInt_roomnumber() {
		return int_roomnumber;
	}

	public void setInt_roomnumber(String int_roomnumber) {
		this.int_roomnumber = int_roomnumber;
	}

	public String getInt_show() {
		return int_show;
	}

	public void setInt_show(String int_show) {
		this.int_show = int_show;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IntCard [int_index=" + int_index + ", ind_index=" + ind_index + ", ent_index=" + ent_index
				+ ", int_start=" + int_start + ", int_end=" + int_end + ", int_roomnumber=" + int_roomnumber
				+ ", int_show=" + int_show + ", name=" + name + ", getInt_index()=" + getInt_index()
				+ ", getInd_index()=" + getInd_index() + ", getEnt_index()=" + getEnt_index() + ", getInt_start()="
				+ getInt_start() + ", getInt_end()=" + getInt_end() + ", getInt_roomnumber()=" + getInt_roomnumber()
				+ ", getInt_show()=" + getInt_show() + ", getName()=" + getName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}
