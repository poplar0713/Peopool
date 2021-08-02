package com.ssafy.peopool.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Suggestion;
import com.ssafy.peopool.model.repo.SuggestionRepo;

@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	SuggestionRepo suggestionRepo;
	
	
	@Override
	public int registerSuggestion(Suggestion suggestion){
		// TODO Auto-generated method stub
		return suggestionRepo.registerSuggestion(suggestion);
	}

	@Override
	public int acceptSuggestion(String time, int index){
		// TODO Auto-generated method stub
		return suggestionRepo.acceptSuggestion(time, index);
	}

	@Override
	public int rejectSuggestion(int index){
		// TODO Auto-generated method stub
		return suggestionRepo.rejectSuggestion(index);
	}

	@Override
	public int deleteSuggestion(int index) {
		// TODO Auto-generated method stub
		return suggestionRepo.deleteSuggestion(index);
	}

	@Override
	public Suggestion getSuggestion(int index){
		// TODO Auto-generated method stub
		return suggestionRepo.getSuggestion(index);
	}

}
