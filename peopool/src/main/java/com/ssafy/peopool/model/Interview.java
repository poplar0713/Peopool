package com.ssafy.peopool.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

// 면접 일정
public class Interview {

	int int_index; // 일정 인덱스
	int ind_index; // 회원 인덱스
	int ent_index; // 기업 인덱스
	String int_start; // 면접 시작 시간
	String int_end; // 면접 종료 시간
	String int_roomnumber; // 면접 room number
	String int_show; // 진행 여부 (show / now show)
	String int_done; // 완료 여부
	
	public Interview() {
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
	public String getInt_done() {
		return int_done;
	}
	public void setInt_done(String int_done) {
		this.int_done = int_done;
	}
	@Override
	public String toString() {
		return "Interview [int_index=" + int_index + ", ind_index=" + ind_index + ", ent_index=" + ent_index
				+ ", int_start=" + int_start + ", int_end=" + int_end + ", int_roomnumber=" + int_roomnumber
				+ ", int_show=" + int_show + ", int_done=" + int_done + "]";
	}

	

	

	
	
}
