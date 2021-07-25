package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Recruit;
import com.ssafy.peopool.model.repo.RecruitRepo;

@Service
public class RecruitServiceImpl implements RecruitService{

	@Autowired
	RecruitRepo recruitRepo;
	
	
	
	@Override
	public List<Recruit> getAllRecruit(){
		// TODO Auto-generated method stub
		return recruitRepo.getAllRecruit();
	}

	@Override
	public List<Recruit> getERecruit(int index){
		// TODO Auto-generated method stub
		return recruitRepo.getERecruit(index);
	}

	@Override
	public List<Recruit> getRecruit(boolean check){
		// TODO Auto-generated method stub
		return recruitRepo.getRecruit(check);
	}

	@Override
	public int registerRecruit(Recruit recruit){
		// TODO Auto-generated method stub
		return recruitRepo.registerRecruit(recruit);
	}

	@Override
	public int modifyRecruit(Recruit recruit){
		// TODO Auto-generated method stub
		return recruitRepo.modifyRecruit(recruit);
	}

	@Override
	public int deleteRecruit(int index){
		// TODO Auto-generated method stub
		return recruitRepo.deleteRecruit(index);
	}

	@Override
	public Recruit getRecruit(int index){
		// TODO Auto-generated method stub
		return recruitRepo.getRecruit(index);
	}

}
