package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.dto.Recruit;
import com.ssafy.peopool.model.repo.RecruitRepo;

@Service
public class RecruitServiceImpl implements RecruitService{

	@Autowired
	RecruitRepo recruitRepo;
	
	
	
	@Override
	public List<Recruit> getAllRecruit() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recruit> getERecruit(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recruit> getRecruit(boolean check) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerRecruit(Recruit recruit) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyRecruit(Recruit recruit) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecruit(int index) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Recruit getRecruit(int index) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
