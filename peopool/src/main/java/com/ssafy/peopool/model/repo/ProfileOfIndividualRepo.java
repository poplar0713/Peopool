package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import com.ssafy.peopool.model.IndCard;
import com.ssafy.peopool.model.ProfileOfIndividual;

@Repository
public interface ProfileOfIndividualRepo {

	// 전체 프로필 조회
	List<IndCard> getAllProfile();
	
	// 프로필 수정
	int modifyProfile(ProfileOfIndividual profileOfIndividual);
	
	// 자기소개 수정
	int modifyIntro(ProfileOfIndividual profileOfIndividual);

	// 프로필 공개
	int modifySwitchOn(ProfileOfIndividual profileOfIndividual);

	// 프로필 비공개
	int modifySwitchOff(ProfileOfIndividual profileOfIndividual);
	
	// 프로필 삭제
	int deleteProfile(int index);
	
	// 이력서 등록
	int modifyResume(ProfileOfIndividual profileOfIndividual);
	
	// 사진 등록
	int modifyPhoto(ProfileOfIndividual profileOfIndividual);
	
	// 영상 등록
	int modifyVideo(ProfileOfIndividual profileOfIndividual);

	// 프로필 조회
	Map<Object, Object> getProfile(int index);
	
	// 인덱스로 조회
	IndCard getIndexOfProfile(int index);
	
	// 기술 스택을 가진 사용자 프로필 조회
	List<IndCard> getTagOfProfile(String word);
	
	// 자기소개에 따른 사용자 프로필 조회
	List<IndCard> getIntroOfProfile(String word);

	// 직무 변경
	int modifyPart(ProfileOfIndividual profileOfIndividual);

	// 인덱스로 경력과 직무 조회
	IndCard getIndexOfCareerAndPart(int index);

	// 경력 변경
	int modifyCareer(ProfileOfIndividual profileOfIndividual);
}
