package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Suggestion;
import com.ssafy.peopool.model.repo.SuggestionRepo;

@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	SuggestionRepo suggestionRepo;
	
	
	@Override
	public boolean registerSuggestion(Suggestion suggestion){
		// TODO Auto-generated method stub
		return suggestionRepo.registerSuggestion(suggestion) == 1;
	}

	@Override
	public boolean acceptSuggestion(Suggestion suggestion){
		// TODO Auto-generated method stub
		return suggestionRepo.acceptSuggestion(suggestion) == 1;
	}

	@Override
	public boolean rejectSuggestion(int index){
		// TODO Auto-generated method stub
		return suggestionRepo.rejectSuggestion(index) == 1;
	}

	@Override
	public boolean deleteSuggestion(int index) {
		// TODO Auto-generated method stub
		return suggestionRepo.deleteSuggestion(index) == 1;
	}

	@Override
	public List<Suggestion> getSuggestion(int index){
		// TODO Auto-generated method stub
		return suggestionRepo.getSuggestion(index);
	}

	@Override
	public List<Suggestion> getEntSuggestion(int index) {
		// TODO Auto-generated method stub
		return suggestionRepo.getEntSuggestion(index);
	}

}
