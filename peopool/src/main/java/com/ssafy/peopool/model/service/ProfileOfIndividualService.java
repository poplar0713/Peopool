package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import com.ssafy.peopool.model.ProfileOfIndividual;

public interface ProfileOfIndividualService {

	// 프로필 수정
	boolean modifyProfile(ProfileOfIndividual profile, int index);

	// 프로필 공개
	boolean modifySwitchOn(int index);

	// 프로필 비공개
	boolean modifySwitchOff(int index);

	// 프로필 조회
	ProfileOfIndividual getProfile(int index);

}
