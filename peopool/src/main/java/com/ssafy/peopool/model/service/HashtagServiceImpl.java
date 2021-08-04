package com.ssafy.peopool.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.Hashtag;
import com.ssafy.peopool.model.repo.HashtagRepo;

@Service
public class HashtagServiceImpl implements HashtagService {

	@Autowired
	HashtagRepo hashtagRepo;

	@Override
	public List<Map<String, Object>> tagByUser(int index) {
		// TODO Auto-generated method stub
		return hashtagRepo.tagByUser(index);
	}
	
	@Override
	public List<Hashtag> userByTag(String name) {
		// TODO Auto-generated method stub
		return hashtagRepo.userByTag(name);
	}

	@Override
	public List<Map<String, Object>> countByTag() {
		// TODO Auto-generated method stub
		return hashtagRepo.countByTag();
	}

	@Override
	public boolean registerHashtag(Hashtag hashtag) {
		// TODO Auto-generated method stub
		return hashtagRepo.registerHashtag(hashtag) == 1;
	}

	@Override
	public boolean deleteHashtag(int index) {
		// TODO Auto-generated method stub
		return hashtagRepo.deleteHashtag(index) == 1;
	}

	

}
