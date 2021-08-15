package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.model.EntCard;
import com.ssafy.peopool.model.ProfileOfEnterprise;

public interface ProfileOfEnterpriseService {
	
	// 전체 프로필 조회
	List<EntCard> getAllProfile() throws SQLException;
	
	// 전체 프로필 조회 (팔로워 순)
	List<EntCard> getAllProfileByFollower() throws SQLException;

	// 프로필 수정
	boolean modifyProfileOfEnterprise(ProfileOfEnterprise profileOfEnterprise) throws SQLException;

	// 프로필 삭제
	boolean deleteProfileOfEnterprise(int index) throws SQLException;

	// 프로필 조회
	EntCard getProfileOfEnterprise(int index) throws SQLException;

	// 이름으로 회사 검색
	List<EntCard> getNameOfEnterprise(String name) throws SQLException;
	
	// 기업소개에서 회사 검색
	List<EntCard> getIntroOfEnterprise(String word) throws SQLException;
	
	// 분류에서 회사 검색
	List<EntCard> getClassOfEnterprise(String word) throws SQLException;
	
	// 사진 등록
	boolean modifyPhoto(ProfileOfEnterprise profileOfEnterprise) throws SQLException;

}
