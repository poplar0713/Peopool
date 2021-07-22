package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import com.ssafy.peopool.dto.ProfileOfIndividual;

public interface ProfileOfIndividualService {
	

	// 이력서 수정
	void modifyResume(String file, int index) throws SQLException;

	// 이력서 삭제
	void deleteResume(int index) throws SQLException;

	// 동영상 수정
	void modifyVideo(String file, int index) throws SQLException;

	// 동영상 삭제
	void deleteVideo(int index) throws SQLException;

	// 사진 수정
	void modifyPhoto(String file, int index) throws SQLException;

	// 사진 삭제
	void deletePhoto(int index) throws SQLException;

	// 자기소개 수정
	void modifyIntroduce(String file, int index) throws SQLException;

	// 자기소개 삭제
	void deleteIntroduce(int index) throws SQLException;

	// 프로필 공개
	void modifySwitchOn(int index) throws SQLException;

	// 프로필 비공개
	void modifySwitchOff(int index) throws SQLException;

	// 프로필 조회
	ProfileOfIndividual getProfile(int index) throws SQLException;

}
