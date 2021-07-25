package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.ProfileOfIndividual;
import com.ssafy.peopool.model.repo.ProfileOfIndividualRepo;

@Service
public class ProfileOfIndividualServiceImpl implements ProfileOfIndividualService {

	@Autowired
	ProfileOfIndividualRepo profileOfIndividualRepo;
	
	@Override
	public void modifyProfile(String file, int index) throws SQLException {
		// TODO Auto-generated method stub
		profileOfIndividualRepo.modifyProfile(file, index);
	}

	@Override
	public void modifySwitchOn(int index) throws SQLException {
		// TODO Auto-generated method stub
		profileOfIndividualRepo.modifySwitchOn(index);
	}

	@Override
	public void modifySwitchOff(int index) throws SQLException {
		// TODO Auto-generated method stub
		profileOfIndividualRepo.modifySwitchOff(index);
	}

	@Override
	public ProfileOfIndividual getProfile(int index) throws SQLException {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getProfile(index);
	}

}
