package com.ssafy.peopool.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.IntCard;
import com.ssafy.peopool.model.Interview;

@Repository
public interface InterviewRepo {
	int registerURL(Interview interview) throws SQLException; // url 등록

	List<IntCard> getInterviews(int index) throws SQLException; // 면접 일정 조회

	List<IntCard> getLastInterviews(int index) throws SQLException; // 지난 일정 조회

	int finishInterview(Interview interview) throws SQLException; // 면접 종료

	int isInterview(Interview interview) throws SQLException; // 면접 노쇼

	int registerInterview(Interview interview) throws SQLException; // 면접 일정 등록
	
	List<IntCard> getEInterviews(int index) throws SQLException; // 기업의 면접 일정 조회

	List<IntCard> getELastInterviews(int index) throws SQLException; // 기업의 지난 면접 일정 조회
}
