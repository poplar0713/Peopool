package com.ssafy.peopool.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.peopool.model.Hashtag;
import com.ssafy.peopool.model.SelectedTag;

public interface HashtagService {

	// 사용자 별 태그 목록
	List<Map<String, Object>> tagByUser(int index, int type);

	// 태그별 사용자 목록
	List<Map<Object, Object>> userByTag(String name, int type);
	
	// 태그 별 사용자 수
	List<Map<String, Object>> countByTag();

	// 태그 등록
	boolean registerHashtag(Hashtag hashtag);

	// 태그 삭제
	boolean deleteHashtag(int index);

	// 선택된 태그를 모두 포함하는 사용자 교집합 목록
	List<Map<Object, Object>> userTagIntersection(SelectedTag list);

	// 선택된 태그를 포함하는 사용자 합집합 목록
	List<Map<Object, Object>> userTagUnion(SelectedTag list);

}
