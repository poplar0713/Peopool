package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import com.ssafy.peopool.model.Suggestion;

public interface SuggestionService {

	// 면접 제안
	int registerSuggestion(Suggestion suggestion);

	// 면접 제안 수락
	int acceptSuggestion(String time, int index);

	// 면접 제안 거절
	int rejectSuggestion(int index);

	// 면접 제안 삭제
	int deleteSuggestion(int index);

	// 면접 제안 조회
	Suggestion getSuggestion(int index);

}
