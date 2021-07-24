package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.repo.SuggestionRepo;

@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	SuggestionRepo suggestionRepo;
	
	
	@Override
	public void registerSuggestion(SuggestionRepo suggestion) throws SQLException {
		// TODO Auto-generated method stub
		suggestionRepo.registerSuggestion(suggestion);
	}

	@Override
	public void acceptSuggestion(String time, int index) throws SQLException {
		// TODO Auto-generated method stub
		suggestionRepo.acceptSuggestion(time, index);
	}

	@Override
	public void rejectSuggestion(int index) throws SQLException {
		// TODO Auto-generated method stub
		suggestionRepo.rejectSuggestion(index);
	}

	@Override
	public void deleteSuggestion(int index) throws SQLException {
		// TODO Auto-generated method stub
		suggestionRepo.deleteSuggestion(index);
	}

	@Override
	public SuggestionRepo getSuggestion(int index) throws SQLException {
		// TODO Auto-generated method stub
		return suggestionRepo.getSuggestion(index);
	}

}
