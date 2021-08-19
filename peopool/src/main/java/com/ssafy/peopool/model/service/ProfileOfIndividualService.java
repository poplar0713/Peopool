package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.peopool.model.IndCard;
import com.ssafy.peopool.model.ProfileOfIndividual;

public interface ProfileOfIndividualService {

	// 프로필 조회
	List<IndCard> getAllProfile();
	
	// 프로필 수정
	boolean modifyProfile(ProfileOfIndividual profileOfIndividual);
	
	// 자기소개 수정
	boolean modifyIntro(ProfileOfIndividual profileOfIndividual);

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
	Map<Object, Object> getProfile(int index);
	
	// 기술스택을 가진 사용자 프로필 조회
	List<IndCard> getTagOfProfile(String word);
	
	// 자기소개에 따른 사용자 프로필 조회
	List<IndCard> getIntroOfProfile(String word);
	
	//인덱스로 가져오기
	IndCard getIndexOfProfile(int index);

	// 인덱스로 직무 번호 변경
	boolean modifyPart(ProfileOfIndividual profileOfIndividual);

	// 인덱스로 경력과 직무 가져오기
	IndCard getIndexOfCareerAndPart(int index);

	//인덱스로 경력 번호 변경
	boolean modifyCareer(ProfileOfIndividual profileOfIndividual);
	

}
