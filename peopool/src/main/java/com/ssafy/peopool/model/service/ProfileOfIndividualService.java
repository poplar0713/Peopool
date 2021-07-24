package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import com.ssafy.peopool.dto.ProfileOfIndividual;

public interface ProfileOfIndividualService {

	// 프로필 수정
	void modifyProfile(String file, int index) throws SQLException;

	// 프로필 공개
	void modifySwitchOn(int index) throws SQLException;

	// 프로필 비공개
	void modifySwitchOff(int index) throws SQLException;

	// 프로필 조회
	ProfileOfIndividual getProfile(int index) throws SQLException;

}
