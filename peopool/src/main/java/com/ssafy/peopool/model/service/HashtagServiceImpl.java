package com.ssafy.peopool.model.service;

import java.util.ArrayList;
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
	public List<Map<String, Object>> tagByUser(int index, int type) {
		// TODO Auto-generated method stub
		return hashtagRepo.tagByUser(index, type);
	}
	
	@Override
	public List<Map<Object, Object>> userByTag(String name, int type) {
		// TODO Auto-generated method stub
		return hashtagRepo.userByTag(name, type);
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

	@Override
	public List<Map<Object, Object>> userTagIntersection(List<Integer> list) {
		int count = list.size();
		return hashtagRepo.userTagIntersection(list, count);
	}

	@Override
	public List<Map<Object, Object>> userTagUnion(List<Integer> list) {
		int count = list.size();
		return hashtagRepo.userTagUnion(list, count);
	}

	

}
