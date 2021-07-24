package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepo {
	void registerURL(String url) throws SQLException;

	List<InterviewRepo> getInterviews(int index) throws SQLException;

	List<InterviewRepo> getLastInterviews(int index) throws SQLException;

	void finishInterview() throws SQLException;

	void isInterview() throws SQLException;

	void registerInterview(InterviewRepo interview) throws SQLException;
}
