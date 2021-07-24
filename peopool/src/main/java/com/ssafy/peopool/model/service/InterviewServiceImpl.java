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
	public void registerURL(String url) throws SQLException {
		// TODO Auto-generated method stub
		interviewRepo.registerURL(url);
	}

	@Override
	public List<InterviewRepo> getInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.getInterviews(index);
	}

	@Override
	public List<InterviewRepo> getLastInterviews(int index) throws SQLException {
		// TODO Auto-generated method stub
		return interviewRepo.getLastInterviews(index);
	}

	@Override
	public void finishInterview() throws SQLException {
		// TODO Auto-generated method stub
		interviewRepo.finishInterview();
	}

	@Override
	public void isInterview() throws SQLException {
		// TODO Auto-generated method stub
		interviewRepo.isInterview();
	}

	@Override
	public void registerInterview(InterviewRepo interview) throws SQLException {
		// TODO Auto-generated method stub
		interviewRepo.registerInterview(interview);
	}

}
