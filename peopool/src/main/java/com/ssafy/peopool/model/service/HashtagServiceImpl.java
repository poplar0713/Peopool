package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.repo.HashtagRepo;

@Service
public class HashtagServiceImpl implements HashtagService {

	
	@Autowired
	HashtagRepo hashtagRepo;
	
	
	@Override
	public HashMap<Integer, String> tagByUser() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Integer> countByTag() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerHashtag(int tag_inex, int ind_index) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletedHashtag(int index) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
