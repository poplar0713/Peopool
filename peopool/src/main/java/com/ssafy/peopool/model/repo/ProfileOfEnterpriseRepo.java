package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.EntCard;
import com.ssafy.peopool.model.EntCardImage;
import com.ssafy.peopool.model.ProfileOfEnterprise;

@Repository
public interface ProfileOfEnterpriseRepo {

	// 전체 프로필 조회
	List<EntCard> getAllProfile() throws SQLException;
	
	// 전체 프로필 조회 (팔로워 순)
	List<EntCardImage> getAllProfileByFollower() throws SQLException;
	
	// 프로필 수정
	int modifyProfileOfEnterprise(ProfileOfEnterprise profileOfEnterprise) throws SQLException;

	// 프로필 삭제
	int deleteProfileOfEnterprise(int index) throws SQLException;
	
	// 파일 경로와 프로필 조회
	List<Map<Object, Object>> getProfile(int index);

	// 프로필 조회
	EntCard getProfileOfEnterprise(int index) throws SQLException;

	// 이름으로 회사 검색
	List<EntCard> getNameOfEnterprise(String name) throws SQLException;
	
	// 기업소개에서 회사 검색
	List<EntCard> getIntroOfEnterprise(String word) throws SQLException;
	
	// 분류에서 회사 검색
	List<EntCard> getClassOfEnterprise(String word) throws SQLException;
	
	// 사진 등록
	int modifyPhoto(ProfileOfEnterprise profileOfEnterprise) throws SQLException;;
}
