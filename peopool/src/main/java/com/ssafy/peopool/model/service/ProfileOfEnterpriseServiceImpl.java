package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.EntCard;
import com.ssafy.peopool.model.ProfileOfEnterprise;
import com.ssafy.peopool.model.repo.ProfileOfEnterpriseRepo;

@Service
public class ProfileOfEnterpriseServiceImpl implements ProfileOfEnterpriseService {

	@Autowired
	ProfileOfEnterpriseRepo profileOfEnterpriseRepo;
	
	@Override
	public List<EntCard> getAllProfile() throws SQLException {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.getAllProfile();
	}
	
	@Override
	public boolean modifyProfileOfEnterprise(ProfileOfEnterprise profileOfEnterprise) throws SQLException {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.modifyProfileOfEnterprise(profileOfEnterprise) == 1;
	}

	@Override
	public boolean deleteProfileOfEnterprise(int index) throws SQLException {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.deleteProfileOfEnterprise(index) == 1;
	}

	@Override
	public EntCard getProfileOfEnterprise(int index) throws SQLException {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.getProfileOfEnterprise(index);
	}

	@Override
	public List<EntCard> getNameOfEnterprise(String name) throws SQLException {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.getNameOfEnterprise(name);
	}

	@Override
	public List<EntCard> getIntroOfEnterprise(String word) throws SQLException {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.getIntroOfEnterprise(word);
	}

	@Override
	public List<EntCard> getClassOfEnterprise(String word) throws SQLException {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.getClassOfEnterprise(word);
	}

	
}
