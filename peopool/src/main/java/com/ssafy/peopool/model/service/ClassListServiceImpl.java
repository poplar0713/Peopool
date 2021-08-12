package com.ssafy.peopool.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.ClassList;
import com.ssafy.peopool.model.repo.ClassListRepo;

@Service
public class ClassListServiceImpl implements ClassListService {
	
	@Autowired
	ClassListRepo classListRepo;

	@Override
	public List<ClassList> getClassList() {
		// TODO Auto-generated method stub
		return classListRepo.getClassList();
	}
	
	

}
