package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.peopool.model.Hashtag;

public interface HashtagService {

	// 사용자 별 태그 목록
	Map<String, Integer> tagByUser(int index);

	// 태그별 사용자 목록
	List<Integer> userByTag(String name);
	
	// 태그 별 사용자 수
	HashMap<String, Integer> countByTag();

	// 태그 등록
	boolean registerHashtag(Hashtag hashtag);

	// 태그 삭제
	boolean deletedHashtag(int index);

}
