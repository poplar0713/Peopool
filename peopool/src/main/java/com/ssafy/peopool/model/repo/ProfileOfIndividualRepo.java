package com.ssafy.peopool.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.ProfileOfIndividual;

@Repository
public interface ProfileOfIndividualRepo {

	// 프로필 수정
	int modifyProfile(ProfileOfIndividual profileOfIndividual);

	// 프로필 공개
	int modifySwitchOn(ProfileOfIndividual profileOfIndividual);

	// 프로필 비공개
	int modifySwitchOff(ProfileOfIndividual profileOfIndividual);
	
	// 프로필 삭제
	int deleteProfile(int index);

	// 프로필 조회
	ProfileOfIndividual getProfile(int index);

}
