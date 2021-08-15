package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.EntCard;
import com.ssafy.peopool.model.ProfileOfEnterprise;
import com.ssafy.peopool.model.repo.ProfileOfEnterpriseRepo;

@Service
public class ProfileOfEnterpriseServiceImpl implements ProfileOfEnterpriseService {

	
	private static final Logger logger = LoggerFactory.getLogger( ProfileOfEnterpriseServiceImpl.class);
	
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

	@Override
	public boolean modifyPhoto(ProfileOfEnterprise profileOfEnterprise) throws SQLException {
		if(profileOfEnterprise.getFileInfos() != null) {
			logger.debug("업로드 파일 수 : {}", profileOfEnterprise.getFileInfos().size());
			profileOfEnterpriseRepo.modifyPhoto(profileOfEnterprise);
			logger.debug("파일 번호 : {}", profileOfEnterprise.getEnt_image());
		}
		return profileOfEnterpriseRepo.modifyProfileOfEnterprise(profileOfEnterprise) == 1;
	}

	@Override
	public List<Map<Object, Object>> getProfile(int index) {
		// TODO Auto-generated method stub
		return profileOfEnterpriseRepo.getProfile(index);
	}

	
}
