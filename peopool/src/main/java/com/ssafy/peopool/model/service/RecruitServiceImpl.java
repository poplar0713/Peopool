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
		return recruitRepo.getAllRecruit();
	}

	@Override
	public List<Recruit> getERecruit(int index) throws SQLException {
		// TODO Auto-generated method stub
		return recruitRepo.getERecruit(index);
	}

	@Override
	public List<Recruit> getRecruit(boolean check) throws SQLException {
		// TODO Auto-generated method stub
		return recruitRepo.getRecruit(check);
	}

	@Override
	public void registerRecruit(Recruit recruit) throws SQLException {
		// TODO Auto-generated method stub
		recruitRepo.registerRecruit(recruit);
	}

	@Override
	public void modifyRecruit(Recruit recruit) throws SQLException {
		// TODO Auto-generated method stub
		recruitRepo.modifyRecruit(recruit);
	}

	@Override
	public void deleteRecruit(int index) throws SQLException {
		// TODO Auto-generated method stub
		recruitRepo.deleteRecruit(index);
	}

	@Override
	public Recruit getRecruit(int index) throws SQLException {
		// TODO Auto-generated method stub
		return recruitRepo.getRecruit(index);
	}

}
