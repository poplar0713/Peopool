package com.ssafy.peopool.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.peopool.model.CateList;
import com.ssafy.peopool.model.TagList;
import com.ssafy.peopool.model.repo.TagListRepo;

@Service
public class TagListServiceImpl implements TagListService {

	@Autowired
	TagListRepo taglistRepo;

	@Override
	public List<TagList> getTagList() {
		// TODO Auto-generated method stub
		return taglistRepo.getTagList();
	}

	@Override
	public List<TagList> getCTagList(int index) {
		// TODO Auto-generated method stub
		return taglistRepo.getCTagList(index);
	}

	@Override
	public List<CateList> getCategoryList() {
		// TODO Auto-generated method stub
		return taglistRepo.getCategoryList();
	}
	
	
}
