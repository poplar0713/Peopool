package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.peopool.model.Hashtag;

public interface HashtagService {

	// 사용자 별 태그 목록
	List<Map<String, Object>> tagByUser(int index);

	// 태그별 사용자 목록
	List<Hashtag> userByTag(String name);
	
	// 태그 별 사용자 수
	List<Map<String, Object>> countByTag();

	// 태그 등록
	boolean registerHashtag(Hashtag hashtag);

	// 태그 삭제
	boolean deleteHashtag(int index);

}
