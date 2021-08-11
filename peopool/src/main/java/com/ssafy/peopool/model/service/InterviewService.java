package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.model.IntCard;
import com.ssafy.peopool.model.Interview;
import com.ssafy.peopool.model.repo.InterviewRepo;

public interface InterviewService {
	
	boolean registerURL(Interview interview) throws SQLException; // url 등록

	List<IntCard> getInterviews(int index) throws SQLException; // 개인의 면접 일정 조회

	List<IntCard> getLastInterviews(int index) throws SQLException; // 개인의 지난 면접 조회

	List<IntCard> getEInterviews(int index) throws SQLException; // 기업의 면접 일정 조회

	List<IntCard> getELastInterviews(int index) throws SQLException; // 기업의 지난 면접 조회
	
	boolean finishInterview(Interview interview) throws SQLException; // 면접 종료

	boolean isInterview(Interview interview) throws SQLException; // 면접 노쇼

	boolean registerInterview(Interview interview) throws SQLException; // 면접 일정 등록
	
	boolean modifyPass(int index) throws SQLException; // 면접 합격

	boolean modifyFail(int index) throws SQLException; // 면접 불합격

}
