package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.model.Recruit;

public interface RecruitService {

	// 전체 공고 조회
	List<Recruit> getAllRecruit();

	// 회사 전체 공고 조회
	List<Recruit> getERecruit(int index);

	// 진행중인 공고 조회
	// 종료된 공고 조회
	List<Recruit> getRecruit(boolean check);

	// 공고 등록
	int registerRecruit(Recruit recruit);

	// 공고 수정
	int modifyRecruit(Recruit recruit);

	// 공고 삭제
	int deleteRecruit(int index);

	// 공고 조회
	Recruit getRecruit(int index);

}
