package com.ssafy.peopool.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.ProfileOfIndividual;

@Repository
public interface ProfileOfIndividualRepo {

	// 프로필 수정
	int modifyProfile(ProfileOfIndividual profile, int index);

	// 프로필 공개
	int modifySwitchOn(int index);

	// 프로필 비공개
	int modifySwitchOff(int index);

	// 프로필 조회
	ProfileOfIndividual getProfile(int index);

}
