package com.ssafy.peopool.model.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.repo.HashtagRepo;

@Service
public class HashtagServiceImpl implements HashtagService {

	@Autowired
	HashtagRepo hashtagRepo;

	@Override
	public HashMap<Integer, String> tagByUser() {
		// TODO Auto-generated method stub
		return hashtagRepo.tagByUser();
	}

	@Override
	public HashMap<String, Integer> countByTag() {
		// TODO Auto-generated method stub
		return hashtagRepo.countByTag();
	}

	@Override
	public boolean registerHashtag(int tag_inex, int ind_index) {
		// TODO Auto-generated method stub
		return hashtagRepo.registerHashtag(tag_inex, ind_index) == 1;
	}

	@Override
	public boolean deletedHashtag(int index) {
		// TODO Auto-generated method stub
		return hashtagRepo.deletedHashtag(index) == 1;
	}

}
