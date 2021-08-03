package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.Interview;

@Repository
public interface InterviewRepo {
	int registerURL(Interview interview) throws SQLException;

	List<Interview> getInterviews(int index) throws SQLException;

	List<Interview> getLastInterviews(int index) throws SQLException;

	int finishInterview(Interview interview) throws SQLException;

	int isInterview(Interview interview) throws SQLException;

	int registerInterview(Interview interview) throws SQLException;
}
