package com.ssafy.peopool.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionRepo {

	// 면접 제안
	void registerSuggestion(SuggestionRepo suggestion) throws SQLException;

	// 면접 제안 수락
	void acceptSuggestion(String time, int index) throws SQLException;

	// 면접 제안 거절
	void rejectSuggestion(int index) throws SQLException;

	// 면접 제안 삭제
	void deleteSuggestion(int index) throws SQLException;

	// 면접 제안 조회
	SuggestionRepo getSuggestion(int index) throws SQLException;

}
