package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.ProfileOfIndividual;
import com.ssafy.peopool.model.repo.ProfileOfIndividualRepo;

@Service
public class ProfileOfIndividualServiceImpl implements ProfileOfIndividualService {

	@Autowired
	ProfileOfIndividualRepo profileOfIndividualRepo;

	@Override
	public List<ProfileOfIndividual> getAllProfile() {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getAllProfile();
	}
	
	@Override
	public boolean modifyProfile(ProfileOfIndividual profileOfIndividual) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifyProfile(profileOfIndividual) == 1;
	}

	@Override
	public boolean modifySwitchOn(ProfileOfIndividual profileOfIndividual) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifySwitchOn(profileOfIndividual) == 1;
	}

	@Override
	public boolean modifySwitchOff(ProfileOfIndividual profileOfIndividual) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifySwitchOff(profileOfIndividual) == 1;
	}

	@Override
	public ProfileOfIndividual getProfile(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getProfile(index);
	}

	@Override
	public boolean deleteProfile(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.deleteProfile(index) == 1;
	}

	

}
