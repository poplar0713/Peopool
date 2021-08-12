package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.model.IndCard;
import com.ssafy.peopool.model.ProfileOfIndividual;

public interface ProfileOfIndividualService {

	// 프로필 조회
	List<IndCard> getAllProfile();
	
	// 프로필 수정
	boolean modifyProfile(ProfileOfIndividual profileOfIndividual);

	// 프로필 공개
	boolean modifySwitchOn(ProfileOfIndividual profileOfIndividual);

	// 프로필 비공개
	boolean modifySwitchOff(ProfileOfIndividual profileOfIndividual);
	
	// 프로필 삭제
	boolean deleteProfile(int index);
	
	// 이력서 등록
	boolean modifyResume(ProfileOfIndividual profileOfIndividual);
	
	// 사진 등록
	boolean modifyPhoto(ProfileOfIndividual profileOfIndividual);
	
	// 영상 등록
	boolean modifyVideo(ProfileOfIndividual profileOfIndividual);

	// 프로필 조회
	IndCard getProfile(int index);
	
	// 기술스택을 가진 사용자 프로필 조회
	List<IndCard> getTagOfProfile(String word);

}
