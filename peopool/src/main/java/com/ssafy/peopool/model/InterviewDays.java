package com.ssafy.peopool.model;

import java.util.ArrayList;

public class InterviewDays {
	String date;
	private ArrayList<IntCard> interviewers = new ArrayList<IntCard>();
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public ArrayList<IntCard> getInterviewers() {
		return interviewers;
	}
	public void setInterviewers(ArrayList<IntCard> interviewers) {
		this.interviewers = interviewers;
	}
	
	public String toString() {
		String st = date + "\n";
		for(int i = 0; i < interviewers.size(); i++) {
			st += "{ " +(interviewers.get(i).toString() + "}, ");
		}
		return st;
	}
}
