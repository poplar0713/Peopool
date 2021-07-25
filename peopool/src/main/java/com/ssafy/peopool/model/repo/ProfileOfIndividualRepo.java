package com.ssafy.peopool.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.ProfileOfIndividual;

@Repository
public interface ProfileOfIndividualRepo {

	// 프로필 수정
	void modifyProfile(String file, int index) throws SQLException;

	// 프로필 공개
	void modifySwitchOn(int index) throws SQLException;

	// 프로필 비공개
	void modifySwitchOff(int index) throws SQLException;

	// 프로필 조회
	ProfileOfIndividual getProfile(int index) throws SQLException;

}
