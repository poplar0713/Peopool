package com.ssafy.peopool.model.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.CateList;
import com.ssafy.peopool.model.TagList;

@Repository
public interface TagListRepo {

	// 태그목록조회
	List<TagList> getTagList();

	List<TagList> getCTagList(int index);

	List<CateList> getCategoryList();
}