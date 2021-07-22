package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.repo.InterviewRepo;

@Service
public class InterviewServiceImpl implements InterviewService{

	@Autowired
	InterviewRepo interviewRepo;
	
	
	@Override
	public void registerUL(String url) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InterviewRepo> getInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterviewRepo> getLastInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finishInterview() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isInterview() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerInterview(InterviewRepo interview) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
