package com.ssafy.peopool.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InterviewDays implements Comparable<InterviewDays> {
	String date;
	
	@JsonProperty("interviewers")
	private List<IntCard> interviewers = new ArrayList<IntCard>();
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public List<IntCard> getInterviewers() {
		return interviewers;
	}
	public void setInterviewers(List<IntCard> interviewers) {
		this.interviewers = interviewers;
	}
	
	public String toString() {
		String st = date + "\n";
		for(int i = 0; i < interviewers.size(); i++) {
			st += "{ " +(interviewers.get(i).toString() + "}, ");
		}
		return st;
	}

	@Override
	public int compareTo(InterviewDays o) {
		return this.date.compareTo(o.date);
	}
}
