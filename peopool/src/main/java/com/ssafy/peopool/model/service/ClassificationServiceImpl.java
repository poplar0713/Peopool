package com.ssafy.peopool.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Classification;
import com.ssafy.peopool.model.repo.ClassificationRepo;

@Service
public class ClassificationServiceImpl implements ClassificationService{

	@Autowired
	ClassificationRepo classificationRepo;
	
	@Override
	public boolean registerClass(Classification classification) {
		// TODO Auto-generated method stub
		return classificationRepo.registerClass(classification) == 1;
	}

	@Override
	public boolean deleteClass(int index) {
		// TODO Auto-generated method stub
		return classificationRepo.deleteClass(index) == 1;
	}

	@Override
	public List<Map<Object, Object>> getClass(int index) {
		// TODO Auto-generated method stub
		return classificationRepo.getClass(index);
	}

	@Override
	public List<Map<Object, Object>> getEnt(String name) {
		// TODO Auto-generated method stub
		return classificationRepo.getEnt(name);
	}

}
