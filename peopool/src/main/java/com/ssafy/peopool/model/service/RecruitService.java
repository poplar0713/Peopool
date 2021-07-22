package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.dto.Recruit;

public interface RecruitService {
	

	// 전체 공고 조회
	List<Recruit> getAllRecruit() throws SQLException;

	// 회사 전체 공고 조회
	List<Recruit> getERecruit(int index) throws SQLException;

	// 진행중인 공고 조회
	// 종료된 공고 조회
	List<Recruit> getRecruit(boolean check) throws SQLException;

	// 공고 등록
	void registerRecruit(Recruit recruit) throws SQLException;

	// 공고 수정
	void modifyRecruit(Recruit recruit) throws SQLException;

	// 공고 삭제
	void deleteRecruit(int index) throws SQLException;

	// 공고 조회
	Recruit getRecruit(int index) throws SQLException;

}
