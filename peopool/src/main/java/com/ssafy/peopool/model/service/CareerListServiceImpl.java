package com.ssafy.peopool.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.CareerList;
import com.ssafy.peopool.model.repo.CareerListRepo;

@Service
public class CareerListServiceImpl implements CareerListService {

	@Autowired
	CareerListRepo careerListRepo;
	
	@Override
	public List<CareerList> getCareerList() {
		// TODO Auto-generated method stub
		return careerListRepo.getCareerList();
	}


}
