package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.peopool.model.IndCard;
import com.ssafy.peopool.model.ProfileOfIndividual;
import com.ssafy.peopool.model.repo.ProfileOfIndividualRepo;

@Service
public class ProfileOfIndividualServiceImpl implements ProfileOfIndividualService {

	
	private static final Logger logger = LoggerFactory.getLogger( ProfileOfIndividualServiceImpl.class);
	
	@Autowired
	ProfileOfIndividualRepo profileOfIndividualRepo;

	@Override
	public List<IndCard> getAllProfile() {
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
	public Map<Object, Object> getProfile(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getProfile(index);
	}

	@Override
	public boolean deleteProfile(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.deleteProfile(index) == 1;
	}

	@Override
	@Transactional
	public boolean modifyResume(ProfileOfIndividual profileOfIndividual) {
		// TODO Auto-generated method stub
		if(profileOfIndividual.getFileInfos() != null) {
			logger.debug("업로드 파일 수 : {}", profileOfIndividual.getFileInfos().size());
			profileOfIndividualRepo.modifyResume(profileOfIndividual);
			logger.debug("파일 번호 : {}", profileOfIndividual.getInd_resume());
		}
		return profileOfIndividualRepo.modifyProfile(profileOfIndividual) == 1;
		
	}

	@Override
	public boolean modifyPhoto(ProfileOfIndividual profileOfIndividual) {
		if(profileOfIndividual.getFileInfos() != null) {
			logger.debug("업로드 파일 수 : {}", profileOfIndividual.getFileInfos().size());
			profileOfIndividualRepo.modifyPhoto(profileOfIndividual);
			logger.debug("파일 번호 : {}", profileOfIndividual.getInd_photo());
		}
		return profileOfIndividualRepo.modifyProfile(profileOfIndividual) == 1;
	}

	@Override
	public boolean modifyVideo(ProfileOfIndividual profileOfIndividual) {
		if(profileOfIndividual.getFileInfos() != null) {
			logger.debug("업로드 파일 수 : {}", profileOfIndividual.getFileInfos().size());
			profileOfIndividualRepo.modifyVideo(profileOfIndividual);
			logger.debug("파일 번호 : {}", profileOfIndividual.getInd_video());
		}
		return profileOfIndividualRepo.modifyProfile(profileOfIndividual) == 1;
	}

	@Override
	public List<IndCard> getTagOfProfile(String word) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getTagOfProfile(word);
	}

	@Override
	public List<IndCard> getIntroOfProfile(String word) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getIntroOfProfile(word);
	}

	@Override
	public IndCard getIndexOfProfile(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getIndexOfProfile(index);
	}

	@Override
	public boolean modifyPart(ProfileOfIndividual profileOfIndividual) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifyPart(profileOfIndividual) == 1;
	}

	@Override
	public IndCard getIndexOfCareerAndPart(int index) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.getIndexOfCareerAndPart(index);
	}

	@Override
	public boolean modifyCareer(ProfileOfIndividual profileOfIndividual) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifyCareer(profileOfIndividual) == 1;
	}

	@Override
	public boolean modifyIntro(ProfileOfIndividual profileOfIndividual) {
		// TODO Auto-generated method stub
		return profileOfIndividualRepo.modifyIntro(profileOfIndividual) == 1;
	}

}
