package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.EntCard;
import com.ssafy.peopool.model.ProfileOfEnterprise;

@Repository
public interface ProfileOfEnterpriseRepo {

	// 전체 프로필 조회
	List<EntCard> getAllProfile() throws SQLException;
	
	// 프로필 수정
	int modifyProfileOfEnterprise(ProfileOfEnterprise profileOfEnterprise) throws SQLException;

	// 프로필 삭제
	int deleteProfileOfEnterprise(int index) throws SQLException;

	// 프로필 조회
	EntCard getProfileOfEnterprise(int index) throws SQLException;

	// 이름으로 회사 검색
	List<EntCard> getNameOfEnterprise(String name) throws SQLException;
	
	// 기업소개에서 회사 검색
	List<EntCard> getIntroOfEnterprise(String word) throws SQLException;
	
	// 분류에서 회사 검색
	List<EntCard> getClassOfEnterprise(String word) throws SQLException;
}
