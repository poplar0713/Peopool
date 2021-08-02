package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.model.Interview;
import com.ssafy.peopool.model.repo.InterviewRepo;

public interface InterviewService {
	
	boolean registerURL(String url) throws SQLException;

	List<Interview> getInterviews(int index) throws SQLException;

	List<Interview> getLastInterviews(int index) throws SQLException;

	boolean finishInterview(Interview interview) throws SQLException;

	boolean isInterview(Interview interview) throws SQLException;

	boolean registerInterview(Interview interview) throws SQLException;

}
