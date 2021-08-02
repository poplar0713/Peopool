package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import com.ssafy.peopool.model.ProfileOfEnterprise;

public interface ProfileOfEnterpriseService {
	

	// 프로필 수정
	boolean modifyProfileOfEnterprise(ProfileOfEnterprise profileOfEnterprise) throws SQLException;

	// 프로필 삭제
	boolean deleteProfileOfEnterprise(int index) throws SQLException;

	// 프로필 조회
	ProfileOfEnterprise getProfileOfEnterprise(int index) throws SQLException;

	// 이름으로 회사 검색
	ProfileOfEnterprise getNameOfEnterprise(String name) throws SQLException;

}
