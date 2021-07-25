package com.ssafy.peopool.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.ProfileOfEnterprise;

@Repository
public interface ProfileOfEnterpriseRepo {

	// 프로필 수정
	void modifyProfileOfEnterprise(ProfileOfEnterprise profileOfEnterprise) throws SQLException;

	// 프로필 삭제
	void deleteProfileOfEnterprise(int index) throws SQLException;

	// 프로필 조회
	ProfileOfEnterprise getProfileOfEnterprise(int index) throws SQLException;

	// 이름으로 회사 검색
	ProfileOfEnterprise getNameOfEnterprise(String name) throws SQLException;
}
