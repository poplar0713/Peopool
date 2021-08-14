package com.ssafy.peopool.model.repo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.peopool.model.Hashtag;

@Repository
public interface HashtagRepo {

	// 사용자 별 태그 목록
	List<Map<String, Object>> tagByUser(int index, int type);
	
	// 태그별 사용자 목록
	List<Map<Object, Object>> userByTag(String name, int type);

	// 태그 별 사용자 수
	List<Map<String, Object>> countByTag();

	// 태그 등록
	int registerHashtag(Hashtag hashtag);

	// 태그 삭제
	int deleteHashtag(int index);

	// 태그를 모두 포함하는 사용자 교집합 목록
	List<Map<Object, Object>> userTagIntersection(List<String> list);
	
	// 태그를 포함하는 사용자 합집합 목록
	List<Map<Object, Object>> userTagUnion(List<String> list);
}
