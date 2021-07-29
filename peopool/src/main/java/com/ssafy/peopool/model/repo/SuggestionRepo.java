package com.ssafy.peopool.model.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.Suggestion;

@Repository
public interface SuggestionRepo {

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
