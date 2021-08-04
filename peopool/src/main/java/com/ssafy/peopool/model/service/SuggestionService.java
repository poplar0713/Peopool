package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.peopool.model.Suggestion;

public interface SuggestionService {

	// 면접 제안
	boolean registerSuggestion(Suggestion suggestion);

	// 면접 제안 수락
	boolean acceptSuggestion(Suggestion suggestion);

	// 면접 제안 거절
	boolean rejectSuggestion(int index);
	
	// 면접 제안 거절
	boolean cancelSuggestion(int index);

	// 면접 제안 삭제
	boolean deleteSuggestion(int index);

	// 면접 제안 조회
	List<Suggestion> getSuggestion(int index);
	
	// 기업의 면접요청 기록 조회
	List<Suggestion> getEntSuggestion(int index);

}
