package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import com.ssafy.peopool.model.repo.SuggestionRepo;

public interface SuggestionService {
	

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
