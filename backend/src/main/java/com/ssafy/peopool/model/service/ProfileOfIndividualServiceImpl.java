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
	public boolean modifyProfile(ProfileOfIndividual profile, int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifyProfile(profile, index) == 1;
	}

	@Override
	public boolean modifySwitchOn(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifySwitchOn(index) == 1;
	}

	@Override
	public boolean modifySwitchOff(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifySwitchOff(index) == 1;
	}

	@Override
	public ProfileOfIndividual getProfile(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getProfile(index);
	}

}
